package com.sparta.cucumber.controller;

import com.sparta.cucumber.dto.ChatRequestDto;
import com.sparta.cucumber.models.ChatRoom;
import com.sparta.cucumber.service.ChatService;
import com.sparta.cucumber.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {
    private final ChatService chatService;
    private final SimpMessageSendingOperations messagingTemplate;
    private final UserService userService;

    @ResponseBody
    @PostMapping("/api/room/exit")
    public ResponseEntity<?> exitRoom(@RequestBody ChatRequestDto chatRequestDto){
        log.debug("exitRoom chatRequestDto : "+chatRequestDto.toString());
        chatService.exitRoom(chatRequestDto);
        return ResponseEntity.ok().body(null);
    }

    @ResponseBody
    @PostMapping("/api/room/enter")
    public ResponseEntity<?> enterRoom(@RequestBody ChatRequestDto chatRequestDto){
        log.debug("enterRoom chatRequestDto : "+chatRequestDto.toString());
        return ResponseEntity.ok().body(chatService.enterRoom(chatRequestDto));
    }

    @ResponseBody
    @GetMapping("/api/room/{id}")
    public ResponseEntity<?> getRoom(@PathVariable("id") Long id){
        log.debug("getRoom : "+id);
        return ResponseEntity.ok().body(chatService.getRoom(id));
    }

    @ResponseBody
    @PostMapping("/api/room")
    public ResponseEntity<ChatRoom> createRoom(@RequestBody ChatRequestDto chatRequestDto) {
        log.debug("chatRequestDto : " + chatRequestDto.toString());
        return ResponseEntity.ok().body(chatService.createRoom(chatRequestDto));
    }
    @ResponseBody
    @GetMapping("/api/rooms")
    public ResponseEntity<List<ChatRoom>> getRooms(){
        return ResponseEntity.ok().body(chatService.getRooms());
    }

    @MessageMapping("/chat/message")
    public void message(ChatRequestDto chatRequestDto) throws Exception {
        log.debug("/chat/message");
        log.debug("chatRequestDto : "+chatRequestDto.toString());
        messagingTemplate.convertAndSend("/sub/chat/room/"+chatRequestDto.getRoomSubscribeId(),chatRequestDto.getMsg());
    }

    @MessageMapping("/chat/message/All")
    public void messageAll(ChatRequestDto chatRequestDto) throws Exception{
        log.debug("/chat/message/All");
        log.debug("chatRequestDto : "+chatRequestDto.toString());
        messagingTemplate.convertAndSend("/sub/chat/room",chatRequestDto.getMsg());
    }
}
