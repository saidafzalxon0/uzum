package uz.java.uzum.service;

import uz.java.uzum.dto.CategoryDto;
import uz.java.uzum.dto.ResponseDto;

public interface CategoryService {
    ResponseDto<CategoryDto> addCategory(CategoryDto categoryDto);
}
