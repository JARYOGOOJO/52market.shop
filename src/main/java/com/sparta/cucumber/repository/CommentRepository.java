package com.sparta.cucumber.repository;

import com.sparta.cucumber.models.Article;
import com.sparta.cucumber.models.Comment;
import com.sparta.cucumber.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByArticle_Id(Long articleId);

    List<Comment> findAllByArticle(Article article);

    Comment findAllByArticleAndUserAndContent(Article article, User user, String content);
}
