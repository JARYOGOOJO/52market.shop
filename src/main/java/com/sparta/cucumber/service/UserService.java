package com.sparta.cucumber.service;

import com.sparta.cucumber.dto.JwtRequestDto;
import com.sparta.cucumber.dto.JwtResponseDto;
import com.sparta.cucumber.dto.UserRequestDto;
import com.sparta.cucumber.error.CustomException;
import com.sparta.cucumber.models.Role;
import com.sparta.cucumber.models.User;
import com.sparta.cucumber.repository.UserRepository;
import com.sparta.cucumber.security.kakao.KakaoOAuth2;
import com.sparta.cucumber.security.kakao.KakaoUserInfo;
import com.sparta.cucumber.utils.JwtTokenUtil;
import com.sparta.cucumber.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static com.sparta.cucumber.error.ErrorCode.*;
import static org.springframework.web.util.HtmlUtils.htmlEscape;

@RequiredArgsConstructor
@Service
public class UserService {
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";
    private final UserRepository userRepository;
    private final KakaoOAuth2 kakaoOAuth2;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final ValidationUtil validationUtil;

    @Transactional
    public JwtResponseDto validate(JwtRequestDto requestDto) {
        String token = requestDto.getToken();
        String refresh = requestDto.getRefreshToken();
        User user1 = userRepository.findById(requestDto.getUserId()).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        User user2 = userRepository.findByName(jwtTokenUtil.getUsernameFromToken(token)).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        if (!Objects.equals(user1, user2)) {
            throw new UsernameNotFoundException("잘못된 요청입니다.");
        }
        return new JwtResponseDto(token, refresh, user1.getId(), user1.getSubscribeId());
    }

    @Transactional
    public void signup(UserRequestDto userRequestDto) {
        String email = userRequestDto.getEmail();
        String phoneNumber = userRequestDto.getPhoneNumber();
        if (validationUtil.invalidEmail(email)) {
            throw new CustomException(WRONG_INPUT_EMAIL);
        } else if (!validationUtil.validPhoneNumber(phoneNumber)) {
            throw new CustomException(WRONG_INPUT_PHONE_NUMBER);
        }
        User exists = userRepository
                .findByEmail(userRequestDto.getEmail()).orElse(null);
        if (exists != null) {
            throw new CustomException(USER_ALREADY_EXIST);
        } else {
            String refresh = jwtTokenUtil.genRefreshToken();
            User user = User
                    .builder()
                    .name(htmlEscape(userRequestDto.getName()))
                    .email(htmlEscape(email))
                    .encodedPassword(passwordEncoder.encode(userRequestDto.getPassword()))
                    .phoneNumber(phoneNumber)
                    .refreshToken(refresh)
                    .build();
            userRepository.save(user);
        }
    }

    @Transactional(readOnly = true)
    public User signIn(UserRequestDto userRequestDto) {
        if (validationUtil.invalidEmail(userRequestDto.getEmail())) {
            throw new CustomException(WRONG_INPUT_EMAIL);
        }
        User user = userRepository.findByEmail(userRequestDto.getEmail()).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        if (passwordEncoder.matches(userRequestDto.getPassword(), user.getPassword())) {
            return user;
        } else {
            throw new CustomException(USER_NOT_FOUND);
        }
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
                String refresh = jwtTokenUtil.genRefreshToken();
                kakaoUser = User
                        .builder()
                        .name(nickname)
                        .email(email)
                        .encodedPassword(encodedPassword)
                        .kakaoId(kakaoId)
                        .refreshToken(refresh)
                        .build();
            }
            userRepository.save(kakaoUser);
        }
        Authentication kakaoUsernamePassword = new UsernamePasswordAuthenticationToken(nickname, password);
        Authentication authentication = authenticationManager.authenticate(kakaoUsernamePassword);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return nickname;
    }

    @Transactional
    public User updateProfile(UserRequestDto userRequestDto, String profileImage) {
        User findUser = userRepository
                .findByEmail(userRequestDto.getEmail())
                .orElseThrow(()
                        -> new CustomException(USER_NOT_FOUND));
        userRequestDto.setPicture(profileImage);
        findUser.updateMyPage(userRequestDto);
        return findUser;
    }

    public User updateLocation(UserRequestDto userRequestDto) {
        User findUser = userRepository
                .findByEmail(userRequestDto.getEmail())
                .orElseThrow(()
                        -> new CustomException(USER_NOT_FOUND));
        return findUser.updateLocation(userRequestDto);
    }

    public boolean askIfExists(UserRequestDto userRequestDto) {
        return userRepository.existsByName(userRequestDto.getName());
    }
}
