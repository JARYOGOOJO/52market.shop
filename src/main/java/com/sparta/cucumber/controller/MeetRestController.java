package com.sparta.cucumber.controller;

import com.sparta.cucumber.dto.MeetRequestDto;
import com.sparta.cucumber.models.Meet;
import com.sparta.cucumber.repository.MeetRepository;
import com.sparta.cucumber.service.MeetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MeetRestController {

    private final MeetService meetService;
    private final MeetRepository meetRepository;

    @PostMapping("/api/meet/delete")
    ResponseEntity<?> deleteMeet(@RequestBody MeetRequestDto meetDTO){
        meetRepository.deleteById(meetDTO.getId());
        return ResponseEntity.ok().body(200);
    }

    @PostMapping("/api/meet")
    ResponseEntity<?> meet(@RequestBody MeetRequestDto meetDTO){
        Meet meet = meetService.create(meetDTO);
        return ResponseEntity.ok().body(meet);
    }
}
