package uz.java.uzum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.java.uzum.dto.ColorDto;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.service.ColorService;

import java.util.List;

@RestController
@RequestMapping("/color")
@RequiredArgsConstructor
public class ColorController {
    private final ColorService colorService;

    @GetMapping
    public ResponseDto<List<ColorDto>> getAll(){
        return colorService.getAll();
    }

    @PostMapping
    public ResponseDto<ColorDto> add(@RequestBody ColorDto colorDto){
        return colorService.add(colorDto);
    }

    @DeleteMapping("{id}")
    public ResponseDto<ColorDto> delete(@PathVariable Integer id){
        return colorService.delete(id);
    }

    @GetMapping("{id}")
    public ResponseDto<ColorDto> getById(@PathVariable Integer id){
        return colorService.getById(id);
    }

    @PatchMapping
    public ResponseDto<ColorDto> update(@RequestBody ColorDto colorDto){
        return colorService.update(colorDto);
    }
}
