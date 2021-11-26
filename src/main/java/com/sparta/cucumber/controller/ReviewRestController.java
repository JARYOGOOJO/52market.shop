package com.sparta.cucumber.controller;

import com.sparta.cucumber.models.Review;
import com.sparta.cucumber.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ReviewRestController {

    public final ReviewRepository reviewRepository;

    @GetMapping("/{id}/reviews")
    List<Review> getReviews(@PathVariable("id") Long reviewTargetId) {
        return reviewRepository.findAllByReviewTargetUser_Id(reviewTargetId);
    }
}
