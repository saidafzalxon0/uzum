package uz.java.uzum.repository;

import org.springframework.data.domain.Page;
import uz.java.uzum.entity.Product;

import java.util.List;

public interface ProductRepository{
    Page<Product> universalSearch(String query, List<String> filter, String sorting, String ordering, Integer size, Integer currentPage);
    Page<Product> getWithSort(Integer id, List<String> filter,String sorting, String ordering, Integer currentPage);
    boolean insertViewedProduct(Integer userId, Integer productId);
}
