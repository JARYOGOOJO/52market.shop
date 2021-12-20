package com.sparta.cucumber.service;

import com.sparta.cucumber.dto.ReviewRequestDto;
import com.sparta.cucumber.error.CustomException;
import com.sparta.cucumber.models.Review;
import com.sparta.cucumber.models.User;
import com.sparta.cucumber.repository.ReviewRepository;
import com.sparta.cucumber.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.sparta.cucumber.error.ErrorCode.REVIEW_NOT_FOUND;
import static com.sparta.cucumber.error.ErrorCode.USER_NOT_FOUND;
import static org.springframework.web.util.HtmlUtils.htmlEscape;

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
                () -> new CustomException(USER_NOT_FOUND)
        );
        Review review = Review.builder()
                .user(user)
                .title(htmlEscape(reviewRequestDto.getTitle()))
                .content(htmlEscape(reviewRequestDto.getContent()))
                .build();

        return reviewRepository.save(review);
    }

    @Transactional
    public Review update(Long reviewId, ReviewRequestDto reviewRequestDto) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new CustomException(REVIEW_NOT_FOUND)
        );
        review.update(reviewRequestDto);
        return review;
    }

    @Transactional
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new CustomException(REVIEW_NOT_FOUND)
        );
        reviewRepository.deleteById(review.getId());
    }
}
