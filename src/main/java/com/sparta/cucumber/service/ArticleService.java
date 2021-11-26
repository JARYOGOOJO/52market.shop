package com.sparta.cucumber.service;

import com.sparta.cucumber.dto.ArticleRequestDto;
import com.sparta.cucumber.models.Article;
import com.sparta.cucumber.models.User;
import com.sparta.cucumber.repository.ArticleRepository;
import com.sparta.cucumber.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    @Transactional
    public Article uploadOrUpdate(ArticleRequestDto requestDto) {
        Long userId = requestDto.getUserId();
        User user = userRepository
                .findById(userId)
                .orElseThrow(
                        () -> new NullPointerException("잘못된 접근입니다."));
        Article article = Article.builder()
                .user(user)
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .image(requestDto.getImage())
                .latitude(user.getLatitude())
                .longitude(user.getLongitude())
                .build();
        return articleRepository.save(article);
    }
}
