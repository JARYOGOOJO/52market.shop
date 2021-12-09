package com.sparta.cucumber.service;

import com.sparta.cucumber.dto.UserRequestDto;
import com.sparta.cucumber.models.Role;
import com.sparta.cucumber.models.User;
import com.sparta.cucumber.repository.UserRepository;
import com.sparta.cucumber.security.kakao.KakaoOAuth2;
import com.sparta.cucumber.security.kakao.KakaoUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";
    private final KakaoOAuth2 kakaoOAuth2;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public void signup(UserRequestDto userDTO) {
        User exists = userRepository
                .findByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword()).orElse(null);
        if (exists != null) {
            signin(userDTO);
        } else {
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
        }
    }

    @Transactional(readOnly = true)
    public void signin(UserRequestDto userDTO) {
        userRepository.findByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
    }

    public String kakaoLogin(String token) {
        KakaoUserInfo userInfo = kakaoOAuth2.getUserInfo(token);
        Long kakaoId = userInfo.getId();
        String nickname = userInfo.getNickname();
        String email = userInfo.getEmail();
        String password = kakaoId + ADMIN_TOKEN;
        User kakaoUser = userRepository.findBySocialId(kakaoId)
                .orElse(null);
        if (kakaoUser == null) {
            String encodedPassword = passwordEncoder.encode(password);
            Role role = Role.USER;
            User existsUser = userRepository.findByEmail(email).orElse(null);
            if (existsUser != null) {
                kakaoUser = existsUser.updateKakao(nickname, encodedPassword, email, role, kakaoId);
            } else {
                kakaoUser = new User(nickname, encodedPassword, email, role, kakaoId);
            }
            userRepository.save(kakaoUser);
        }
        Authentication kakaoUsernamePassword = new UsernamePasswordAuthenticationToken(nickname, password);
        Authentication authentication = authenticationManager.authenticate(kakaoUsernamePassword);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return nickname;
    }

    @Transactional
    public User updateProfileImage(UserRequestDto userDTO, String profileImage) {
        User user = userRepository
                .findByEmail(userDTO.getEmail())
                .orElseThrow(()
                        -> new NullPointerException("잘못된 접근입니다."));
        userDTO.setPicture(profileImage);
        return user.updateImage(userDTO);
    }
}
