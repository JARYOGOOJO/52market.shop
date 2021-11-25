package com.sparta.cucumber.controller;

import com.sparta.cucumber.dto.CommentRequestDto;
import com.sparta.cucumber.models.Article;
import com.sparta.cucumber.models.Comment;
import com.sparta.cucumber.repository.ArticleRepository;
import com.sparta.cucumber.repository.CommentRepository;
import com.sparta.cucumber.repository.ReviewRepository;
import com.sparta.cucumber.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentRestController {
    public final CommentRepository commentRepository;
    public final CommentService commentService;
    public final ArticleRepository articleRepository;

    @GetMapping("/api/comments/{id}")
    public List<Comment> getComments(@PathVariable("id") Long articleId) {
        return commentRepository.findAllByArticle_Id(articleId);
    }

    @PostMapping("/api/comment")
    public Comment writeComment(@RequestBody CommentRequestDto requestDto) {
        return commentService.uploadOrUpdate(requestDto);
    }

    @PutMapping("/api/comment")
    public Comment editComment(@RequestBody CommentRequestDto requestDto) {
        return commentService.uploadOrUpdate(requestDto);
    }
}
