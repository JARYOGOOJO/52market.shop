package com.sparta.cucumber.chat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoticeTest {

    private final String title = "새로운 대화 요청";
    private final String content = "안녕하세요..";
    private final Long senderId = 1L;
    private final Long targetId = 2L;
    private final Long subscriberId = 2L;
    private final NoticeType type = NoticeType.MESSAGE;
    private final boolean isRead = false;

    @Test
    @DisplayName("알림 타이틀")
    void getTitle() {
        Notice notice = Notice.builder()
                .title(title)
                .content(content)
                .senderId(senderId)
                .targetId(targetId)
                .subscriberId(subscriberId)
                .type(type)
                .build();
        System.out.println(notice.toString());
        assertEquals(notice.getTitle(), title);
    }

    @Test
    @DisplayName("알림 컨텐츠")
    void getContent() {
        Notice notice = Notice.builder()
                .title(title)
                .content(content)
                .senderId(senderId)
                .targetId(targetId)
                .subscriberId(subscriberId)
                .type(type)
                .build();
        System.out.println(notice.toString());
        assertEquals(notice.getContent(), content);
    }

    @Test
    @DisplayName("알림 발신자 아이디")
    void getSenderId() {
        Notice notice = Notice.builder()
                .title(title)
                .content(content)
                .senderId(senderId)
                .targetId(targetId)
                .subscriberId(subscriberId)
                .type(type)
                .build();
        System.out.println(notice.toString());
        assertEquals(notice.getSenderId(), senderId);
    }

    @Test
    @DisplayName("알림 타겟 아이디")
    void getTargetId() {
        Notice notice = Notice.builder()
                .title(title)
                .content(content)
                .senderId(senderId)
                .targetId(targetId)
                .subscriberId(subscriberId)
                .type(type)
                .build();
        System.out.println(notice.toString());
        assertEquals(notice.getTargetId(), targetId);
    }

    @Test
    @DisplayName("알림 상대방 구독 아이디 (deprecated)")
    void getSubscriberId() {
        Notice notice = Notice.builder()
                .title(title)
                .content(content)
                .senderId(senderId)
                .targetId(targetId)
                .subscriberId(subscriberId)
                .type(type)
                .build();
        System.out.println(notice.toString());
        assertEquals(notice.getSubscriberId(), subscriberId);
    }

    @Test
    @DisplayName("알림 타입")
    void getType() {
        Notice notice = Notice.builder()
                .title(title)
                .content(content)
                .senderId(senderId)
                .targetId(targetId)
                .subscriberId(subscriberId)
                .type(type)
                .build();
        System.out.println(notice.getType().getMessage());
        assertEquals(notice.getType(), type);
    }

    @Test
    @DisplayName("알림 읽음 확인")
    void isRead() {
        Notice notice = Notice.builder()
                .title(title)
                .content(content)
                .senderId(senderId)
                .targetId(targetId)
                .subscriberId(subscriberId)
                .type(type)
                .build();
        System.out.println(notice.toString());
        assertFalse(notice.isRead());
        notice.read();
        assertTrue(notice.isRead());
    }

    @Test
    @DisplayName("값이 같은 두 알림 비교 (False)")
    void builder() {
        Notice notice = Notice.builder()
                .title(title)
                .content(content)
                .senderId(senderId)
                .targetId(targetId)
                .subscriberId(subscriberId)
                .type(type)
                .build();
        System.out.println(notice.toString());
        String title = "새로운 대화 요청";
        String content = "안녕하세요..";
        Long senderId = 1L;
        Long targetId = 2L;
        Long subscriberId = 2L;
        NoticeType type = NoticeType.MESSAGE;
        Notice notice1 = Notice.builder()
                .title(title)
                .content(content)
                .senderId(senderId)
                .targetId(targetId)
                .subscriberId(subscriberId)
                .type(type)
                .build();
        System.out.println(notice1.toString());
        assertNotEquals(notice, notice1);
    }
}