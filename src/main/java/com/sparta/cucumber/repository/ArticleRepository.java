package com.sparta.cucumber.repository;

import com.sparta.cucumber.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
