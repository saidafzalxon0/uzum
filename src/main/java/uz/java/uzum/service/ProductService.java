package uz.java.uzum.service;

import org.springframework.data.domain.Page;
import uz.java.uzum.dto.ProductDto;
import uz.java.uzum.dto.ResponseDto;

public interface ProductService {
    ResponseDto<ProductDto> addProduct(ProductDto productDto);
    ResponseDto<ProductDto> updateProduct(ProductDto productDto);
    ResponseDto<Page<ProductDto>> getAllProducts(Integer page, Integer size);
    ResponseDto<ProductDto> getProductById(Integer id);
}
