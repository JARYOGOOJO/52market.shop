package com.sparta.cucumber.service;

import com.sparta.cucumber.dto.ReviewRequestDto;
import com.sparta.cucumber.models.Review;
import com.sparta.cucumber.models.User;
import com.sparta.cucumber.repository.ReviewRepository;
import com.sparta.cucumber.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public List<Review> getReviews(Long reviewTargetId) {
        return reviewRepository.findAllByReviewTargetUser_Id(reviewTargetId);
    }


    @Transactional
    public Review uploadOrUpdate(ReviewRequestDto requestDto) {
        User reviewUser = userRepository.findById(requestDto.getReviewUserid()).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        User targetUser = userRepository.findById(requestDto.getReviewTargetUserId()).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );

        Review review = Review.builder()
                .from(reviewUser)
                .to(targetUser)
                .content(requestDto.getContent())
                .star(requestDto.getScore())
                .build();

        return reviewRepository.save(review);
    }

    @Transactional
    public Long deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );
        reviewRepository.deleteById(review.getId());
        return review.getId();
    }
}
