package com.sparta.cucumber.chat;

import com.sparta.cucumber.models.Timestamped;
import com.sparta.cucumber.models.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "chat_room")
public class ChatRoom extends Timestamped {
    @Id
    @Column(name = "room_subscribe_id")
    private String roomSubscribeId;
    private String title;
    @ManyToOne
    @JoinColumn(name = "host")
    private User host;
    @ManyToOne
    @JoinColumn(name = "guest")
    private User guest;
    private boolean isActive;

    @Builder
    public ChatRoom(User host, String title) {
        this.roomSubscribeId = RandomStringUtils.random(16, true, true);
        this.title = title;
        this.host = host;
        this.isActive = true;
    }

    public ChatRoom enter(User guest) {
        if (this.host == guest) {
            return this;
        }
        this.guest = guest;
        this.isActive = true;
        return this;
    }

    public void exit(User user) {
        if (user == this.host) {
            this.host = null;
            this.isActive = false;
        } else if (user == this.guest) {
            this.guest = null;
        }
    }
}
