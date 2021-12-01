package com.sparta.cucumber.dto;

import lombok.Data;

@Data
public class ChatRequestDto {
    private Long id;
    private String roomSubscribeId;
    private String title;
    private String msg;
    private boolean isActive;
}
