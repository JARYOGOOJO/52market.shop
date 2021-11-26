package com.sparta.cucumber.controller;

import com.sparta.cucumber.dto.UserRequestDto;
import com.sparta.cucumber.models.User;
import com.sparta.cucumber.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserRestController {
    public final UserService userService;

    @PostMapping("/api/signup")
    public ResponseEntity<?> signup(@RequestBody UserRequestDto userDTO) {
        System.out.println(userDTO.toString());
        User registerUser = userService.signup(userDTO);
        return ResponseEntity.ok().body(registerUser);
    }

    @PostMapping("/api/signin")
    public ResponseEntity<?> signin(@RequestBody UserRequestDto userDTO) {
        System.out.println(userDTO.toString());
        User user = userService.signin(userDTO);
        return ResponseEntity.ok().body(user);
    }
}
