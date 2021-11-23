package com.sparta.cucumber.service;

import com.sparta.cucumber.dto.ArticleRequestDto;
import com.sparta.cucumber.models.Article;
import com.sparta.cucumber.models.Timestamped;
import com.sparta.cucumber.repository.ArticleRepository;
import com.sparta.cucumber.user.User;
import com.sparta.cucumber.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    public Article upload(ArticleRequestDto requestDto) {
        Long userId = requestDto.getUserId();
        Article article = new Article();
        User user = userRepository.findById(userId).orElseThrow(NullPointerException::new);
        article.setUser(user);
        article.setTitle(requestDto.getTitle());
        article.setContent(requestDto.getContent());
        article.setImage(requestDto.getImage());
        article.setLatitude(user.getLatitude());
        article.setLongitude(user.getLongitude());
        return articleRepository.save(article);
    }

    public Article update(Long articleId, ArticleRequestDto requestDto) {
        Article article = articleRepository.findById(articleId).orElseThrow(NullPointerException::new);
        article.setTitle(requestDto.getTitle());
        article.setContent(requestDto.getContent());
        article.setImage(requestDto.getImage());
        return articleRepository.save(article);
    }
}
