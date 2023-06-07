package uz.java.uzum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.java.uzum.projection.ProductProjection;

import java.util.List;

@Repository
public interface FavouriteRepository extends JpaRepository {
    boolean like(Integer userId, Integer productId);
    List<ProductProjection> getFavouriteByUser(Integer userId);
    boolean unlike(Integer userId, Integer productId);
}
