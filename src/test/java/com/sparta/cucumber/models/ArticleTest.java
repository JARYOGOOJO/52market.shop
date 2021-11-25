package com.sparta.cucumber.models;

import com.sparta.cucumber.repository.ArticleRepository;
import com.sparta.cucumber.user.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.sparta.cucumber.user.User;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ArticleTest {

    @Autowired
    ArticleRepository articleRepository;

    @AfterEach
    void tearDown() {
        articleRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성 후 조회하기")
    public void getArticle() {
        String title = "타이틀";
        String content = "컨텐츠컨텐츠컨텐츠컨텐츠컨텐츠컨텐츠컨텐츠컨텐츠";
        User user = User
                .builder()
                .name("유동민")
                .build();
        Article article = Article
                .builder()
                .title(title)
                .content(content)
                .user(user)
                .build();
        articleRepository.save(article);

        List<Article> articleList = articleRepository.findAll();
        assertEquals(articleList.get(0), article);
    }
}