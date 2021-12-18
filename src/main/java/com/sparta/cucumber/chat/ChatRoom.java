package com.sparta.cucumber.chat;

import com.sparta.cucumber.models.Timestamped;
import com.sparta.cucumber.models.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "chatroom")
public class ChatRoom extends Timestamped {
    @Id
    private String roomId;
    private String title;
    @ManyToOne
    @JoinColumn(name = "host")
    private User host;
    @ManyToOne
    @JoinColumn(name = "guest")
    private User guest;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "messages")
    private List<Notice> messages;
    private boolean isActive;

    @Builder
    public ChatRoom(User host, String title) {
        this.roomId = RandomStringUtils.random(8);
        this.title = title;
        this.host = host;
        this.messages = new ArrayList<>();
        this.isActive = true;
    }

    public ChatRoom enter(User user) {
        this.guest = user;
        this.isActive = true;
        return this;
    }

    public ChatRoom exit(User user) {
        this.messages.clear();
        this.isActive = false;
        return this;
    }

    public void talk(Notice msg) {
        this.messages.add(msg);
    }
}
