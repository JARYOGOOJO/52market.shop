package com.sparta.cucumber.service;

import com.sparta.cucumber.dto.ArticleRequestDto;
import com.sparta.cucumber.models.Article;
import com.sparta.cucumber.models.User;
import com.sparta.cucumber.repository.ArticleRepository;
import com.sparta.cucumber.repository.UserRepository;
import com.sparta.cucumber.utils.LocationDistance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    public final LocationDistance location;

//    @Transactional
//    public Article uploadOrUpdate(ArticleRequestDto requestDto) {
//        Long userId = requestDto.getUserId();
//        User user = userRepository
//                .findById(userId)
//                .orElseThrow(
//                        () -> new NullPointerException("잘못된 접근입니다."));
//        Article article = Article.builder()
//                .user(user)
//                .title(requestDto.getTitle())
//                .content(requestDto.getContent())
//                .image(requestDto.getImage())
//                .latitude(user.getLatitude())
//                .longitude(user.getLongitude())
//                .build();
//        return articleRepository.save(article);
//    }

    public List<Article> getArticles(String query) {
        return articleRepository.findAllByTitleContains(query);
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article seeDetailOfArticle(Long articleId) {
        return articleRepository
                .findById(articleId)
                .orElseThrow(NullPointerException::new);
    }

    public List<Article> getAroundArticle(Double lat, Double lon) {
        return articleRepository
                .findAll()
                .stream()
                .filter(article -> {
                    double dist = location.distance(
                            lat, lon,
                            article.getLatitude(), article.getLongitude(),
                            "meter");
                    return dist < 1000;
                }).collect(Collectors.toList());
    }

    public List<Article> getUsersArticles(@PathVariable("id") Long userId) {
        return articleRepository.findAllByUser_Id(userId);
    }


    public Long removeArticle(Long userId, Long articleId) {
        Article article = articleRepository
                .findById(articleId)
                .orElseThrow(
                        () -> new NullPointerException("게시물이 존재하지 않습니다."));
        if (Objects.equals(article.getUser().getId(), userId)) {
            articleRepository.deleteById(articleId);
        }
        return articleId;
    }

    @Transactional
    public Article uploadOrUpdate(ArticleRequestDto requestDto, MultipartFile file) throws IOException {
        Long userId = requestDto.getUserId();
        User user = userRepository
                .findById(userId)
                .orElseThrow(
                        () -> new NullPointerException("잘못된 접근입니다."));

        // 경로지정
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        // 랜덤 식별자 생성
        UUID uuid = UUID.randomUUID();

        // uuid_원래파일명
        String fileName = uuid + "_" + file.getOriginalFilename();
        // 빈 파일 생성
        File saveFile = new File(projectPath, fileName);
        // 업로드 된 파일 저장
        file.transferTo(saveFile);

        Article article = Article.builder()
                .user(user)
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .image(requestDto.getImage())
                .latitude(user.getLatitude())
                .longitude(user.getLongitude())
                .fileName(requestDto.getFileName())
                .filePath(requestDto.getFilePath())
                .build();

        return articleRepository.save(article);
    }
}
