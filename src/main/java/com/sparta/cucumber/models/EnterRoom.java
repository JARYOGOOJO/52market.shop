package com.sparta.cucumber.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "enterroom")
@TableGenerator(
        name = "ENTERROOM_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "ENTERROOM_SEQ", allocationSize = 30)
public class EnterRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ENTERROOM_GENERATOR")
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private ChatRoom room;
    @Builder
    public EnterRoom(User user, ChatRoom room) {
        this.user = user;
        this.room = room;
    }
}
