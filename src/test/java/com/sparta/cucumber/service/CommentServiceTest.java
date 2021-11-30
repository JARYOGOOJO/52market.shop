package com.sparta.cucumber.service;

import com.sparta.cucumber.models.Article;
import com.sparta.cucumber.models.Comment;
import com.sparta.cucumber.models.User;
import com.sparta.cucumber.repository.ArticleRepository;
import com.sparta.cucumber.repository.CommentRepository;
import com.sparta.cucumber.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CommentServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    @Transactional
    void uploadOrUpdate() {

        User user = User
                .builder()
                .name("test")
                .password("111")
                .phoneNumber("010-1111-2222")
                .email("test@naver.com")
                .latitude(0.0)
                .longitude(0.0)
                .build();
        User saveUser = userRepository.save(user);

        Article article = Article.builder()
                .user(saveUser)
                .title("testTitle")
                .content("testContent")
                .image("testImage")
                .latitude(0.0)
                .longitude(0.0)
                .build();

        Article saveArticle = articleRepository.save(article);
        Comment comment = Comment
                .builder()
                .content("testComment")
                .user(saveUser)
                .article(saveArticle)
                .build();

        Comment saveComment = commentRepository.save(comment);
        Optional<Comment> findComment = commentRepository.findById(saveComment.getId());
        Assertions.assertThat(saveComment).isEqualTo(findComment.get());
    }
}