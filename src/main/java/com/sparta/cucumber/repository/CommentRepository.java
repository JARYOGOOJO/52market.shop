package com.sparta.cucumber.repository;

import com.sparta.cucumber.models.Article;
import com.sparta.cucumber.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
