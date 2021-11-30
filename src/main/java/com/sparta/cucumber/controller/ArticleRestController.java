package com.sparta.cucumber.controller;

import com.sparta.cucumber.dto.ArticleRequestDto;
import com.sparta.cucumber.models.Article;
import com.sparta.cucumber.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ArticleRestController {

    public final ArticleService articleService;

    @GetMapping("/api/articles/{query}")
    public ResponseEntity<List<Article>> getArticles(@PathVariable("query") String query) {
        List<Article> articles = articleRepository.findAllByTitleContains(query);
        return ResponseEntity.ok().body(articles);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles;
        articles = articleRepository.findAll();
        return ResponseEntity.ok().body(articles);
    }

    @GetMapping("/api/article/{id}")
    public ResponseEntity<Article> seeDetailOfArticle(@PathVariable("id") Long articleId) {
        Article article = articleRepository
            .findById(articleId)
            .orElseThrow(NullPointerException::new);
        return ResponseEntity.ok().body(article);
    }

    @GetMapping("/api/articles/{lat}/{lng}")
    public ResponseEntity<List<Article>> getAroundArticle (@PathVariable("lat") Double lat,
                                                          @PathVariable("lng") Double lon) {
        List<Article> articles = articleRepository
            .findAll()
            .stream()
            .filter(article-> {
                double  dist  = location.distance(
                    lat, lon,
                    article.getLatitude(), article.getLongitude(),
                    "meter");
                return dist < 1000;
            }).collect(Collectors.toList());
        return ResponseEntity.ok().body(articles);
    }

    @GetMapping("/api/article/user/{id}")
    public ResponseEntity<List<Article>> getUsersArticles (@PathVariable("id") Long userId) {
        List<Article> articles = articleRepository.findAllByUser_Id(userId);
        return ResponseEntity.ok().body(articles);
    }

    @PostMapping("/api/article/write")
    public ResponseEntity<Article> writeArticle (@RequestBody ArticleRequestDto requestDto) {
        Article article = articleService.uploadOrUpdate(requestDto);
        return ResponseEntity.ok().body(article);
    }

    @PutMapping("/api/article/update")
    public ResponseEntity<Article> editArticle (@RequestBody ArticleRequestDto requestDto) {
        Article article = articleService.uploadOrUpdate(requestDto);
        return ResponseEntity.ok().body(article);
    }

    @DeleteMapping("/api/article/{id}/{userId}")
    public ResponseEntity<Long> removeArticle(@PathVariable("userId") Long userId,
                                              @PathVariable("id") Long articleId) {
        articleRepository.deleteById(articleId);
        return ResponseEntity.ok().body(articleId);
    }
}
