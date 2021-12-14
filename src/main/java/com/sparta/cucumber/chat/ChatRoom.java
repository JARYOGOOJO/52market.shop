package com.sparta.cucumber.chat;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity(name = "chatroom")
@TableGenerator(
        name = "CHATROOM_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "CHATROOM_SEQ", allocationSize = 30)
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "CHATROOM_GENERATOR")
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
