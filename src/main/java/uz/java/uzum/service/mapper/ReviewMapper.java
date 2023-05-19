package uz.java.uzum.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import uz.java.uzum.dto.CategoryDto;
import uz.java.uzum.dto.ReviewDto;
import uz.java.uzum.entity.Category;
import uz.java.uzum.entity.Review;
import uz.java.uzum.repository.CategoryRepository;
import uz.java.uzum.repository.ReviewRepository;
import uz.java.uzum.repository.UserRepository;

@Mapper(componentModel = "spring")
public abstract class ReviewMapper implements CommonMapper<ReviewDto, Review> {
    @Autowired
    protected ReviewRepository reviewRepository;
    @Autowired
    protected UserRepository userRepository;
    @Mapping(target = "parentId", expression = "java(review.getParentId() != null ? review.getParentId().getId() : null)")
    @Mapping(target = "userId", expression = "java(review.getUserId() != null ? review.getUserId().getId() : null)")
    public abstract ReviewDto toDto(Review review);

    @Mapping(target = "parentId", expression = "java(reviewDto.getParentId() != null ? reviewRepository.findById(reviewDto.getParentId()).get() : null)")
    @Mapping(target = "userId", expression = "java(reviewDto.getUserId() != null ? userRepository.findById(reviewDto.getUserId()).get() : null)")
    public abstract Review toEntity(ReviewDto reviewDto);
}
