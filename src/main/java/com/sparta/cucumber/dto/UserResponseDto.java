package com.sparta.cucumber.dto;

import lombok.Data;

@Data
public class UserResponseDto {
    private String name;
    private String email;
    private String picture;
}