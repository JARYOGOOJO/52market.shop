package com.sparta.cucumber.chat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NoticeType {

    COMMENT("COMMENT_NOTICE", "댓글", "새로운 댓글이 업로드되었습니다."),
    ARTICLE("ARTICLE_NOTICE", "게시물", "새로운 게시물이 업로드되었습니다."),
    REVIEWS("REVIEWS_NOTICE", "리뷰", "새로운 리뷰가 올라왔어요."),
    CALLING("CALLING_NOTICE", "대화방 초대", "대화 신청이 도착했습니다."),
    MESSAGE("MESSAGE_NOTICE", "메세지", "새 메세지");

    private final String key;
    private final String title;
    private final String message;
}
