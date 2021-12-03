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
    public User signup(UserRequestDto userDTO) {
        User exists = userRepository
                .findByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
        if (exists != null) {
            return signin(userDTO);
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
            return user;
        }
    }

    @Transactional(readOnly = true)
    public User signin(UserRequestDto userDTO) {
        return userRepository.findByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
    }

    public String kakaoLogin(String token) {
        // 카카오 OAuth2 를 통해 카카오 사용자 정보 조회
        KakaoUserInfo userInfo = kakaoOAuth2.getUserInfo(token);
        Long kakaoId = userInfo.getId();
        String nickname = userInfo.getNickname();
        String email = userInfo.getEmail();

        // 우리 DB 에서 회원 Id 와 패스워드
        // 회원 Id = 카카오 nickname
        // 패스워드 = 카카오 Id + ADMIN TOKEN
        String password = kakaoId + ADMIN_TOKEN;

        // DB 에 중복된 Kakao Id 가 있는지 확인
        User kakaoUser = userRepository.findBySocialId(kakaoId)
                .orElse(null);

        // 카카오 정보로 회원가입
        if (kakaoUser == null) {
            // 패스워드 인코딩
            String encodedPassword = passwordEncoder.encode(password);
            // ROLE = 사용자
            Role role = Role.USER;

            kakaoUser = new User(nickname, encodedPassword, email, role, kakaoId);
//
            userRepository.save(kakaoUser);
        }

        // 로그인 처리
        Authentication kakaoUsernamePassword = new UsernamePasswordAuthenticationToken(nickname, password);
        Authentication authentication = authenticationManager.authenticate(kakaoUsernamePassword);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return nickname;
    }
}
