package com.sparta.cucumber.chat;

import com.sparta.cucumber.models.Timestamped;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name = "message")
@NoArgsConstructor
public class Notice extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private Long senderId;
    private Long targetId;
    private Long subscriberId;
    @Enumerated(EnumType.STRING)
    private NoticeType type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private ChatRoom chatRoom;
    private boolean isRead;

    @Builder
    public Notice(ChatRoom chatRoom, String title, String content, Long senderId, Long targetId, Long subscriberId, NoticeType type) {
        this.targetId = targetId;
        this.title = title;
        this.content = content;
        this.senderId = senderId;
        this.subscriberId = subscriberId;
        this.chatRoom = chatRoom;
        this.type = type;
        this.isRead = false;
    }

    public void read() {
        this.isRead = true;
    }
}
