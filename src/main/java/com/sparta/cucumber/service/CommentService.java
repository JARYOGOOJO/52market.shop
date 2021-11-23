package com.sparta.cucumber.service;

import com.sparta.cucumber.dto.CommentRequestDto;
import com.sparta.cucumber.models.Article;
import com.sparta.cucumber.models.Comment;
import com.sparta.cucumber.repository.ArticleRepository;
import com.sparta.cucumber.repository.CommentRepository;
import com.sparta.cucumber.user.User;
import com.sparta.cucumber.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private CommentRepository commentRepository;
    private ArticleRepository articleRepository;
    private UserRepository userRepository;

    public Comment upload (CommentRequestDto requestDto) {
        Article article = articleRepository.findById(requestDto.getArticleId()).orElse(null);
        User user = userRepository.findById(requestDto.getUserId()).orElse(null);
        Comment comment = new Comment(user, article, requestDto.getContent());
        return commentRepository.save(comment);
    }

    public Comment update (Long commentId, CommentRequestDto requestDto) {

    }
}
