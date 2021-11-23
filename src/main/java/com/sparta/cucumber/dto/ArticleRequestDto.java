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
    private String address;
    private Float latitude;
    private Float longitude;
}
