package uz.java.uzum.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.java.uzum.dto.CategoryDto;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.service.impl.CategoryServiceImpl;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class    CategoryController {
    private final CategoryServiceImpl categoryService;

    @PostMapping
    public ResponseDto<CategoryDto> addCategory(@RequestBody @Valid CategoryDto categoryDto){
        return categoryService.addCategory(categoryDto);
    }
}
