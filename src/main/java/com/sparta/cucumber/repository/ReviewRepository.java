package com.sparta.cucumber.repository;

import com.sparta.cucumber.models.Article;
import com.sparta.cucumber.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
