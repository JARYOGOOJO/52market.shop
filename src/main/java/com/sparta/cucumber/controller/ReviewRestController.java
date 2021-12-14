package com.sparta.cucumber.controller;

import com.sparta.cucumber.dto.ReviewRequestDto;
import com.sparta.cucumber.models.Review;
import com.sparta.cucumber.repository.ReviewRepository;
import com.sparta.cucumber.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ReviewRestController {

    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;


    @Operation(description = "리뷰 id로 가져오기", method = "GET")
    @GetMapping("/api/{id}/reviews")
    public List<Review> getReviews(@PathVariable("id") Long reviewTargetId) {
        return reviewService.getReviews(reviewTargetId);
    }

    @Operation(description = "리뷰 저장하기", method = "POST")
    @PostMapping("/api/review")
    public ResponseEntity<Review> writeReview(@RequestBody ReviewRequestDto requestDto) {
        Review review = reviewService.uploadOrUpdate(requestDto);
        return ResponseEntity.ok().body(review);
    }

    @Operation(description = "리뷰 변경하기", method = "PUT")
    @PutMapping("/api/reviews/{id}")
    public ResponseEntity<Review> editReview(@PathVariable("id") Long reviewId, @RequestBody ReviewRequestDto requestDto) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(NullPointerException::new);
        reviewService.uploadOrUpdate(review, requestDto);
        return ResponseEntity.ok().body(review);
    }

    // delete 기능을 만들지 논의가 필요할 듯 합니다
    @Operation(description = "리뷰 삭제하기", method = "DELETE")
    @DeleteMapping("/api/review/{id}")
    public ResponseEntity<Long> deleteReview(@PathVariable("id") Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok().body(reviewId);
    }
}
