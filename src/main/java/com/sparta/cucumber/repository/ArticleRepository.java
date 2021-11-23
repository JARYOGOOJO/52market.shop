package com.sparta.cucumber.repository;

import com.sparta.cucumber.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByTitleContains(String query);
    List<Article> findAllByUser_Id(Long userId);
}
