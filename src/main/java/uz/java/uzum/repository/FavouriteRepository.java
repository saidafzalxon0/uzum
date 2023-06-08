package uz.java.uzum.repository;

import uz.java.uzum.projections.ProductProjection;

import java.util.List;

public interface FavouriteRepository{
    boolean like(Integer userId, Integer productId);
    List<ProductProjection> getFavouriteByUser(Integer userId);
    boolean unlike(Integer userId, Integer productId);
}
