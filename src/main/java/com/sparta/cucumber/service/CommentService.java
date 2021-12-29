package com.sparta.cucumber.service;

import com.sparta.cucumber.dto.CommentRequestDto;
import com.sparta.cucumber.error.CustomException;
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

import static com.sparta.cucumber.error.ErrorCode.ARTICLE_NOT_FOUND;
import static com.sparta.cucumber.error.ErrorCode.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Transactional
    public Comment uploadOrUpdate(CommentRequestDto requestDto) {
        Article article = articleRepository
                .findById(requestDto.getArticleId())
                .orElseThrow(
                        () -> new CustomException(ARTICLE_NOT_FOUND));
        User user = userRepository
                .findById(requestDto.getUserId())
                .orElseThrow(
                        () -> new CustomException(USER_NOT_FOUND));
        Comment comment = Comment
                .builder()
                .content(requestDto.getContent())
                .user(user)
                .article(article)
                .build();
        article.addComment(comment);
        return commentRepository.save(comment);
    }

    public List<Comment> getComments(Long articleId) {
        return commentRepository.findAllByArticle_Id(articleId);
    }

    @Transactional
    public Long checkAndDelete(Long commentId) {
        commentRepository.deleteById(commentId);
        return commentId;
    }
}
