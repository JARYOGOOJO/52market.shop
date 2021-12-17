package com.sparta.cucumber.chat;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity(name = "chatroom")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomSubscribeId;
    private String title;
    private boolean isActive;

    @Builder
    public ChatRoom(String title, boolean isActive) {
        this.roomSubscribeId = UUID.randomUUID().toString();
        this.title = title;
        this.isActive = isActive;
    }
}
