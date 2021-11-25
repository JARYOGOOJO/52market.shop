package com.sparta.cucumber.service;

import com.sparta.cucumber.dto.ArticleRequestDto;
import com.sparta.cucumber.models.Article;
import com.sparta.cucumber.models.Timestamped;
import com.sparta.cucumber.repository.ArticleRepository;
import com.sparta.cucumber.user.User;
import com.sparta.cucumber.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
                .orElseThrow(NullPointerException::new);
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
