package com.sparta.cucumber.chat;

import com.sparta.cucumber.models.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String destination;
    private String content;
    @ManyToOne
    @JoinColumn(name = "writer_id")
    private User writer;
    private NoticeType type;

    @Builder
    public Notice(String destination, String content, User writer, NoticeType type) {
        this.content = content;
        this.destination = destination;
        this.writer = writer;
        this.type = type;
    }
}
