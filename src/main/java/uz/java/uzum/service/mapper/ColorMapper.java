package uz.java.uzum.service.mapper;

import org.mapstruct.Mapper;
import uz.java.uzum.dto.ColorDto;
import uz.java.uzum.entity.Color;

@Mapper(componentModel = "spring")
public abstract class ColorMapper implements CommonMapper<ColorDto, Color>{
    @Override
    public abstract ColorDto toDto(Color color);

    @Override
    public abstract Color toEntity(ColorDto colorDto);
}
