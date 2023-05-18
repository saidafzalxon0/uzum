package uz.java.uzum.service.mapper;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import uz.java.uzum.dto.CategoryDto;
import uz.java.uzum.entity.Category;
import uz.java.uzum.repository.CategoryRepository;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper implements CommonMapper<CategoryDto, Category>{
    @Autowired
    protected CategoryRepository categoryRepository;
    @Mapping(target = "parentId", expression = "java(category.getParentId() != null ? category.getParentId().getId() : null)")
    public abstract CategoryDto toDto(Category category);

    @Mapping(target = "parentId", expression = "java(categoryDto.getParentId() != null ? categoryRepository.findById(categoryDto.getParentId()).get() : null)")
    public abstract Category toEntity(CategoryDto categoryDto);
}
