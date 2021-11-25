package com.sparta.cucumber.controller;

import com.sparta.cucumber.dto.ArticleRequestDto;
import com.sparta.cucumber.models.Article;
import com.sparta.cucumber.repository.ArticleRepository;
import com.sparta.cucumber.service.ArticleService;
import com.sparta.cucumber.utils.LocationDistance;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@RestController
public class ArticleRestController {

    public final ArticleRepository articleRepository;
    public final ArticleService articleService;
    public final LocationDistance location;

    @GetMapping("/api/articles")
    public ResponseEntity<List<Article>> getArticles (@RequestParam(value="query") String query) {
        List<Article> articles;
        if (query != null) {
            articles = articleRepository.findAllByTitleContains(query);
        } else {
            articles = articleRepository.findAll();
        }
        return ResponseEntity.ok().body(articles);
    }

    @GetMapping("/api/article/{id}")
    public ResponseEntity<Article> seeDetailOfArticle (@PathVariable("id") Long articleId) {
        Article article = articleRepository
            .findById(articleId)
            .orElseThrow(NullPointerException::new);
        return ResponseEntity.ok().body(article);
    }

    @GetMapping("/api/articles/{lat}/{lng}")
    public ResponseEntity<List<Article>> getAroundArticle (@PathVariable("lat") Float lat,
                                                          @PathVariable("lng") Float lon) {
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
