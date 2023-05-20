package uz.java.uzum.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.dto.ReviewDto;
import uz.java.uzum.repository.ReviewRepository;
import uz.java.uzum.service.ReviewService;
import uz.java.uzum.service.mapper.ReviewMapper;


import static uz.java.uzum.service.appStatus.AppStatusCodes.*;
import static uz.java.uzum.service.appStatus.AppStatusMessages.*;

@Service
@RequiredArgsConstructor
public class  ReviewServiceImpl implements ReviewService {
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
