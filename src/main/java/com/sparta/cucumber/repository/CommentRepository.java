package com.sparta.cucumber.repository;

import com.sparta.cucumber.models.Article;
import com.sparta.cucumber.models.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @EntityGraph(attributePaths = {"article", "user"})
    List<Comment> findAllByArticle_Id(Long articleId);

    @EntityGraph(attributePaths = {"article", "user"})
    List<Comment> findAllByArticle(Article article);
}
