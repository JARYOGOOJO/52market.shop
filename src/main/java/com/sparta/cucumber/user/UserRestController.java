package com.sparta.cucumber.user;

import com.sparta.cucumber.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserRestController {
    public final UserRepository userRepository;
}
