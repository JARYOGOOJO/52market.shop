package com.sparta.cucumber.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name="comment")
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @Column(name="commentId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;
    @Column
    private String content;
}