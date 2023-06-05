package uz.java.uzum.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import uz.java.uzum.dto.BrandDto;
import uz.java.uzum.dto.CategoryDto;
import uz.java.uzum.dto.ProductDto;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.entity.Product;
import uz.java.uzum.service.impl.CategoryServiceImpl;

import java.util.Map;
import java.util.Set;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryResources {

    private final CategoryServiceImpl categoryService;
    @GetMapping("/{id}")
    public ResponseDto<Page<ProductDto>> get(@PathVariable Integer id,
                                             @RequestParam(required = false) String sorting,
                                             @RequestParam(required = false) String ordering,
                                             @RequestParam(required = false,defaultValue = "10") Integer size,
                                             @RequestParam(required = false, defaultValue = "0") Integer currentPage){
        return categoryService.getWithSort(id, sorting,ordering,currentPage);
    }
    @PostMapping
    public ResponseDto<CategoryDto> addCategory(@RequestBody @Valid CategoryDto categoryDto){
        return categoryService.addCategory(categoryDto);
    }
    @GetMapping("/{id}/brand")
    public ResponseDto<Page<ProductDto>> byBrand(@PathVariable Integer id,
                                                 @RequestParam List<String> filter,
                                                 @RequestParam(required = false) Integer currentPage) {
        return categoryService.getByBrand(id,filter,currentPage);
    }
    @GetMapping("/{id}/brands")
    public ResponseDto<Set<BrandDto>> brands(@PathVariable Integer id){
        return categoryService.brandByCategory(id);
    }

}