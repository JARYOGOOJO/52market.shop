package com.sparta.cucumber.controller;

import com.sparta.cucumber.models.Article;
import com.sparta.cucumber.repository.ArticleRepository;
import com.sparta.cucumber.utils.LocationDistance;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class ArticleRestController {
    public final ArticleRepository articleRepository;
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
}
