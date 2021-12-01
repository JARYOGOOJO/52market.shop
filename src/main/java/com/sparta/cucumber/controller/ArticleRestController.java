package com.sparta.cucumber.controller;

import com.sparta.cucumber.dto.ArticleRequestDto;
import com.sparta.cucumber.models.Article;
import com.sparta.cucumber.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ArticleRestController {

    public final ArticleService articleService;

    @GetMapping("/api/articles/{query}")
    public ResponseEntity<List<Article>> getArticles(@PathVariable("query") String query) {
        List<Article> articles = articleService.getArticles(query);
        return ResponseEntity.ok().body(articles);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleService.getAllArticles();
        return ResponseEntity.ok().body(articles);
    }

    @GetMapping("/api/article/{id}")
    public ResponseEntity<Article> seeDetailOfArticle(@PathVariable("id") Long articleId) {
        Article article = articleService.seeDetailOfArticle(articleId);
        return ResponseEntity.ok().body(article);
    }

    @GetMapping("/api/articles/{lat}/{lng}")
    public ResponseEntity<List<Article>> getAroundArticle (@PathVariable("lat") Double lat,
                                                          @PathVariable("lng") Double lon) {
        List<Article> articles = articleService.getAroundArticle(lat, lon);
        return ResponseEntity.ok().body(articles);
    }

    @GetMapping("/api/article/user/{id}")
    public ResponseEntity<List<Article>> getUsersArticles (@PathVariable("id") Long userId) {
        List<Article> articles = articleService.getUsersArticles(userId);
        return ResponseEntity.ok().body(articles);
    }

//    @PostMapping("/api/article/write")
//    public ResponseEntity<Article> writeArticle (@RequestBody ArticleRequestDto requestDto) {
//        Article article = articleService.uploadOrUpdate(requestDto);
//        return ResponseEntity.ok().body(article);
//    }

    @PutMapping("/api/article/update")
    public ResponseEntity<Article> editArticle (@ModelAttribute ArticleRequestDto requestDto,
                                                @ModelAttribute MultipartFile file) throws IOException {
        Article article = articleService.uploadOrUpdate(requestDto, file);
        return ResponseEntity.ok().body(article);
    }

    @DeleteMapping("/api/article/{id}/{userId}")
    public ResponseEntity<Long> removeArticle(@PathVariable("userId") Long userId,
                                              @PathVariable("id") Long articleId) {
        Long id = articleService.removeArticle(userId, articleId);
        return ResponseEntity.ok().body(id);
    }


    @PostMapping("/api/article/write")
    public ResponseEntity<Article> writeArticle (@ModelAttribute ArticleRequestDto requestDto,
                                                 @ModelAttribute MultipartFile file) throws IOException {
        Article article = articleService.uploadOrUpdate(requestDto, file);
        return ResponseEntity.ok().body(article);
    }
}
