package com.sparta.cucumber.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.cucumber.chat.Notice;
import com.sparta.cucumber.chat.NoticeType;
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
            Notice msg = objectMapper.readValue(publishMessage,Notice.class);
            if(msg.getType() == NoticeType.MESSAGE){
                messagingTemplate.convertAndSend("/sub/chat/" + msg.getSubscriberId(), msg);
            }else if(msg.getType() == NoticeType.CALLING){
                messagingTemplate.convertAndSend("/sub/notice/user/" + msg.getSubscriberId(), msg);
            }else if(msg.getType() == NoticeType.DEL_CMT){
                messagingTemplate.convertAndSend("/sub/notice/delete/comment", msg);
            }else if(msg.getType() == NoticeType.COMMENT){
                messagingTemplate.convertAndSend("/sub/notice/comment", msg);
            }else if(msg.getType() == NoticeType.ARTICLE){
                messagingTemplate.convertAndSend("/sub/notice/article", msg);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
