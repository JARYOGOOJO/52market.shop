package com.sparta.cucumber.controller;

import com.sparta.cucumber.dto.UserRequestDto;
import com.sparta.cucumber.models.User;
import com.sparta.cucumber.repository.ArticleRepository;
import com.sparta.cucumber.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.File;
import java.io.FileInputStream;

import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ArticleRestControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArticleRepository articleRepository;
    private Long userId;
    private Long articleId;

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @BeforeAll
    void set() {
        User user = User.builder()
                .email("monica@street.dancer")
                .name("MONICA")
                .build();
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setLatitude(37.49718785602991);
        userRequestDto.setLongitude(127.03832333222415);
        user.updateLocation(userRequestDto);
        userRepository.save(user);
        userId = user.getId();
    }

    @AfterAll
    void teardown() {
        userRepository.findByEmail("monica@street.dancer").ifPresent(userRepository::delete);
    }

    @Test
    @Order(1)
    @DisplayName("게시물 작성하기")
    void writeArticle() throws Exception {
        File f = new File("src/test/resources/images/cat_kkori.jpg");
        System.out.println(f.isFile() + " " + f.getName() + " " + f.exists());
        FileInputStream fis = new FileInputStream(f);
        MockMultipartFile mmf = new MockMultipartFile(
                "file",
                f.getName(),
                MediaType.MULTIPART_FORM_DATA.toString(),
                fis);
        ResultActions result = mockMvc.perform(
                multipart("/api/articles")
                        .file(mmf)
                        .param("title", "꼬리짱")
                        .param("content", "개귀엽쥬")
                        .param("userId", String.valueOf(userId))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .characterEncoding("UTF-8")
        );
        articleId = articleRepository.findByTitleAndContent("꼬리짱", "개귀엽쥬").getId();
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(ArticleRestController.class))
                .andExpect(handler().methodName("writeArticle"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id", is(Math.toIntExact(articleId))))
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.title").isString())
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.content").isString())
                .andExpect(jsonPath("$.imagePath").exists())
                .andExpect(jsonPath("$.imagePath").isString())
                .andExpect(jsonPath("$.imageName").exists())
                .andExpect(jsonPath("$.imageName").isString())
                .andExpect(jsonPath("$.commentList").exists())
                .andExpect(jsonPath("$.commentList").isArray())
                .andExpect(jsonPath("$.user").exists())
                .andExpect(jsonPath("$.user").isMap())
        ;
    }

    @Test
    @Order(2)
    @DisplayName("모든 게시물 불러오기")
    public void getAllArticles() throws Exception {
        ResultActions result = mockMvc.perform(
                get("/api/articles")
                        .param("page", "1")
                        .accept(MediaType.APPLICATION_JSON)
        );
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(ArticleRestController.class))
                .andExpect(handler().methodName("getAllArticles"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].title").exists())
                .andExpect(jsonPath("$[0].title").isString())
                .andExpect(jsonPath("$[0].content").exists())
                .andExpect(jsonPath("$[0].content").isString())
                .andExpect(jsonPath("$[0].imagePath").exists())
                .andExpect(jsonPath("$[0].imagePath").isString())
                .andExpect(jsonPath("$[0].imageName").exists())
                .andExpect(jsonPath("$[0].imageName").isString())
                .andExpect(jsonPath("$[0].commentList").exists())
                .andExpect(jsonPath("$[0].commentList").isArray())
                .andExpect(jsonPath("$[0].user").exists())
                .andExpect(jsonPath("$[0].user").isMap())
        ;
    }

    @Test
    @Order(4)
    @DisplayName("게시물 상세보기")
    void seeDetailOfArticle() throws Exception {
        ResultActions result = mockMvc.perform(
                get("/api/article/" + articleId)
                        .accept(MediaType.APPLICATION_JSON)
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(ArticleRestController.class))
                .andExpect(handler().methodName("seeDetailOfArticle"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id", is(Math.toIntExact(articleId))))
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.title").isString())
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.content").isString())
                .andExpect(jsonPath("$.imagePath").exists())
                .andExpect(jsonPath("$.imagePath").isString())
                .andExpect(jsonPath("$.imageName").exists())
                .andExpect(jsonPath("$.imageName").isString())
                .andExpect(jsonPath("$.commentList").exists())
                .andExpect(jsonPath("$.commentList").isArray())
                .andExpect(jsonPath("$.user").exists())
                .andExpect(jsonPath("$.user").isMap())
        ;
    }

    @Test
    @Order(3)
    @DisplayName("근처 게시물 불러오기")
    void getAroundArticle() throws Exception {
        ResultActions result = mockMvc.perform(
                get("/api/articles/37.501086/127.0365988")
                        .accept(MediaType.APPLICATION_JSON)
        );
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(ArticleRestController.class))
                .andExpect(handler().methodName("getAroundArticle"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].title").exists())
                .andExpect(jsonPath("$[0].title").isString())
                .andExpect(jsonPath("$[0].content").exists())
                .andExpect(jsonPath("$[0].content").isString())
                .andExpect(jsonPath("$[0].imagePath").exists())
                .andExpect(jsonPath("$[0].imagePath").isString())
                .andExpect(jsonPath("$[0].imageName").exists())
                .andExpect(jsonPath("$[0].imageName").isString())
                .andExpect(jsonPath("$[0].commentList").exists())
                .andExpect(jsonPath("$[0].commentList").isArray())
                .andExpect(jsonPath("$[0].user").exists())
                .andExpect(jsonPath("$[0].user").isMap())
        ;
    }

    @Test
    @Order(4)
    @DisplayName("게시물 수정하기")
    void editArticle() throws Exception {
        ResultActions result = mockMvc.perform(
                put("/api/article")
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"userId\": " + userId + ", \"id\": " + articleId + ", \"content\": \"세상에서 제일 이쁘다. " + RandomStringUtils.randomAlphabetic(4) + "\"}")
                        .contentType(MediaType.APPLICATION_JSON)
        );
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(ArticleRestController.class))
                .andExpect(handler().methodName("editArticle"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id", is(Math.toIntExact(articleId))))
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.title").isString())
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.content").isString())
                .andExpect(jsonPath("$.imagePath").exists())
                .andExpect(jsonPath("$.imagePath").isString())
                .andExpect(jsonPath("$.imageName").exists())
                .andExpect(jsonPath("$.imageName").isString())
                .andExpect(jsonPath("$.commentList").exists())
                .andExpect(jsonPath("$.commentList").isArray())
                .andExpect(jsonPath("$.user").exists())
                .andExpect(jsonPath("$.user").isMap())
        ;
    }

    @Test
    @Order(5)
    @DisplayName("게시물 삭제하기")
    void removeArticle() throws Exception {
        ResultActions result = mockMvc.perform(
                delete("/api/article/" + articleId)
                        .accept(MediaType.APPLICATION_JSON)
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(ArticleRestController.class))
                .andExpect(handler().methodName("removeArticle"))
                .andExpect(jsonPath("$").isNumber());
    }
}