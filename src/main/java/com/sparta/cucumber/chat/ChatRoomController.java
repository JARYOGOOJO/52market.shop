package com.sparta.cucumber.chat;

import com.sparta.cucumber.redis.RedisSubscriber;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatRoomController {
    private final ChatRoomService chatService;
    private final RedisMessageListenerContainer redisMessageListener;
    private final RedisSubscriber redisSubscriber;

    @Operation(description = "방만들기", method = "POST")
    @ResponseBody
    @PostMapping("/api/room")
    public ResponseEntity<ChatRoom> createRoom(@RequestBody ChatRequestDto chatRequestDto) {
        log.debug("chatRequestDto : " + chatRequestDto.toString());
        return ResponseEntity.ok().body(chatService.createRoom(chatRequestDto));
    }

    @Operation(description = "방입장", method = "POST")
    @ResponseBody
    @PostMapping("/api/room/enter")
    public ResponseEntity<?> enterRoom(@RequestBody ChatRequestDto chatRequestDto) {
        log.debug("enterRoom chatRequestDto : " + chatRequestDto.toString());
        ChatRoom chatRoom = chatService.enterRoom(chatRequestDto);
        // redis 채팅방 입장시 방 subId로 구독하기
        ChannelTopic topic = new ChannelTopic(chatRoom.getRoomSubscribeId());
        redisMessageListener.addMessageListener(redisSubscriber, topic);
        return ResponseEntity.ok().body(chatRoom);
    }

    @Operation(description = "방나가기", method = "POST")
    @ResponseBody
    @PostMapping("/api/room/exit")
    public ResponseEntity<?> exitRoom(@RequestBody ChatRequestDto chatRequestDto) {
        log.debug("exitRoom chatRequestDto : " + chatRequestDto.toString());
        chatService.exitRoom(chatRequestDto);
        return ResponseEntity.ok().body(null);
    }
}

