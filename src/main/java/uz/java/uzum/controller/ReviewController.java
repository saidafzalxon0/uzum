package uz.java.uzum.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.java.uzum.dto.CategoryDto;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.dto.ReviewDto;
import uz.java.uzum.service.Impl.CategoryServiceImpl;
import uz.java.uzum.service.Impl.ReviewServiceImpl;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewServiceImpl reviewService;

    @PostMapping
    public ResponseDto<ReviewDto> addReview(@RequestBody @Valid ReviewDto reviewDto){
        return reviewService.addReview(reviewDto);
    }
}
