package uz.java.uzum.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.java.uzum.dto.CategoryDto;
import uz.java.uzum.dto.ProductDto;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.entity.Product;
import uz.java.uzum.repository.CategoryRepository;
import uz.java.uzum.repository.ProductRepository;
import uz.java.uzum.service.CategoryService;
import uz.java.uzum.service.mapper.BrandMapper;
import uz.java.uzum.service.mapper.CategoryMapper;
import uz.java.uzum.service.mapper.ProductMapper;

import java.util.List;

import static uz.java.uzum.service.appStatus.AppStatusCodes.DATABASE_ERROR_CODE;
import static uz.java.uzum.service.appStatus.AppStatusCodes.OK_CODE;
import static uz.java.uzum.service.appStatus.AppStatusMessages.*;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final BrandMapper brandMapper;

    @Override
    public ResponseDto<CategoryDto> addCategory(CategoryDto categoryDto) {
        try {
            return ResponseDto.<CategoryDto>builder()
                    .data(categoryMapper.toDto(
                            categoryRepository.save(
                                    categoryMapper.toEntity(categoryDto)
                            )
                    ))
                    .message(OK)
                    .success(true)
                    .build();
        }catch (Exception e){
            return ResponseDto.<CategoryDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + ": " + e.getMessage())
                    .data(categoryDto)
                    .build();
        }
    }

    @Override
    public ResponseDto<Page<ProductDto>> getWithSort(Integer id, List<String> filter, String sorting, String ordering, Integer currentPage) {
        Page<Product> sort = productRepository.getWithSort(id,filter, sorting, ordering, currentPage);
        if(sort.isEmpty()){
            return ResponseDto.<Page<ProductDto>>builder()
                    .message(EMPTY_STRING)
                    .code(OK_CODE)
                    .success(true)
                    .build();
        }
        return ResponseDto.<Page<ProductDto>>builder()
                .data(sort.map(pr->productMapper.toDto(pr)))
                .code(OK_CODE)
                .message(OK)
                .build();
    }
}
