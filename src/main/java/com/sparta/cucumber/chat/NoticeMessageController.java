package com.sparta.cucumber.chat;

import com.sparta.cucumber.redis.RedisPublisher;
import com.sparta.cucumber.redis.RedisSubscriber;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
@Controller
public class NoticeMessageController {

    private final SimpMessageSendingOperations messagingTemplate;
    private final NoticeService service;
    private final RedisPublisher redisPublisher;
    private final RedisSubscriber redisSubscriber;
    private final RedisMessageListenerContainer redisMessageListener;

    @Operation(description = "게시글 작성시 전체알림", method = "MESSAGE")
    @MessageMapping("/new/articles")
    public void articleNoticeAll(ChatRequestDto requestDto) {
        log.debug("/new/articles");
        log.debug("chatRequestDto : " + requestDto);
        Notice notice = service.article(requestDto);
        ChannelTopic topic = new ChannelTopic("articleNotice");
        redisPublisher.publish(topic, notice);
    }

    @Operation(description = "댓글 작성시 전체알림")
    @MessageMapping("/new/comments")
    public void commentNoticeAll(ChatRequestDto requestDto) {
        log.debug("/new/comments");
        log.debug("chatRequestDto : " + requestDto);
        Notice notice = service.comment(requestDto);
        ChannelTopic topic = new ChannelTopic("commentNotice");
        redisPublisher.publish(topic, notice);
    }

    @Operation(description = "댓글 삭제 시 전체알림")
    @MessageMapping("/del/comments")
    public void deleteOneComment(ChatRequestDto requestDto) {
        log.debug("/del/comments");
        log.debug("chatRequestDto : " + requestDto);
        Notice notice = service.uncomment(requestDto);
        ChannelTopic topic = new ChannelTopic("commentDeleteNotice");
        redisPublisher.publish(topic, notice);
    }

    @Operation(description = "유저에게 알림보내기")
    @MessageMapping("/new/notices")
    public void userNoticeMessage(ChatRequestDto requestDto) {
        log.debug("/new/notices");
        log.debug("chatRequestDto : " + requestDto);
        System.out.println("/new/notices chatRequestDto : "+ requestDto);
        Long userId = requestDto.getTargetId();
        Notice notice = service.invite(requestDto);

        // redis 에게 구독한 사람한테 전달하라고 전송
        ChannelTopic topic = new ChannelTopic(String.valueOf(userId));
        redisPublisher.publish(topic, notice);
    }

    @Operation(description = "채팅방 메세지 보내기", method = "MESSAGE")
    @MessageMapping("/chat/messages")
    public void message(ChatRequestDto requestDto) {
        log.debug("/chat/messages");
        System.out.println("/chat/messages chatRequestDto : " + requestDto.toString());
        log.debug("chatRequestDto : " + requestDto);
        String roomId = requestDto.getRoomSubscribeId();
        Notice msg = service.message(requestDto);
        ChannelTopic topic = new ChannelTopic(roomId);
        redisPublisher.publish(topic, msg);
    }
}
