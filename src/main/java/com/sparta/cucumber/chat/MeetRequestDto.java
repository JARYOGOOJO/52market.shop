package com.sparta.cucumber.chat;

import lombok.Data;

@Data
public class MeetRequestDto {
    private Long id;
    private Long commenterId;
    private Long articleId;
}