package uz.java.uzum.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import uz.java.uzum.entity.Category;
import uz.java.uzum.entity.Product;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductRepositoryImpl implements ProductRepository, JpaRepository<Product, Integer> {
    private final EntityManager entityManager;

    @Override
    public Page<Product> universalSearch(String query, List<String> filter,String sorting, String ordering, Integer size, Integer currentPage) {
        size = Math.max(size, 0);
        int page = Math.max(currentPage,0);

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        criteriaQuery.select(root);

        Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + query.toLowerCase() + "%");
        Predicate categoryPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("category").get("name")), "%" + query.toLowerCase() + "%");
        Predicate descriptionPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + query.toLowerCase() + "%");
        Predicate finalPredicate = criteriaBuilder.or(namePredicate, categoryPredicate, descriptionPredicate);



        TypedQuery<Category> customQuery = entityManager.createQuery("select c from Category c where LOWER( c.name ) LIKE :query", Category.class);
        customQuery.setParameter("query","%"+query.toLowerCase()+"%");

        if(filter != null && !filter.isEmpty()){
            Predicate filterPredicate = root.get("brand").get("name").in(filter);
            finalPredicate = criteriaBuilder.and(filterPredicate,filterPredicate);
            criteriaQuery.where(finalPredicate);
        }else {
            criteriaQuery.where(finalPredicate);
        }
        if (sorting != null && ordering != null) {
            Path<Object> sortPath;
            if (ordering.equalsIgnoreCase("ascending")) {
                sortPath = root.get(sorting);
                criteriaQuery.orderBy(criteriaBuilder.asc(sortPath));
            } else if (ordering.equalsIgnoreCase("descending")) {
                sortPath = root.get(sorting);
                criteriaQuery.orderBy(criteriaBuilder.desc(sortPath));
            }
        }

        TypedQuery<Product> search = entityManager.createQuery(criteriaQuery);

        long count = search.getResultList().size();


        if (count > 0 && count / size <= page){
            if (count % size == 0) {
                page = (int) count / size - 1;
            } else {
                page = (int) count / size;
            }
        }
        search.setFirstResult(size * page);
        search.setMaxResults(size);

        if(search.getResultList().isEmpty()){
            if(!customQuery.getResultList().isEmpty()){
                return getWithSort(customQuery.getResultList().get(0).getId(),filter,sorting,ordering,currentPage);
            }
        }


        return new PageImpl<>(search.getResultList(),PageRequest.of(page,size),count);

    }

    @Override
    public Page<Product> getWithSort(Integer cid,List<String> filter, String sorting, String ordering, Integer currentPage) {
        int size = 10;
        int page = Math.max(currentPage,0);

        List<Integer> categoryIds = getCategoriesWithChildren(cid);

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);

        Predicate categoryPredicate = root.get("category").get("id").in(categoryIds);
        if(filter != null && !filter.isEmpty()){
            Predicate brandPredicate = root.get("brand").get("name").in(filter);
            criteriaQuery.select(root).where(criteriaBuilder.and(categoryPredicate,brandPredicate));
        }else {
            criteriaQuery.select(root).where(categoryPredicate);
        }


        if(sorting != null && ordering != null){
            Path<Object> sortPath = root.get(sorting);
            Order order = ordering.equalsIgnoreCase("ascending") ? criteriaBuilder.asc(sortPath) : criteriaBuilder.desc(sortPath);
            criteriaQuery.orderBy(order);
        }

        TypedQuery<Product> query = entityManager.createQuery(criteriaQuery);

        long count = query.getResultList().size();
        if (count > 0 && count / size <= page) {
            if (count % size == 0) {
                page = (int) count / size - 1;
            } else {
                page = (int) count / size;
            }
        }
        query.setFirstResult(page * size);
        query.setMaxResults(size);

        return new PageImpl<>(query.getResultList(), PageRequest.of(page, size), count);

    }

    @Override
    @Transactional
    public boolean insertViewedProduct(Integer userId, Integer productId) {
        Query query = entityManager.createNativeQuery("INSERT INTO viewed_products(user_id, product_id) VALUES (:uId, :pId)")
                .setParameter("uId", userId)
                .setParameter("pId", productId);

        try {
            int i = query.executeUpdate();
            return i > 0;
        } catch (Exception e) {
            log.error("error during insert viewed_product table {}",e.getMessage());
            return false;
        }
    }

    @Override
    public Optional<Product> findById(Integer productId) {
        return Optional.empty();
    }

    private List<Integer> getCategoriesWithChildren(Integer categoryId) {

        List<Integer> categoryIds = entityManager.createNativeQuery(
                        "WITH RECURSIVE child_categories AS (" +
                                "  SELECT id " +
                                "  FROM category " +
                                "  WHERE id = :categoryId " +
                                "  UNION ALL " +
                                "  SELECT c.id " +
                                "  FROM category c " +
                                "  JOIN child_categories cc ON cc.id = c.parent_category_id" +
                                ") " +
                                "SELECT id " +
                                "FROM child_categories"
                )
                .setParameter("categoryId", categoryId)
                .getResultList();

        return categoryIds;
    }
}