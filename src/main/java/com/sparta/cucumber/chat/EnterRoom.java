package com.sparta.cucumber.chat;

import com.sparta.cucumber.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "enterroom")
public class EnterRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private ChatRoom room;

    public EnterRoom(User user, ChatRoom room) {
        this.user = user;
        this.room = room;
    }
}
