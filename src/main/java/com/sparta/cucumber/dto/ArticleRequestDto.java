package com.sparta.cucumber.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class ArticleRequestDto {
    private Long userId;
    private String title;
    private String content;
    private String image;
}
