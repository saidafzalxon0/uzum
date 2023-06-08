package uz.java.uzum.service.mapper;

import org.mapstruct.Mapper;
import uz.java.uzum.dto.BrandDto;
import uz.java.uzum.entity.Brand;
@Mapper(componentModel = "spring")
public abstract class BrandMapper implements CommonMapper<BrandDto, Brand>{
    @Override
    public abstract BrandDto toDto(Brand brand);

    @Override
    public abstract Brand toEntity(BrandDto brandDto);
}
