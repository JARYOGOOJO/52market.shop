package com.sparta.cucumber.dto;

import com.sparta.cucumber.models.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class JwtResponseDto {

    private final String token;
    private final User user;
}