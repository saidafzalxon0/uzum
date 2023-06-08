package uz.java.uzum.service.mapper;

import org.mapstruct.Mapper;
import uz.java.uzum.dto.ImageQualityDto;
import uz.java.uzum.entity.ImageQuality;

@Mapper(componentModel = "spring")
public abstract class ImageQualityMapper implements CommonMapper<ImageQualityDto, ImageQuality>{
    @Override
    public abstract ImageQualityDto toDto(ImageQuality imageQuality);
    @Override
    public abstract ImageQuality toEntity(ImageQualityDto imageQualityDto);
}
