package uz.java.uzum.service.mapper;

import org.mapstruct.Mapper;
import uz.java.uzum.dto.ProductVariantDto;
import uz.java.uzum.entity.ProductVariant;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductVariantMapper extends CommonMapper<List<ProductVariantDto>, List<ProductVariant>> {
}