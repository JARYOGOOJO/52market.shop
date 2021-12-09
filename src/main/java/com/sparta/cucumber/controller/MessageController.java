package com.sparta.cucumber.controller;

import com.sparta.cucumber.dto.ChatRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MessageController {

    private final SimpMessageSendingOperations messagingTemplate;

    @Operation(description = "채팅방 메세지 보내기", method = "MESSAGE")
    @MessageMapping("/chat/message")
    public void message(ChatRequestDto chatRequestDto) {
        log.debug("/chat/message");
        System.out.println("chatRequestDto : " + chatRequestDto.toString());
        log.debug("chatRequestDto : " + chatRequestDto.toString());
        messagingTemplate.convertAndSend("/sub/chat/room/" + chatRequestDto.getRoomSubscribeId(), chatRequestDto.getMsg());
    }

    @Operation(description = "전체 메세지 보내기", method = "MESSAGE")
    @MessageMapping("/chat/message/All")
    public void messageAll(ChatRequestDto chatRequestDto) {
        log.debug("/chat/message/All");
        log.debug("chatRequestDto : " + chatRequestDto.toString());
        messagingTemplate.convertAndSend("/sub/chat/room", chatRequestDto.getMsg());
    }

    @Operation(description = "게시글 작성시 전체알림", method = "MESSAGE")
    @MessageMapping("/article/notice/All")
    public void articleNoticeAll(ChatRequestDto chatRequestDto) {
        log.debug("/article/notice/All");
        log.debug("chatRequestDto : " + chatRequestDto.toString());
        messagingTemplate.convertAndSend("/sub/article/notice/All", chatRequestDto.getMsg());
    }

    @Operation(description = "댓글 작성시 전체알림")
    @MessageMapping("/comment/notice/All")
    public void commentNoticeAll(ChatRequestDto chatRequestDto) {
        log.debug("/comment/notice/All");
        log.debug("chatRequestDto : " + chatRequestDto.toString());
        messagingTemplate.convertAndSend("/sub/comment/notice/All", chatRequestDto.getMsg());
    }
}
