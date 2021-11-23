package com.sparta.cucumber.controller;

import com.sparta.cucumber.models.Comment;
import com.sparta.cucumber.repository.CommentRepository;
import com.sparta.cucumber.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentRestController {
    public final CommentRepository commentRepository;

    @GetMapping("/api/comments/{id}")
    public List<Comment> getComments(@PathVariable("id") Long articleId) {
        return commentRepository.findAllByArticle_Id();
    }

    @PostMapping("/api/comment/{id}")
    public Comment writeComment(@PathVariable("id") Long id) {
        return commentRepository.save(new Comment());
    }
}
