package com.sparta.cucumber.service;

import com.sparta.cucumber.dto.ReviewRequestDto;
import com.sparta.cucumber.models.Review;
import com.sparta.cucumber.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public Review uploadOrUpdate(ReviewRequestDto requestDto) {
        Review reviewUser = reviewRepository.findById(requestDto.getReviewUserid()).orElseThrow(
                () -> new NullPointerException("잘못된 접근입니다.")
        );
        Review reviewTargetUser = reviewRepository.findById(requestDto.getReviewTargetUserId()).orElse(null);

        assert reviewTargetUser != null;

        Review review = Review.builder()
                .content(requestDto.getContent())
                .star(requestDto.getScore())
                .build();

        return reviewRepository.save(review);
    }
}
