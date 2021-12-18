package com.sparta.cucumber.chat;

import lombok.Data;

@Data
public class ChatRequestDto {
    private Long userId;
    private String roomSubscribeId;
    private String title;
    private String content;
    private String userSubscribeId;
    private boolean isActive;
}
