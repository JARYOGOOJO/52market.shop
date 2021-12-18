package com.sparta.cucumber.chat;

import lombok.Data;

@Data
public class ChatRequestDto {
    private Long userId;
    private String userSubscribeId;
    private String roomSubscribeId;
    private String title;
    private String content;
    private boolean isActive;
}
