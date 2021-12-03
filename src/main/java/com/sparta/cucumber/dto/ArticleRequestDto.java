package com.sparta.cucumber.dto;

import lombok.Data;

@Data
public class ArticleRequestDto {
    private Long userId;
    private String title;
    private String content;
    private String image;

    private String fileName;
    private String filePath;
}
