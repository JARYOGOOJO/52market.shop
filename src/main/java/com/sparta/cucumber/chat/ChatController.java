package com.sparta.cucumber.chat;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
        return ResponseEntity.ok().body(chatService.enterRoom(chatRequestDto));
    }

    @Operation(description = "방나가기", method = "POST")
    @ResponseBody
    @PostMapping("/api/room/exit")
    public ResponseEntity<?> exitRoom(@RequestBody ChatRequestDto chatRequestDto) {
        log.debug("exitRoom chatRequestDto : " + chatRequestDto.toString());
        chatService.exitRoom(chatRequestDto);
        return ResponseEntity.ok().body(null);
    }

    @Operation(description = "방 id로 가져오기", method = "GET")
    @ResponseBody
    @GetMapping("/api/room/{id}")
    public ResponseEntity<?> getRoom(@PathVariable("id") Long id){
        log.debug("getRoom : "+id);
        return ResponseEntity.ok().body(chatService.getRoom(id));
    }

    @Operation(description = "방 전체목록 가져오기", method = "GET")
    @ResponseBody
    @GetMapping("/api/rooms")
    public ResponseEntity<List<ChatRoom>> getRooms(){
        return ResponseEntity.ok().body(chatService.getRooms());
    }
}
