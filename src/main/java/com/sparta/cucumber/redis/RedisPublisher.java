package com.sparta.cucumber.redis;

import com.sparta.cucumber.chat.ChatRequestDto;
import com.sparta.cucumber.chat.Notice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisPublisher {
    private final RedisTemplate<String, Object> redisTemplate;

    public void publish(ChannelTopic topic, ChatRequestDto chatRequestDto) {
        redisTemplate.convertAndSend(topic.getTopic(), chatRequestDto);
    }
    public void publish(ChannelTopic topic, Notice msg) {
        redisTemplate.convertAndSend(topic.getTopic(), msg);
    }
}
