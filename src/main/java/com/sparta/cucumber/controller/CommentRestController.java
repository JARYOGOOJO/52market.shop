package com.sparta.cucumber.controller;

import com.sparta.cucumber.dto.CommentRequestDto;
import com.sparta.cucumber.models.Comment;
import com.sparta.cucumber.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CommentRestController {

    private final CommentService commentService;

    @Operation(description = "댓글 id로 가져오기",method = "GET")
    @GetMapping("/api/comments/{id}")
    public ResponseEntity<List<Comment>> getComments(@PathVariable("id") Long articleId) {
        List<Comment> comments = commentService.getComments(articleId);
        return ResponseEntity.ok().body(comments);
    }

    @Operation(description = "게시글 작성",method = "POST")
    @PostMapping("/api/comment")
    public ResponseEntity<Comment> writeComment(@RequestBody CommentRequestDto requestDto) {
        Comment comment = commentService.uploadOrUpdate(requestDto);
        return ResponseEntity.ok().body(comment);
    }

    @Operation(description = "게시글 삭제",method = "DELETE")
    @DeleteMapping("/api/comment/{id}")
    public ResponseEntity<Long> removeComment(@PathVariable("id") Long commentId) {
        Long result = commentService.checkAndDelete(commentId);
        return ResponseEntity.ok().body(result);
    }
}
