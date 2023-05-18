package uz.java.uzum.service.mapper;

import uz.java.uzum.dto.ProductDto;
import uz.java.uzum.entity.Product;

public abstract class ProductMapper implements CommonMapper<ProductDto, Product>{
    @Override
    public abstract ProductDto toDto(Product product);

    @Override
    public abstract Product toEntity(ProductDto productDto);

}
