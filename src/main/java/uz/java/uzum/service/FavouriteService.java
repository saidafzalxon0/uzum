package uz.java.uzum.service;

import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.projection.ProductProjection;

import java.util.List;

public interface FavouriteService {
    ResponseDto<List<ProductProjection>> getFavouriteByUser(Integer userId);
    ResponseDto<Boolean> like(Integer userId, Integer productId);

    ResponseDto<Boolean> unlike(Integer userId, Integer productId);
}
