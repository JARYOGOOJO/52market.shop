package com.sparta.cucumber.service;

import com.sparta.cucumber.models.Review;
import com.sparta.cucumber.models.User;
import com.sparta.cucumber.repository.ReviewRepository;
import com.sparta.cucumber.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class ReviewServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Test
    @Transactional
    void uploadOrUpdate() {

        User user1 = User.builder()
                .name("user1")
                .password("123")
                .email("123@gmail.com")
                .build();

        User saveUser1 = userRepository.save(user1);

        User user2 = User.builder()
                .name("user2")
                .password("321")
                .email("321@gmail.com")
                .build();

        User saveUser2 = userRepository.save(user2);

        Review review = Review.builder()
                .from(saveUser1)
                .to(saveUser2)
                .content("좋아욤")
                .star(3)
                .build();
        Review saveReview = reviewRepository.save(review);

        Optional<Review> findReview = reviewRepository.findById(saveReview.getId());
        Assertions.assertThat(saveReview).isEqualTo(findReview.get());

    }
}