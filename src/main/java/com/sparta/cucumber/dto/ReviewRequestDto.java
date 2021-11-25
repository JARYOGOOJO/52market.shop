package com.sparta.cucumber.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDto {
    private Long reviewUserid;
    private Long reviewTargetUserId;
    private Integer score;
    private String content;
}
