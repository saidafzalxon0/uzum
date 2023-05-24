package uz.java.uzum.service;

import uz.java.uzum.dto.ColorDto;
import uz.java.uzum.dto.ResponseDto;

import java.util.List;

public interface ColorService {
    ResponseDto<List<ColorDto>> getAll();
    ResponseDto<ColorDto> getById(Integer id);
    ResponseDto<ColorDto> add(ColorDto colorDto);
    ResponseDto<ColorDto> update(ColorDto colorDto);
    ResponseDto<ColorDto> delete(Integer id);
}
