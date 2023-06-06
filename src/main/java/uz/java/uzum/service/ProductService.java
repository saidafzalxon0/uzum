package uz.java.uzum.service;

import org.springframework.data.domain.Page;
import uz.java.uzum.dto.ProductDto;
import uz.java.uzum.dto.ResponseDto;

public interface ProductService {
    ResponseDto<ProductDto> addProduct(ProductDto productDto);
    ResponseDto<ProductDto> updateProduct(ProductDto productDto);
    ResponseDto<ProductDto> getById(Integer id);
    //ResponseDto<List<ProductProjection>> getProducts(Integer userId,Integer currentPage, Integer size);

    //ResponseDto<List<ProductProjection>> getViewedProduct(Integer userId);
}
