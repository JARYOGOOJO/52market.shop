package com.sparta.cucumber.chat;

import lombok.Data;

@Data
public class ChatRequestDto {
    private Long userId;
    private Long targetId;
    private String userSubscribeId;
    private String roomSubscribeId;
    private String title;
    private String content;
    private String msgType;
    private boolean isActive;
    private NoticeType type;
}
