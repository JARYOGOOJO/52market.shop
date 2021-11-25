package com.sparta.cucumber.dto;

import lombok.*;

@Data
public class ReviewRequestDto {
    private Long reviewUserid;
    private Long reviewTargetUserId;
    private Integer score;
    private String content;
}
