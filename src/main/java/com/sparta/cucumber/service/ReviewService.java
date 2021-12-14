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

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> getUserReview(Long userId) {
        return reviewRepository.findAllByUser_Id(userId);
    }

    @Transactional
    public Review upload(ReviewRequestDto reviewRequestDto) {
        Long userId = reviewRequestDto.getUserId();
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NullPointerException("해당 사용자가 존재하지 않습니다")
        );
        Review review = Review.builder()
                .user(user)
                .title(reviewRequestDto.getTitle())
                .content(reviewRequestDto.getContent())
                .build();

        return reviewRepository.save(review);
    }

    @Transactional
    public Review update(Long reviewId, ReviewRequestDto reviewRequestDto) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new NullPointerException("해당 게시글이 존재하지 않습니다")
        );
        review.update(reviewRequestDto);
        return review;
    }

    @Transactional
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );
        reviewRepository.deleteById(review.getId());
    }
}
