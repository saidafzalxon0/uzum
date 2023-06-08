package uz.java.uzum.service.mapper;

import org.mapstruct.Mapper;
import uz.java.uzum.dto.VariantDto;
import uz.java.uzum.entity.Variant;
@Mapper(componentModel = "spring")
public abstract class VariantMapper implements CommonMapper<VariantDto, Variant>{
    @Override
    public abstract VariantDto toDto(Variant variant);


    @Override
    public abstract Variant toEntity(VariantDto variantDto);
}
