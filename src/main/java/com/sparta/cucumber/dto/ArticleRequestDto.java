package com.sparta.cucumber.dto;

import lombok.*;

@Getter
@NoArgsConstructor
public class ArticleRequestDto {
    private Long userId;
    private String title;
    private String content;
    private String image;
}
