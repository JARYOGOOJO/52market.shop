package com.sparta.cucumber.repository;

import com.sparta.cucumber.models.Article;
import com.sparta.cucumber.models.Meet;
import com.sparta.cucumber.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetRepository extends JpaRepository<Meet, Long> {
    void deleteByArticle(Article article);
    Meet findByArticle(Article article);
    Meet findByCommenterAndArticle(User commenter, Article article);
}
