package com.sparta.cucumber.chat;

import com.sparta.cucumber.models.Timestamped;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import static org.springframework.web.util.HtmlUtils.htmlEscape;

@Getter
@Entity(name = "message")
@NoArgsConstructor
public class Notice extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Long senderId;
    private Long subscriberId;
    private NoticeType type;
    private boolean isRead;

    @Builder
    public Notice(String content, Long senderId, Long subscriberId, NoticeType type) {
        this.content = htmlEscape(content);
        this.senderId = senderId;
        this.subscriberId = subscriberId;
        this.type = type;
        this.isRead = false;
    }
}
