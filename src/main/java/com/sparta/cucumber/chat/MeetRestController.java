package com.sparta.cucumber.chat;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MeetRestController {

    private final MeetService meetService;

    @Operation(description = "유저간의 매칭 id로 가져오기",method = "GET")
    @GetMapping("/api/meets/{id}")
    ResponseEntity<?> readMeets(@PathVariable(name = "id") Long articleId) {
        List<Meet> meets = meetService.readMeets(articleId);
        return ResponseEntity.ok().body(meets);
    }

    @Operation(description = "유저간의 매칭 삭제",method = "POST")
    @PostMapping("/api/meet/delete")
    ResponseEntity<?> deleteMeet(@RequestBody MeetRequestDto meetDTO) {
        Long result = meetService.deleteMeet(meetDTO);
        return ResponseEntity.ok().body(result);
    }

    @Operation(description = "유저간의 매칭 저장",method = "POST")
    @PostMapping("/api/meet")
    ResponseEntity<?> meet(@RequestBody MeetRequestDto meetDTO) {
        Meet meet = meetService.create(meetDTO);
        return ResponseEntity.ok().body(meet);
    }
}
