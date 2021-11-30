package com.sparta.cucumber.controller;

import com.sparta.cucumber.dto.MeetRequestDto;
import com.sparta.cucumber.models.Meet;
import com.sparta.cucumber.service.MeetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MeetRestController {

    private final MeetService meetService;

    @GetMapping("/api/meets")
    ResponseEntity<?> readMeets() {
        List<Meet> meets = meetRepository.findAll();
        return ResponseEntity.ok().body(meets);
    }

    @PostMapping("/api/meet/delete")
    ResponseEntity<?> deleteMeet(@RequestBody MeetRequestDto meetDTO) {
        meetRepository.deleteById(meetDTO.getId());
        return ResponseEntity.ok().body(meetDTO.getId());
    }

    @PostMapping("/api/meet")
    ResponseEntity<?> meet(@RequestBody MeetRequestDto meetDTO) {
        Meet meet = meetService.create(meetDTO);
        return ResponseEntity.ok().body(meet);
    }
}
