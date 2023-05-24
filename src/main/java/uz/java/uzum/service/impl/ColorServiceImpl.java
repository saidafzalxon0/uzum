package uz.java.uzum.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.java.uzum.dto.ColorDto;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.entity.Color;
import uz.java.uzum.repository.ColorRepository;
import uz.java.uzum.service.ColorService;
import uz.java.uzum.service.mapper.ColorMapper;

import java.util.List;
import java.util.Optional;

import static uz.java.uzum.service.appStatus.AppStatusCodes.*;
import static uz.java.uzum.service.appStatus.AppStatusMessages.NOT_FOUND;
import static uz.java.uzum.service.appStatus.AppStatusMessages.OK;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;
    private final ColorMapper colorMapper;
    @Override
    public ResponseDto<List<ColorDto>> getAll() {
        List<Color> getAll = colorRepository.findAll();

        return ResponseDto.<List<ColorDto>>builder()
                .message(OK)
                .success(true)
                .code(OK_CODE)
                .data(getAll.stream().map(colorMapper::toDto).toList())
                .build();
    }

    @Override
    public ResponseDto<ColorDto> getById(Integer id) {
        if (id == null){
            return ResponseDto.<ColorDto>builder()
                    .message("Id is null")
                    .code(VALIDATION_ERROR_CODE)
                    .build();
        }

        Optional<Color> byId = colorRepository.findById(id);

        if (byId.isEmpty()){
            return ResponseDto.<ColorDto>builder()
                    .message(NOT_FOUND)
                    .code(NOT_FOUND_ERROR_CODE)
                    .build();
        }

        return ResponseDto.<ColorDto>builder()
                .code(OK_CODE)
                .message(OK)
                .success(true)
                .data(colorMapper.toDto(byId.get()))
                .build();
    }

    @Override
    public ResponseDto<ColorDto> add(ColorDto colorDto) {
        Optional<Color> byId = colorRepository.findById(colorDto.getId());

        if (!byId.isEmpty()){
            return ResponseDto.<ColorDto>builder()
                    .message("Already exists")
                    .code(VALIDATION_ERROR_CODE)
                    .build();
        }

        colorRepository.save(colorMapper.toEntity(colorDto));

        return ResponseDto.<ColorDto>builder()
                .code(OK_CODE)
                .success(true)
                .message(OK)
                .data(colorMapper.toDto(byId.get()))
                .build();
    }

    @Override
    public ResponseDto<ColorDto> update(ColorDto colorDto) {
        if (colorDto.getId() == null){
            return ResponseDto.<ColorDto>builder()
                    .message("Id is null")
                    .code(VALIDATION_ERROR_CODE)
                    .build();
        }

        Optional<Color> byId = colorRepository.findById(colorDto.getId());
        Color color = byId.get();

        if (byId.isEmpty()){
            return ResponseDto.<ColorDto>builder()
                    .message(NOT_FOUND)
                    .code(NOT_FOUND_ERROR_CODE)
                    .build();
        }

        if (colorDto.getName() != null){
            color.setName(colorDto.getName());
        }
        colorRepository.save(color);

        return ResponseDto.<ColorDto>builder()
                .code(OK_CODE)
                .success(true)
                .message(OK)
                .data(colorMapper.toDto(color))
                .build();
    }

    @Override
    public ResponseDto<ColorDto> delete(Integer id) {
        if (id == null){
            return ResponseDto.<ColorDto>builder()
                    .message("Id is null")
                    .code(VALIDATION_ERROR_CODE)
                    .build();
        }

        Optional<Color> byId = colorRepository.findById(id);

        if (byId.isEmpty()){
            return ResponseDto.<ColorDto>builder()
                    .message(NOT_FOUND)
                    .code(NOT_FOUND_ERROR_CODE)
                    .build();
        }

        colorRepository.deleteById(id);

        return ResponseDto.<ColorDto>builder()
                .code(OK_CODE)
                .success(true)
                .message(OK)
                .data(colorMapper.toDto(byId.get()))
                .build();
    }
}
