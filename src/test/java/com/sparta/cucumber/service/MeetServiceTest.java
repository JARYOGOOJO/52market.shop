package com.sparta.cucumber.service;

import com.sparta.cucumber.models.Article;
import com.sparta.cucumber.models.Meet;
import com.sparta.cucumber.models.User;
import com.sparta.cucumber.repository.ArticleRepository;
import com.sparta.cucumber.repository.MeetRepository;
import com.sparta.cucumber.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class MeetServiceTest {
    @Autowired
    private MeetRepository meetRepository;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private UserRepository userRepository;


    @Test
    @Transactional
    void create() {
        User user = User
                .builder()
                .name("test")
                .password("111")
                .phoneNumber("010-1111-2222")
                .email("test@naver.com")
                .latitude(0.0)
                .longitude(0.0)
                .build();

        User commenter = userRepository.save(user);

        User user2 = User
                .builder()
                .name("test2")
                .password("111")
                .phoneNumber("010-1111-2222")
                .email("test2@naver.com")
                .latitude(0.0)
                .longitude(0.0)
                .build();

        User newCommenter = userRepository.save(user2);

        Article article = Article.builder()
                .user(commenter)
                .title("testTitle")
                .content("testContent")
                .image("testImage")
                .latitude(0.0)
                .longitude(0.0)
                .build();

        Article saveArticle = articleRepository.save(article);

        assertThat(newCommenter).isNotEqualTo(article.getUser());
        Meet meet = Meet
                .builder()
                .article(article)
                .commenter(commenter)
                .build();
        Meet saveMeet = meetRepository.save(meet);
        Optional<Meet> findMeet = meetRepository.findById(saveMeet.getId());
        assertThat(saveMeet).isEqualTo(findMeet.get());
    }
}