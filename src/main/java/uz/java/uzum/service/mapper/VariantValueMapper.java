package uz.java.uzum.service.mapper;

import org.mapstruct.Mapper;
import uz.java.uzum.dto.VariantValueDto;
import uz.java.uzum.entity.VariantValue;
@Mapper(componentModel = "spring")
public abstract class VariantValueMapper implements CommonMapper<VariantValueDto, VariantValue>{
    @Override
    public abstract VariantValueDto toDto(VariantValue variantValue);

    @Override
    public abstract VariantValue toEntity(VariantValueDto variantValueDto);
}
