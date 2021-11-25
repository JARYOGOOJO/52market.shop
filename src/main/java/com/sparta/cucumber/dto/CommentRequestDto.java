package com.sparta.cucumber.dto;

import lombok.*;

@Data
public class CommentRequestDto {
    private Long userId;
    private Long articleId;
    private String content;
}
