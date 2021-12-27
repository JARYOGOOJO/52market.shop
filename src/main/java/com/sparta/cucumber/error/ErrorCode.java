package com.sparta.cucumber.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 400 BAD_REQUEST : 잘못된 요청 */
    INVALID_REFRESH_TOKEN(BAD_REQUEST, "리프레시 토큰이 유효하지 않습니다."),
    MISMATCH_REFRESH_TOKEN(BAD_REQUEST, "리프레시 토큰의 유저 정보가 일치하지 않습니다."),
    INVALID_CHAT_REQUEST(BAD_REQUEST, "유효하지 않은 대화 요청입니다."),
    WRONG_INPUT_EMAIL(BAD_REQUEST, "올바른 이메일을 입력해야 합니다."),
    WRONG_INPUT_PASSWORD(BAD_REQUEST, "올바른 비밀번호를 입력해야 합니다."),
    WRONG_INPUT_PHONE_NUMBER(BAD_REQUEST, "올바른 전화번호를 입력해야 합니다."),
    WRONG_INPUT_URL(BAD_REQUEST, "올바른 주소를 입력해야 합니다."),

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    INVALID_AUTH_TOKEN(UNAUTHORIZED, "권한 정보가 없는 토큰입니다."),
    UNAUTHORIZED_MEMBER(UNAUTHORIZED, "현재 내 계정 정보가 존재하지 않습니다."),
    MALFORMED_JWT_TOKEN(UNAUTHORIZED, "잘못된 형식의 토큰입니다."),

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    USER_NOT_FOUND(NOT_FOUND, "해당 유저 정보를 찾을 수 없습니다."),
    ARTICLE_NOT_FOUND(NOT_FOUND, "해당 게시물 정보를 찾을 수 없습니다."),
    COMMENT_NOT_FOUND(NOT_FOUND, "해당 댓글 정보를 찾을 수 없습니다."),
    REVIEW_NOT_FOUND(NOT_FOUND, "해당 리뷰 정보를 찾을 수 없습니다."),
    CHATROOM_NOT_FOUND(NOT_FOUND, "해당 대화방 정보를 찾을 수 없습니다."),
    REFRESH_TOKEN_NOT_FOUND(NOT_FOUND, "로그아웃 된 사용자입니다."),

    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    DUPLICATE_RESOURCE(CONFLICT, "데이터가 이미 존재합니다."),
    USER_ALREADY_EXIST(CONFLICT, "유저가 이미 존재합니다."),
    ALREADY_EXIST(CONFLICT, "데이터의 하위 객체가 존재합니다."),

    /* 413 PAYLOAD_TOO_LARGE : 주고받을 데이터의 크기가 제한된 것보다 큼 */
    TOO_LARGE(PAYLOAD_TOO_LARGE, "파일의 크기가 너무 큽니다."),
    /* 429 TOO_MANY_REQUESTS : 지나치게 많은 요청 */
    TOO_BUSY(TOO_MANY_REQUESTS, "지나치게 많은 요청을 수행 중입니다."),
    /* 500 INTERNAL_SERVER_ERROR : Resource 를 찾을 수 없음 */
    INTERNAL_ERROR(INTERNAL_SERVER_ERROR, "서버에 에러가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}