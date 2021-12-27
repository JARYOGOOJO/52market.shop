package com.sparta.cucumber.controller;

import com.sparta.cucumber.config.JwtTokenConfigure;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRestControllerTest {

    private MockMvc mockMvc;
    private JwtTokenConfigure jwtTokenConfigure;

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Autowired
    public void setJwtTokenConfigure(JwtTokenConfigure jwtTokenConfigure) {
        this.jwtTokenConfigure = jwtTokenConfigure;
    }

    @Test
    void createAuthenticationTokenByKakao() {
    }

    @Test
    void signup() {
    }

    @Test
    void signin() {
    }

    @Test
    void checkIfExists() {
    }

    @Test
    void whoAmI() {
    }

    @Test
    void whereAmI() {
    }
}