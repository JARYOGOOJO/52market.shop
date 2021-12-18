package com.sparta.cucumber.chat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NoticeType {

    COMMENT("COMMENT_NOTICE", "댓글"),
    ARTICLE("ARTICLE_NOTICE", "게시물"),
    REVIEWS("REVIEWS_NOTICE", "리뷰"),
    CALLING("CALLING_NOTICE", "대화방 초대"),
    MESSAGE("MESSAGE_NOTICE", "메세지");

    private final String key;
    private final String title;
}
