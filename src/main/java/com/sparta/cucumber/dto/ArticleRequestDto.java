package com.sparta.cucumber.dto;

import lombok.*;

@Data
public class ArticleRequestDto {
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private String image;
}
