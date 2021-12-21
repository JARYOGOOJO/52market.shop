package com.sparta.cucumber.chat;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Operation(description = "방만들기", method = "POST")
    @ResponseBody
    @PostMapping("/new/room")
    public ResponseEntity<ChatRoom> createRoom(@RequestBody ChatRequestDto chatRequestDto) {
        log.debug("chatRequestDto : " + chatRequestDto.toString());
        return ResponseEntity.ok().body(chatService.createRoom(chatRequestDto));
    }

    @Operation(description = "방입장", method = "POST")
    @ResponseBody
    @PostMapping("/room/enter")
    public ResponseEntity<?> enterRoom(@RequestBody ChatRequestDto chatRequestDto) {
        log.debug("enterRoom chatRequestDto : " + chatRequestDto.toString());
        return ResponseEntity.ok().body(chatService.enterRoom(chatRequestDto));
    }

    @Operation(description = "방나가기", method = "POST")
    @ResponseBody
    @PostMapping("/room/exit")
    public ResponseEntity<?> exitRoom(@RequestBody ChatRequestDto chatRequestDto) {
        log.debug("exitRoom chatRequestDto : " + chatRequestDto.toString());
        chatService.exitRoom(chatRequestDto);
        return ResponseEntity.ok().body(null);
    }
}

