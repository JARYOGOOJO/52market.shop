package com.sparta.cucumber.dto;

import lombok.Data;

@Data
public class ChatRequestDto {
    private Long id;
    private Long roomId;
    private Long userId;
    private String roomSubscribeId;
    private String title;
    private String msg;
    private boolean isActive;
}
