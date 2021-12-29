package com.sparta.cucumber.dto;

import com.sparta.cucumber.models.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class JwtResponseDto {

    private final String token;
    private final String refreshToken;
    private final Long userId;
    private final String userSubscribeId;
    private final String email;

    public JwtResponseDto(String token, User user) {
        this.token = token;
        this.userId = user.getId();
        this.email = user.getEmail();
        this.refreshToken = user.getRefreshToken();
        this.userSubscribeId = user.getSubscribeId();
    }

    public JwtResponseDto(String token, String refresh, User user) {
        this.token = token;
        this.refreshToken = refresh;
        this.userId = user.getId();
        this.email = user.getEmail();
        this.userSubscribeId = user.getSubscribeId();
    }
}