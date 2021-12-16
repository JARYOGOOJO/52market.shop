package com.sparta.cucumber.chat;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import static org.springframework.web.util.HtmlUtils.htmlEscape;

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
        messagingTemplate.convertAndSend("/sub/chat/room/" + chatRequestDto.getRoomSubscribeId(), htmlEscape(chatRequestDto.getMsg()));
    }

    @Operation(description = "전체 메세지 보내기", method = "MESSAGE")
    @MessageMapping("/chat/message/all")
    public void messageAll(ChatRequestDto chatRequestDto) {
        log.debug("/chat/message/All");
        log.debug("chatRequestDto : " + chatRequestDto.toString());
        messagingTemplate.convertAndSend("/sub/chat/all", htmlEscape(chatRequestDto.getMsg()));
    }

    @Operation(description = "게시글 작성시 전체알림", method = "MESSAGE")
    @MessageMapping("/article/notice/all")
    public void articleNoticeAll(ChatRequestDto chatRequestDto) {
        log.debug("/article/notice/All");
        log.debug("chatRequestDto : " + chatRequestDto.toString());
        messagingTemplate.convertAndSend("/sub/article/notice/all", htmlEscape(chatRequestDto.getMsg()));
    }

    @Operation(description = "댓글 작성시 전체알림")
    @MessageMapping("/comment/notice/all")
    public void commentNoticeAll(ChatRequestDto chatRequestDto) {
        log.debug("/comment/notice/All");
        log.debug("chatRequestDto : " + chatRequestDto.toString());
        messagingTemplate.convertAndSend("/sub/comment/notice/all", htmlEscape(chatRequestDto.getMsg()));
    }

    @Operation(description = "유저에게 알림보내기")
    @MessageMapping("/user/notice")
    public void userNoticeComment(ChatRequestDto chatRequestDto){
        log.debug("/user/notice");
        log.debug("chatRequestDto : " + chatRequestDto.toString());
        messagingTemplate.convertAndSend("/sub/user/notice/" + chatRequestDto.getUserSubscribeId(), htmlEscape(chatRequestDto.getMsg()));
    }
}
