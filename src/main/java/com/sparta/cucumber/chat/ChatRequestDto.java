package com.sparta.cucumber.chat;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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
    @Enumerated(EnumType.STRING)
    private NoticeType type;
}
