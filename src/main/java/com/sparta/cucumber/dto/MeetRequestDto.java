package com.sparta.cucumber.dto;

import lombok.*;

@Data
public class MeetRequestDto {
    private Long id;
    private Long commenterId;
    private Long articleId;
}