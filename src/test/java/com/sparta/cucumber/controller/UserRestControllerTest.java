package com.sparta.cucumber.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRestControllerTest {

    private MockMvc mockMvc;

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @BeforeAll
    void setup() {

    }

    @Test
    @Order(1)
    @DisplayName("카카오 로그인 실패 테스트, (API 특성상 성공 케이스 X)")
    void createAuthenticationTokenByKakao() throws Exception {
        ResultActions result = mockMvc.perform(
                post("/user/kakao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"token\":\"zSP3D2KTZD0M_xcX5mgj1S1G2D_tcHg-IdQufQo9c00AAAF9-eIv4A\"}")
        );

        result.andDo(print())
                .andExpect(status().isUnauthorized())
                .andExpect(handler().handlerType(UserRestController.class))
                .andExpect(handler().methodName("createAuthenticationTokenByKakao"))
                .andExpect(jsonPath("$.code").exists())
                .andExpect(jsonPath("$.code", is("UNAUTHORIZED_MEMBER")))
                .andExpect(jsonPath("$.error").exists())
                .andExpect(jsonPath("$.error", is("UNAUTHORIZED")))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message", is("현재 내 계정 정보가 존재하지 않습니다.")))
                .andExpect(jsonPath("$.status").exists())
                .andExpect(jsonPath("$.status").isNumber())
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.timestamp").isNotEmpty())
        ;
    }

    @Test
    @Order(2)
    @DisplayName("회원 로그인 실패 테스트")
    void signinFailureTest() throws Exception {
        ResultActions result = mockMvc.perform(
                post("/user/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"ace@never.die\",\"password\":\"rew748596\"}")
        );

        result.andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(handler().handlerType(UserRestController.class))
                .andExpect(handler().methodName("signin"))
                .andExpect(jsonPath("$.code").exists())
                .andExpect(jsonPath("$.code", is("USER_NOT_FOUND")))
                .andExpect(jsonPath("$.error").exists())
                .andExpect(jsonPath("$.error", is("NOT_FOUND")))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message", is("해당 유저 정보를 찾을 수 없습니다.")))
                .andExpect(jsonPath("$.status").exists())
                .andExpect(jsonPath("$.status").isNumber())
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.timestamp").isNotEmpty())
        ;
    }

    @Test
    @Order(3)
    @DisplayName("회원가입 성공 테스트")
    void signup() throws Exception {
        ResultActions result = mockMvc.perform(
                post("/user/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"monica@street.dancer\",\"name\":\"MONICA\",\"phoneNumber\":\"010-1234-5678\",\"password\":\"rew12345678\"}")
        );
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(UserRestController.class))
                .andExpect(handler().methodName("signup"))
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.token").isString())
                .andExpect(jsonPath("$.refreshToken").exists())
                .andExpect(jsonPath("$.refreshToken").isString())
                .andExpect(jsonPath("$.userSubscribeId").exists())
                .andExpect(jsonPath("$.userSubscribeId").isString())
                .andExpect(jsonPath("$.userId").exists())
                .andExpect(jsonPath("$.userId").isNumber())
        ;
    }

    @Test
    @Order(4)
    @DisplayName("회원 로그인 성공 테스트")
    void signin() throws Exception {
        ResultActions result = mockMvc.perform(
                post("/user/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"monica@street.dancer\",\"password\":\"rew12345678\"}")
        );
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(UserRestController.class))
                .andExpect(handler().methodName("signin"))
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.token").isString())
                .andExpect(jsonPath("$.refreshToken").exists())
                .andExpect(jsonPath("$.refreshToken").isString())
                .andExpect(jsonPath("$.userSubscribeId").exists())
                .andExpect(jsonPath("$.userSubscribeId").isString())
                .andExpect(jsonPath("$.userId").exists())
                .andExpect(jsonPath("$.userId").isNumber())
        ;
    }

    @Test
    @DisplayName("동일한 이름의 유저 존재 여부 확인, (항상 Boolean 값 리턴)")
    void checkIfExists() throws Exception {
        ResultActions result = mockMvc.perform(
                post("/user/exists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"MONICA\"}")
        );
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(UserRestController.class))
                .andExpect(handler().methodName("checkIfExists"))
                .andExpect(jsonPath("$").isBoolean())
        ;
    }

    @Test
    @Order(5)
    @DisplayName("유저 위치 확인 및 갱신 ")
    void whereAmI() throws Exception {
        ResultActions result = mockMvc.perform(
                post("/user/location")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"monica@street.dancer\", \"latitude\":127.087, \"longitude\":37.592}")
        );
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(UserRestController.class))
                .andExpect(handler().methodName("whereAmI"))
                .andExpect(jsonPath("$.createdAt").exists())
                .andExpect(jsonPath("$.modifiedAt").exists())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.name").isString())
                .andExpect(jsonPath("$.email").exists())
                .andExpect(jsonPath("$.email").isString())
                .andExpect(jsonPath("$.subscribeId").exists())
                .andExpect(jsonPath("$.subscribeId").isString())
                .andExpect(jsonPath("$.role").exists())
                .andExpect(jsonPath("$.role").isString())
        ;
    }
}