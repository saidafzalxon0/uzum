package uz.java.uzum.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.java.uzum.dto.CategoryDto;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.entity.Category;
import uz.java.uzum.repository.CategoryRepository;
import uz.java.uzum.service.CategoryService;
import uz.java.uzum.service.mapper.CategoryMapper;

import static uz.java.uzum.service.appStatus.AppStatusCodes.*;
import static uz.java.uzum.service.appStatus.AppStatusMessages.*;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    @Override
    public ResponseDto<CategoryDto> addCategory(CategoryDto categoryDto) {
        try {
            System.out.println(categoryRepository.findById(3).isPresent() ? categoryRepository.findById(3).get().getParentId().getName() : null);
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
}
