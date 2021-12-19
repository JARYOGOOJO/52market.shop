package com.sparta.cucumber.chat;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
@Controller
public class NoticeMessageController {

    private final SimpMessageSendingOperations messagingTemplate;
    private final NoticeService service;

    @Operation(description = "게시글 작성시 전체알림", method = "MESSAGE")
    @MessageMapping("/new/articles")
    public void articleNoticeAll(ChatRequestDto requestDto) {
        log.debug("/new/articles");
        log.debug("chatRequestDto : " + requestDto);
        Notice notice = service.article(requestDto);
        messagingTemplate.convertAndSend("/sub/notice/article", notice);
    }

    @Operation(description = "댓글 작성시 전체알림")
    @MessageMapping("/new/comments")
    public void commentNoticeAll(ChatRequestDto requestDto) {
        log.debug("/new/comments");
        log.debug("chatRequestDto : " + requestDto);
        Notice notice = service.comment(requestDto);
        messagingTemplate.convertAndSend("/sub/notice/comment", notice);
    }

    @Operation(description = "댓글 삭제 시 전체알림")
    @MessageMapping("/del/comments")
    public void deleteOneComment(ChatRequestDto requestDto) {
        log.debug("/del/comments");
        log.debug("chatRequestDto : " + requestDto);
        Notice notice = service.uncomment(requestDto);
        messagingTemplate.convertAndSend("/sub/notice/comment", notice);
    }

    @Operation(description = "유저에게 알림보내기")
    @MessageMapping("/new/notices")
    public void userNoticeMessage(ChatRequestDto requestDto) {
        log.debug("/new/notices");
        log.debug("chatRequestDto : " + requestDto);
        Long userId = requestDto.getTargetId();
        Notice notice = service.invite(requestDto);
        messagingTemplate.convertAndSend("/sub/notice/user/" + userId, notice);
    }

    @Operation(description = "채팅방 메세지 보내기", method = "MESSAGE")
    @MessageMapping("/chat/messages")
    public void message(ChatRequestDto requestDto) {
        log.debug("/chat/messages");
        System.out.println("chatRequestDto : " + requestDto.toString());
        log.debug("chatRequestDto : " + requestDto);
        String roomId = requestDto.getRoomSubscribeId();
        Notice msg = service.message(requestDto);
        messagingTemplate.convertAndSend("/sub/chat/" + roomId, msg);
    }
}
