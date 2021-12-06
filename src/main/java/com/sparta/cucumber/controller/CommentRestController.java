package com.sparta.cucumber.controller;

import com.sparta.cucumber.dto.CommentRequestDto;
import com.sparta.cucumber.models.Comment;
import com.sparta.cucumber.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CommentRestController {

    public final CommentService commentService;

    @GetMapping("/api/comments/{id}")
    public ResponseEntity<List<Comment>> getComments(@PathVariable("id") Long articleId) {
        List<Comment> comments = commentService.getComments(articleId);
        return ResponseEntity.ok().body(comments);
    }

    @PostMapping("/api/comment")
    public ResponseEntity<Comment> writeComment(@RequestBody CommentRequestDto requestDto) {
        Comment comment = commentService.uploadOrUpdate(requestDto);
        return ResponseEntity.ok().body(comment);
    }

    @DeleteMapping("/api/comment/{id}")
    public ResponseEntity<Long> removeComment(@PathVariable("id") Long commentId) {
        Long result = commentService.checkAndDelete(commentId);
        return ResponseEntity.ok().body(result);
    }
}
