package com.sparta.cucumber.dto;

import lombok.Data;

@Data
public class ArticleRequestDto {
    private Long userId;
    private String title;
    private String content;
}
