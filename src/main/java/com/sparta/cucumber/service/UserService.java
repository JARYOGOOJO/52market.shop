package com.sparta.cucumber.service;

import com.sparta.cucumber.dto.UserRequestDto;
import com.sparta.cucumber.models.User;
import com.sparta.cucumber.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User signup(UserRequestDto userDTO){
        User user = User
                .builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .latitude(userDTO.getLatitude())
                .longitude(userDTO.getLongitude())
                .phoneNumber(userDTO.getPhoneNumber())
                .build();
        userRepository.save(user);
        return user;
    }

    @Transactional(readOnly = true)
    public User signin(UserRequestDto userDTO) {
        return userRepository.findByNameAndPassword(userDTO.getName(), userDTO.getPassword());
    }
}
