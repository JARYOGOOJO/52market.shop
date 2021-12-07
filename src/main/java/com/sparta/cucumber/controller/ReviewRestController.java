package com.sparta.cucumber.controller;

import com.sparta.cucumber.dto.ReviewRequestDto;
import com.sparta.cucumber.models.Review;
import com.sparta.cucumber.repository.ReviewRepository;
import com.sparta.cucumber.service.ReviewService;
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

    @GetMapping("/api/{id}/reviews")
    public List<Review> getReviews(@PathVariable("id") Long reviewTargetId) {
        return reviewService.getReviews(reviewTargetId);
    }

    @PostMapping("/api/review")
    public ResponseEntity<Review> writeReview(@RequestBody ReviewRequestDto requestDto) {
        Review review = reviewService.uploadOrUpdate(requestDto);
        return ResponseEntity.ok().body(review);
    }

    @PutMapping("/api/reviews/{id}")
    public ResponseEntity<Review> editReview(@PathVariable("id") Long reviewId, @RequestBody ReviewRequestDto requestDto) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(NullPointerException::new);
        reviewService.uploadOrUpdate(review, requestDto);
        return ResponseEntity.ok().body(review);
    }

    // delete 기능을 만들지 논의가 필요할 듯 합니다
    @DeleteMapping("/api/review/{id}")
    public ResponseEntity<Long> deleteReview(@PathVariable("id") Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok().body(reviewId);
    }
}
