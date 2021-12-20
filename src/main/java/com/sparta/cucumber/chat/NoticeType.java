package com.sparta.cucumber.chat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NoticeType {

    COMMENT("COMMENT_UPLOAD", "댓글", "새로운 댓글이 업로드되었습니다."),
    DEL_CMT("COMMENT_UPLOAD", "댓글", "댓글이 삭제되었습니다."),
    ARTICLE("ARTICLE_UPLOAD", "게시물", "새로운 게시물이 업로드되었습니다."),
    DEL_ART("ARTICLE_UPLOAD", "게시물", "게시물이 삭제되었습니다."),
    REVIEWS("REVIEWS_UPLOAD", "리뷰", "새로운 리뷰가 올라왔어요."),
    DEL_REV("REVIEWS_UPLOAD", "리뷰", "리뷰가 삭제됐어요."),
    CALLING("CALLING_NOTICE", "대화방 초대", "대화 신청이 도착했습니다."),
    MESSAGE("MESSAGE_NOTICE", "메세지", "새 메세지");

    private final String key;
    private final String title;
    private final String message;
}
