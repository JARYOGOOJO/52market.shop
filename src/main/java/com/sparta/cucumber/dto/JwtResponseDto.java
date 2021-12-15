package com.sparta.cucumber.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class JwtResponseDto {

    private final String token;
    private final Long userId;
    private final String userSubscribeId;
}