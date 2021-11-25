package com.sparta.cucumber.controller;

import com.sparta.cucumber.dto.CommentRequestDto;
import com.sparta.cucumber.models.Article;
import com.sparta.cucumber.models.Comment;
import com.sparta.cucumber.repository.ArticleRepository;
import com.sparta.cucumber.repository.CommentRepository;
import com.sparta.cucumber.repository.ReviewRepository;
import com.sparta.cucumber.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentRestController {

    public final CommentRepository commentRepository;
    public final CommentService commentService;

    @GetMapping("/api/comments/{id}")
    public ResponseEntity<List<Comment>> getComments(@PathVariable("id") Long articleId) {
        List<Comment> comments = commentRepository.findAllByArticle_Id(articleId);
        return ResponseEntity.ok().body(comments);
    }

    @PostMapping("/api/comment")
    public ResponseEntity<Comment> writeComment(@RequestBody CommentRequestDto requestDto) {
        Comment comment = commentService.uploadOrUpdate(requestDto);
        return ResponseEntity.ok().body(comment);
    }

    @PutMapping("/api/comment")
    public ResponseEntity<Comment> editComment(@RequestBody CommentRequestDto requestDto) {
        Comment comment = commentService.uploadOrUpdate(requestDto);
        return ResponseEntity.ok().body(comment);
    }
}
