package com.sparta.cucumber.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
public class UserRestController {
    public final UserRepository userRepository;
}
