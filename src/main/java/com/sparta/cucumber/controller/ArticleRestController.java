package com.sparta.cucumber.controller;

import com.sparta.cucumber.dto.ArticleRequestDto;
import com.sparta.cucumber.models.Article;
import com.sparta.cucumber.repository.ArticleRepository;
import com.sparta.cucumber.user.User;
import com.sparta.cucumber.user.UserRepository;
import com.sparta.cucumber.utils.LocationDistance;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class ArticleRestController {
    public final ArticleRepository articleRepository;
    public final UserRepository userRepository;
    public final LocationDistance location;

    @GetMapping("/api/articles")
    public List<Article> getArticles(@RequestParam(value="query") String query) {
        if (query != null) {
            return articleRepository.findAllByTitleContains(query);
        } else {
            return articleRepository.findAll();
        }
    }

    @GetMapping("/api/articles/{lat}/{lng}")
    public List<Article> getAroundArticle(@PathVariable("lat") Float lat, @PathVariable("lng") Float lon) {
        return articleRepository
                .findAll()
                .stream()
                .filter(article-> {
                    double dist = location.distance(lat, lon, article.getLatitude(), article.getLongitude(), "meter");
                    return dist < 1000;
                }).collect(Collectors.toList());
    }

    @GetMapping("/api/article/{id}")
    public Article seeDetailOfArticle(@PathVariable("id") Long articleId) {
        return articleRepository
                .findById(articleId)
                .orElseThrow(NullPointerException::new);
    }

    @GetMapping("/api/article/user/{id}")
    public List<Article> getUsersArticles(@PathVariable("id") Long userId) {
        return articleRepository
                .findAllByUser_Id(userId);
    }

    @PostMapping("/api/articles")
    public Article writeArticle(@RequestBody ArticleRequestDto requestDto) {
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
}
