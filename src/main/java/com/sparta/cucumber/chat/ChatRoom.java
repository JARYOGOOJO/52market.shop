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
@Entity(name = "chat_room")
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
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn
    private List<Notice> message_list;
    private boolean isActive;

    @Builder
    public ChatRoom(User host, String title) {
        this.roomId = RandomStringUtils.random(16, true, true);
        this.title = title;
        this.host = host;
        this.message_list = new ArrayList<>();
        this.isActive = true;
    }

    public ChatRoom enter(User user) {
        this.guest = user;
        this.isActive = true;
        return this;
    }

    public void exit(User user) {
        this.message_list.clear();
        this.isActive = false;
    }

    public void talk(Notice msg) {
        this.message_list.add(msg);
    }
}
