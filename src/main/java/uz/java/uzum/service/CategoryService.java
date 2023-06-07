package uz.java.uzum.service;

import org.springframework.data.domain.Page;
import uz.java.uzum.dto.CategoryDto;
import uz.java.uzum.dto.ProductDto;
import uz.java.uzum.dto.ResponseDto;

import java.util.List;

public interface CategoryService {
    ResponseDto<CategoryDto> addCategory(CategoryDto categoryDto);
    ResponseDto<Page<ProductDto>> getWithSort(Integer id, List<String> filter, String sorting, String ordering, Integer currentPage);
}
