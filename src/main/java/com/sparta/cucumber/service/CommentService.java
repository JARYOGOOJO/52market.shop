package com.sparta.cucumber.service;

import com.sparta.cucumber.dto.CommentRequestDto;
import com.sparta.cucumber.models.Article;
import com.sparta.cucumber.models.Comment;
import com.sparta.cucumber.models.User;
import com.sparta.cucumber.repository.ArticleRepository;
import com.sparta.cucumber.repository.CommentRepository;
import com.sparta.cucumber.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Transactional
    public Comment uploadOrUpdate (CommentRequestDto requestDto) {
        Article article = articleRepository.findById(requestDto.getArticleId()).orElse(null);
        User user = userRepository.findById(requestDto.getUserId()).orElse(null);
        assert article != null;
        assert user != null;

        Comment comment = Comment
            .builder()
            .content(requestDto.getContent())
            .user(user)
            .article(article)
            .build();
        List<Comment> articleComments = article.getComments();
        articleComments.add(comment);
        return commentRepository.save(comment);
    }
}
