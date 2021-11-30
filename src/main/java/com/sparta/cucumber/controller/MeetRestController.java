package com.sparta.cucumber.controller;

import com.sparta.cucumber.dto.MeetRequestDto;
import com.sparta.cucumber.models.Meet;
import com.sparta.cucumber.repository.MeetRepository;
import com.sparta.cucumber.service.MeetService;
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
    private final MeetRepository meetRepository;

    @GetMapping("/api/meets/{id}")
    ResponseEntity<?> readMeets(@PathVariable(name = "id") Long articleId) {
        List<Meet> meets = meetService.readMeets(articleId);
        return ResponseEntity.ok().body(meets);
    }

    @PostMapping("/api/meet/delete")
    ResponseEntity<?> deleteMeet(@RequestBody MeetRequestDto meetDTO) {
        Long result = meetService.deleteMeet(meetDTO);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/api/meet")
    ResponseEntity<?> meet(@RequestBody MeetRequestDto meetDTO) {
        Meet meet = meetService.create(meetDTO);
        return ResponseEntity.ok().body(meet);
    }
}
