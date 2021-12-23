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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ChatRoomController {
    private final ChatRoomService chatService;
    private final RedisMessageListenerContainer redisMessageListener;
    private final RedisSubscriber redisSubscriber;

    @Operation(description = "방만들기", method = "POST")
    @PostMapping("/new/room")
    public ResponseEntity<ChatRoom> createRoom(@RequestBody ChatRequestDto chatRequestDto) {
        log.debug("chatRequestDto : " + chatRequestDto.toString());
        ChatRoom createdRoom = chatService.createRoom(chatRequestDto);
        // redis 방입장 구독
        ChannelTopic topic = new ChannelTopic(createdRoom.getRoomSubscribeId());
        redisMessageListener.addMessageListener(redisSubscriber, topic);
        return ResponseEntity.ok().body(createdRoom);
    }

    @Operation(description = "방입장", method = "POST")
    @PostMapping("/room/enter")
    public ResponseEntity<?> enterRoom(@RequestBody ChatRequestDto chatRequestDto) {
        log.debug("enterRoom chatRequestDto : " + chatRequestDto.toString());
        ChatRoom enteredRoom = chatService.enterRoom(chatRequestDto);
        // redis 방입장 구독
        ChannelTopic topic = ChannelTopic.of(enteredRoom.getRoomSubscribeId());
        redisMessageListener.addMessageListener(redisSubscriber, topic);
        return ResponseEntity.ok().body(enteredRoom);
    }

    @Operation(description = "방나가기", method = "POST")
    @PostMapping("/room/exit")
    public ResponseEntity<?> exitRoom(@RequestBody ChatRequestDto chatRequestDto) {
        log.debug("exitRoom chatRequestDto : " + chatRequestDto.toString());
        ChatRoom leavedRoom = chatService.exitRoom(chatRequestDto);
        ChannelTopic topic = ChannelTopic.of(leavedRoom.getRoomSubscribeId());
        redisMessageListener.removeMessageListener(redisSubscriber, topic);
        return ResponseEntity.ok().body(null);
    }

    @Operation(description = "방 초대된 지 여부 확인", method = "POST")
    @PostMapping("/room/invited")
    public ResponseEntity<?> isInvited(@RequestBody ChatRequestDto chatRequestDto) {
        return ResponseEntity.ok().body(chatService.isInvited(chatRequestDto));
    }

    @Operation(description = "방 초대 한지 여부 확인", method = "POST")
    @PostMapping("/room/invite")
    public ResponseEntity<?> isInvite(@RequestBody ChatRequestDto chatRequestDto) {
        return ResponseEntity.ok().body(chatService.isInvite(chatRequestDto));
    }

    @Operation(description = "방에 소속된 적 있는지 확인", method = "POST")
    @PostMapping("/room/created")
    public ResponseEntity<?> isRoomCreated(@RequestBody ChatRequestDto chatRequestDto){
        return ResponseEntity.ok().body(chatService.isRoomCreated(chatRequestDto));
    }

}

