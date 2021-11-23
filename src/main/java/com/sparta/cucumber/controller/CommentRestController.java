package com.sparta.cucumber.controller;

import com.sparta.cucumber.dto.CommentRequestDto;
import com.sparta.cucumber.models.Article;
import com.sparta.cucumber.models.Comment;
import com.sparta.cucumber.repository.ArticleRepository;
import com.sparta.cucumber.repository.CommentRepository;
import com.sparta.cucumber.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentRestController {
    public final CommentRepository commentRepository;
    public final ArticleRepository articleRepository;

    @GetMapping("/api/comments/{id}")
    public List<Comment> getComments(@PathVariable("id") Long articleId) {
        return commentRepository.findAllByArticle_Id(articleId);
    }

    @PostMapping("/api/comment/{id}")
    public Comment writeComment(@PathVariable("id") Long id,
                                @RequestBody CommentRequestDto requestDto) {
        Comment comment = new Comment(requestDto);
        Article article = articleRepository
                .findById(id)
                .orElseThrow(NullPointerException::new);
        comment.setArticle(article);
        return commentRepository.save(comment);
    }

    @PutMapping("/api/")
}
