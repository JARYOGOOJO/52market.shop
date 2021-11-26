package com.sparta.cucumber.models;

import com.sparta.cucumber.repository.ArticleRepository;
import com.sparta.cucumber.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class ArticleTest {

    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    UserRepository userRepository;

//    @AfterEach
//    void tearDown() {
//        articleRepository.deleteAll();
//        userRepository.deleteAll();
//    }

    @Test
    @DisplayName("글 작성 후 조회하기")
    public void getArticle() {
        String title = "타이틀";
        String content = "컨텐츠컨텐츠컨텐츠컨텐츠컨텐츠컨텐츠컨텐츠컨텐츠";
        String image = "https://img1.daumcdn.net/thumb/C428x428/?scode=mtistory2&fname=https%3A%2F%2Ftistory4.daumcdn.net%2Ftistory%2F3962924%2Fattach%2F3a33fb3fbfb04050bd15efddb834038f";
        User user1 = User
                .builder()
                .name("유동민")
                .email("ydm2790@naver.com")
                .picture(image)
                .build();
        userRepository.save(user1);
        Article article = Article
                .builder()
                .title(title)
                .content(content)
                .image(image)
                .user(user1)
                .latitude(38.592252158153975)
                .longitude(128.08723572001708)
                .build();
        articleRepository.save(article);

        List<Article> articleList = articleRepository.findAll();
        System.out.println(articleList.get(0));
        System.out.println(article);
    }
}