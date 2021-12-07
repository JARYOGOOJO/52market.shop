package com.sparta.cucumber.controller;

import com.sparta.cucumber.dto.ArticleRequestDto;
import com.sparta.cucumber.models.Article;
import com.sparta.cucumber.service.ArticleService;
import com.sparta.cucumber.service.S3Uploader;
import io.swagger.v3.oas.annotations.Operation;
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
    public final S3Uploader s3Uploader;
    
    @Operation(description = "게시글 검색",method = "GET")
    @GetMapping("/api/articles/{query}")
    public ResponseEntity<List<Article>> getArticles(@PathVariable("query") String query) {
        List<Article> articles = articleService.getArticles(query);
        return ResponseEntity.ok().body(articles);
    }

    @Operation(description = "모든 게시글 가져오기",method = "GET")
    @GetMapping("/api/articles")
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleService.getAllArticles();
        return ResponseEntity.ok().body(articles);
    }

    @Operation(description = "게시글 id로 가져오기",method = "GET")
    @GetMapping("/api/article/{id}")
    public ResponseEntity<Article> seeDetailOfArticle(@PathVariable("id") Long articleId) {
        Article article = articleService.seeDetailOfArticle(articleId);
        return ResponseEntity.ok().body(article);
    }

    @Operation(description = "자신주변 게시글 가져오기",method = "GET")
    @GetMapping("/api/articles/{lat}/{lng}")
    public ResponseEntity<List<Article>> getAroundArticle (@PathVariable("lat") Double lat,
                                                          @PathVariable("lng") Double lon) {
        List<Article> articles = articleService.getAroundArticle(lat, lon);
        return ResponseEntity.ok().body(articles);
    }

    @Operation(description = "유저 게시글 가져오기",method = "GET")
    @GetMapping("/api/article/user/{id}")
    public ResponseEntity<List<Article>> getUsersArticles (@PathVariable("id") Long userId) {
        List<Article> articles = articleService.getUsersArticles(userId);
        return ResponseEntity.ok().body(articles);
    }

    @Operation(description = "게시글 작성",method = "POST")
    @PostMapping("/api/article/write")
    public ResponseEntity<Article> writeArticle(@ModelAttribute ArticleRequestDto requestDto,
                                                @ModelAttribute MultipartFile file) throws IOException {
        String imagePath = s3Uploader.upload(file, "Article");
        Article article = articleService.upload(requestDto, imagePath);
        return ResponseEntity.ok().body(article);
    }

    @Operation(description = "게시글 편집",method = "PUT")
    @PutMapping("/api/article/edit")
    public ResponseEntity<Article> editArticle(@RequestBody ArticleRequestDto requestDto) {
        Article article = articleService.update(requestDto);
        return ResponseEntity.ok().body(article);
    }

    @Operation(description = "게시글 삭제",method = "DELETE")
    @DeleteMapping("/api/article/{id}/{userId}")
    public ResponseEntity<Long> removeArticle(@PathVariable("userId") Long userId,
                                              @PathVariable("id") Long articleId) {
        Long id = articleService.removeArticle(userId, articleId);
        s3Uploader.deleteImage(articleId);
        return ResponseEntity.ok().body(id);
    }

}
