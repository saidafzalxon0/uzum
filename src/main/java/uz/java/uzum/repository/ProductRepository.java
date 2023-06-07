package uz.java.uzum.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.java.uzum.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> universalSearch(String query, List<String> filter, String sorting, String ordering, Integer size, Integer currentPage);
    Page<Product> getWithSort(Integer id, List<String> filter,String sorting, String ordering, Integer currentPage);
    boolean insertViewedProduct(Integer userId, Integer productId);
}
