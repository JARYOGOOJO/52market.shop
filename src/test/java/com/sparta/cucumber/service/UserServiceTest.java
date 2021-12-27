package com.sparta.cucumber.service;

import com.sparta.cucumber.dto.SocialLoginDto;
import com.sparta.cucumber.dto.UserRequestDto;
import com.sparta.cucumber.error.CustomException;
import com.sparta.cucumber.models.Role;
import com.sparta.cucumber.models.User;
import com.sparta.cucumber.repository.UserRepository;
import com.sparta.cucumber.security.UserDetailsImpl;
import com.sparta.cucumber.security.kakao.KakaoOAuth2;
import com.sparta.cucumber.security.kakao.KakaoUserInfo;
import com.sparta.cucumber.utils.JwtTokenUtil;
import com.sparta.cucumber.utils.ValidationUtil;
import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.OrderWith;
import org.junit.runner.manipulation.Ordering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static com.sparta.cucumber.error.ErrorCode.USER_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@OrderWith(value = Ordering.Factory.class)
@SpringBootTest
@WebAppConfiguration
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private KakaoOAuth2 kakaoOAuth2;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private ValidationUtil validationUtil;
    @Autowired
    private UserService userService;

    @Test
    @Order(1)
    @Rollback
    @Transactional
    @DisplayName(value = "JWT 토큰 변환 후 다시 유저로 변환 테스트")
    void validate() {
        System.out.println("Test1");
        User user = User.builder().email("ydm2790@nate.com").name("유동민").build();
        System.out.println("user 000 : " + user);
        userRepository.save(user);
        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        System.out.println(userDetails);
        String token = jwtTokenUtil.generateToken(userDetails);
        System.out.println("token: " + token);
        User user1 = userRepository
                .findByName(jwtTokenUtil.getUsernameFromToken(token))
                .orElseThrow(
                        () -> new CustomException(USER_NOT_FOUND));
        System.out.println("user 001 : " + user1);
        assertEquals(user, user1);
    }

    @Test
    @Order(2)
    @Rollback
    @Transactional
    @DisplayName(value = "회원 가입 테스트")
    void signup() {
        System.out.println("Test2");
        String name = "유동민";
        String password = "rrr999999";
        String phoneNumber = "010-9999-1111";
        assertTrue(validationUtil.validPhoneNumber(phoneNumber));
        String email = "dddd123@nate.com";
        Assertions.assertFalse(validationUtil.invalidEmail(email));
        Double latitude = 126.1;
        Double longitude = 38.0;
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName(name);
        userRequestDto.setPassword(password);
        userRequestDto.setPhoneNumber(phoneNumber);
        userRequestDto.setEmail(email);
        userRequestDto.setLatitude(latitude);
        userRequestDto.setLongitude(longitude);
        User first = userService.signup(userRequestDto);
        assertEquals(first.getEmail(), email);
        assertEquals(first.getPhoneNumber(), phoneNumber);
        assertEquals(first.getEmail(), email);
        Assertions.assertNull(first.getLatitude());
        Assertions.assertNull(first.getLongitude());
        Assertions.assertNull(first.getPicture());
        System.out.println(first.getRefreshToken());
    }

    @Test
    @Order(3)
    @Rollback
    @Transactional
    @DisplayName(value = "로그인 기능 테스트")
    void signIn() {
        System.out.println("Test3");
        String name = "유동민";
        String password = "awesome12345";
        String phoneNumber = "010-1234-1857";
        String email = "dddd123@nate.com";
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName(name);
        userRequestDto.setPassword(password);
        userRequestDto.setPhoneNumber(phoneNumber);
        userRequestDto.setEmail(email);
        System.out.println(userRequestDto);
        User registered = userService.signup(userRequestDto);
        System.out.println(registered);
        String encoded = passwordEncoder.encode(password);
        System.out.println(registered.getPassword());
        System.out.println(encoded);
        assertTrue(passwordEncoder.matches(password, registered.getPassword()));
        User connected = userService.signIn(userRequestDto);
        System.out.println(connected);
        assertEquals(registered, connected);
    }

    @Test
    @Ignore
    @Order(4)
    @Rollback
    @Transactional
    @DisplayName(value = "카카오 로그인 테스트")
    void kakaoLogin() {
        System.out.println("Test4");
        String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";
        SocialLoginDto loginDto = new SocialLoginDto();
        loginDto.setToken("4a6g-216cJJ9rD4euhcm8NKKqoWMrnsQ-YWHaAorDSAAAAF95N6q2A");
        KakaoUserInfo userInfo = kakaoOAuth2.getUserInfo(loginDto.getToken());
        System.out.println(userInfo);
        Long kakaoId = userInfo.getId();
        System.out.println(kakaoId);
        String nickname = userInfo.getNickname();
        System.out.println(nickname);
        String email = userInfo.getEmail();
        System.out.println(email);
        String password = kakaoId + ADMIN_TOKEN;
        System.out.println(password);
        String encodedPassword = passwordEncoder.encode(password);
        System.out.println(encodedPassword);
        User kakaoUser;
        User checkRegistered = userRepository.findBySocialId(kakaoId).orElse(null);
        System.out.println(checkRegistered);
        if (checkRegistered == null) {
            kakaoUser = User.builder()
                    .kakaoId(kakaoId)
                    .email(email)
                    .name(nickname)
                    .encodedPassword(encodedPassword)
                    .build();
        } else {
            kakaoUser = checkRegistered.updateKakao(nickname, encodedPassword, email, Role.USER, kakaoId);
        }
        System.out.println(kakaoUser);
        userRepository.save(kakaoUser);
        assertEquals(kakaoUser, checkRegistered);
    }

    @Test
    @Order(5)
    @Rollback
    @Transactional
    @DisplayName(value = "프로필 업데이트 테스트")
    void updateProfile() {
        System.out.println("Test5");
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setEmail("ydm2790@naver.com");
        userRequestDto.setPhoneNumber("010-1111-9999");
        System.out.println(userRequestDto);
        String profileImage = "https://k.kakaocdn.net/dn/7LXrI/btrobUYvkrC/NOmuvwVeZkCKI3p5oUEbZ1/img_110x110.jpg";
        System.out.println(profileImage);
        User findUser = userRepository
                .findByEmail(userRequestDto.getEmail())
                .orElse(null);
        System.out.println(findUser);
        if (findUser != null) {
            userRequestDto.setPicture(profileImage);
            findUser.updateMyPage(userRequestDto);
            System.out.println(findUser);
        }
    }

    @Test
    @Order(6)
    @Rollback
    @Transactional
    @DisplayName(value = "유저 위치 정보 업데이트 테스트")
    void updateLocation() {
        System.out.println("Test6");
        UserRequestDto userRequestDto = new UserRequestDto();
        Double latitude = 128.1231231;
        Double longitude = 37.125654375;
        userRequestDto.setLatitude(latitude);
        userRequestDto.setLongitude(longitude);
        System.out.println(userRequestDto);
        User user = User.builder()
                .email("ydm2790@cyworld.com")
                .phoneNumber("010-2222-8888")
                .name("돈민찌:D")
                .build();
        System.out.println(user);
        user.updateLocation(userRequestDto);
        System.out.println(user);
        assertEquals(latitude, user.getLatitude());
        assertEquals(longitude, user.getLongitude());
    }
}