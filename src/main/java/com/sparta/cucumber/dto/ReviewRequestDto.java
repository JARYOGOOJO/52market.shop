package com.sparta.cucumber.dto;

import lombok.*;

@Data
public class ReviewRequestDto {
    private Long userId;
    private String title;
    private String content;
}
