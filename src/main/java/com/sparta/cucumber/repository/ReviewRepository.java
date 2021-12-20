package com.sparta.cucumber.repository;

import com.sparta.cucumber.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByUser_Id(Long userId);
}
