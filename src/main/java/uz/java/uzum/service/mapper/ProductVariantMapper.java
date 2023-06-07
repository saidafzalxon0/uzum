package uz.java.uzum.service.mapper;

import org.mapstruct.Mapper;
import uz.java.uzum.dto.ProductVariantDto;
import uz.java.uzum.entity.ProductVariant;

@Mapper(componentModel = "spring")
public abstract class ProductVariantMapper implements CommonMapper<ProductVariantDto, ProductVariant>{
    @Override
    public abstract ProductVariantDto toDto(ProductVariant productVariant);

    @Override
    public abstract ProductVariant toEntity(ProductVariantDto productVariantDto);
}
