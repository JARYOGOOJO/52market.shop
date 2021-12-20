package com.sparta.cucumber.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.cucumber.chat.ChatRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisSubscriber implements MessageListener {
    private final ObjectMapper objectMapper;
    private final StringRedisTemplate redisTemplate;
    private final SimpMessageSendingOperations messagingTemplate;

    // redis 에 누군가 메시지를 전송했을시 구독자가 전달받는 부분
    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String publishMessage = redisTemplate.getStringSerializer().deserialize(message.getBody());
            ChatRequestDto chatRequestDto = objectMapper.readValue(publishMessage, ChatRequestDto.class);
            log.debug("RedisSubscriber : " + chatRequestDto.toString());

            switch (chatRequestDto.getMsgType()) {
                case "chat":
                    messagingTemplate.convertAndSend("/sub/chat/" + chatRequestDto.getRoomSubscribeId(), chatRequestDto.getContent());
                    break;
                case "userNotice":
                    messagingTemplate.convertAndSend("/sub/notice/user/" + chatRequestDto.getUserSubscribeId(), chatRequestDto.getContent());
                    break;
                case "articleNotice":
                    messagingTemplate.convertAndSend("/sub/notice/article", chatRequestDto.getContent());
                    break;
                case "commentNotice":
                    messagingTemplate.convertAndSend("/sub/notice/comment", chatRequestDto.getContent());
                    break;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
