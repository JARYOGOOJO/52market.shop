package com.sparta.cucumber.dto;

import lombok.Data;

@Data
public class JwtRequestDto {
    private String token;
    private String refreshToken;
    private Long userId;
}
