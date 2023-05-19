package uz.java.uzum.service;

import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.dto.ReviewDto;

public interface ReviewService {
    ResponseDto<ReviewDto> addReview(ReviewDto reviewDto);
}
