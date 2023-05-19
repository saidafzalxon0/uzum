package uz.java.uzum.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.java.uzum.dto.CategoryDto;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.dto.ReviewDto;
import uz.java.uzum.repository.CategoryRepository;
import uz.java.uzum.repository.ReviewRepository;
import uz.java.uzum.service.ReviewService;
import uz.java.uzum.service.mapper.CategoryMapper;
import uz.java.uzum.service.mapper.ReviewMapper;

import java.time.LocalDateTime;

import static uz.java.uzum.service.AppStatus.AppStatusCodes.DATABASE_ERROR_CODE;
import static uz.java.uzum.service.AppStatus.AppStatusMessages.DATABASE_ERROR;
import static uz.java.uzum.service.AppStatus.AppStatusMessages.OK;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    @Override
    public ResponseDto<ReviewDto> addReview(ReviewDto reviewDto) {
        try {
            return ResponseDto.<ReviewDto>builder()
                    .data(reviewMapper.toDto(
                            reviewRepository.save(
                                    reviewMapper.toEntity(reviewDto)
                            )
                    ))
                    .message(OK)
                    .success(true)
                    .build();
        }catch (Exception e){
            return ResponseDto.<ReviewDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + ": " + e.getMessage())
                    .data(reviewDto)
                    .build();
        }
    }
}
