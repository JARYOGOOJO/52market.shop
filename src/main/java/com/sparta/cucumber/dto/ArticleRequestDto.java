package com.sparta.cucumber.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequestDto {
    private Long userId;
    private String title;
    private String content;
    private String image;
    private String address;
    private Float latitude;
    private Float longitude;
}
