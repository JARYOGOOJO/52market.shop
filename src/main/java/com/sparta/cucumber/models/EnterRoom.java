package com.sparta.cucumber.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "enterroom")
@TableGenerator(
        name = "ENTERROOM_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "ENTERROOM_SEQ", allocationSize = 50)
public class EnterRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "ENTERROOM_GENERATOR")
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private ChatRoom room;
}
