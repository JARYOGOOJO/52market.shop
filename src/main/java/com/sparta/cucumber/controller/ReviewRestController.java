package com.sparta.cucumber.controller;

import com.sparta.cucumber.dto.ReviewRequestDto;
import com.sparta.cucumber.models.Review;
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


//    @Operation(description = "리뷰 id로 가져오기", method = "GET")
//    @GetMapping("/api/{id}/reviews")
//    public List<Review> getReviews(@PathVariable("id") Long reviewTargetId) {
//        return reviewService.getReviews(reviewTargetId);
//    }
    @Operation(description = "모든 리뷰 가져오기", method = "GET")
    @GetMapping("/api/reviews")
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews =  reviewService.getAllReviews();
        return ResponseEntity.ok().body(reviews);
    }

    @Operation(description = "유저의 리뷰 가져오기", method = "GET")
    @GetMapping("/api/reviews/{id}")
    public ResponseEntity<List<Review>> getUserReviews(@PathVariable("id") Long userId) {
        List<Review> reviews = reviewService.getUserReview(userId);
        return ResponseEntity.ok().body(reviews);
    }


    @Operation(description = "리뷰 저장하기", method = "POST")
    @PostMapping("/api/review")
    public ResponseEntity<Review> writeReview(@RequestBody ReviewRequestDto requestDto) {
        Review review = reviewService.upload(requestDto);
        return ResponseEntity.ok().body(review);
    }

    @Operation(description = "리뷰 변경하기", method = "PUT")
    @PutMapping("/api/reviews/{id}")
    public ResponseEntity<Review> editReview(@PathVariable("id") Long reviewId, @RequestBody ReviewRequestDto requestDto) {
        Review review = reviewService.update(reviewId, requestDto);
        return ResponseEntity.ok().body(review);
    }

    @Operation(description = "리뷰 삭제하기", method = "DELETE")
    @DeleteMapping("/api/review/{id}")
    public ResponseEntity<Long> deleteReview(@PathVariable("id") Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok().body(reviewId);
    }
}
