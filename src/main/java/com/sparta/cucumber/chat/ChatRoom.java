package com.sparta.cucumber.chat;

import com.sparta.cucumber.models.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "chatroom")
public class ChatRoom {
    @Id
    private String roomId;
    private String title;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> members;
    private boolean isActive;

    @Builder
    public ChatRoom(String roomSubscribeId, String title) {
        this.roomId = roomSubscribeId;
        this.title = title;
        this.isActive = true;
    }

    public ChatRoom enter(User user) {
        this.members.add(user);
        return this;
    }

    public ChatRoom exit(User user) {
        this.members.remove(user);
        return this;
    }
}
