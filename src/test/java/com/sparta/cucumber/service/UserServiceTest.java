package com.sparta.cucumber.service;

import com.sparta.cucumber.dto.UserRequestDto;
import com.sparta.cucumber.error.CustomException;
import com.sparta.cucumber.error.ErrorCode;
import com.sparta.cucumber.models.User;
import com.sparta.cucumber.repository.UserRepository;
import com.sparta.cucumber.security.UserDetailsImpl;
import com.sparta.cucumber.security.kakao.KakaoOAuth2;
import com.sparta.cucumber.utils.JwtTokenUtil;
import com.sparta.cucumber.utils.ValidationUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.OrderWith;
import org.junit.runner.manipulation.Ordering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@OrderWith(value = Ordering.Factory.class)
@SpringBootTest
@WebAppConfiguration
public class UserServiceTest {

    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private KakaoOAuth2 kakaoOAuth2;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
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
                        () -> new CustomException(ErrorCode.USER_NOT_FOUND));
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
        String email = "dddd123@nate.com";
        Double latitude = 126.1;
        Double longitude = 38.0;
        UserRequestDto userRequestDto = new UserRequestDto(
                name, password, phoneNumber, email, latitude, longitude, null);
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
    @DisplayName(value = "테스트")
    void signIn() {
        System.out.println("Test3");
    }

    @Test
    @Order(4)
    @Rollback
    @Transactional
    @DisplayName(value = "테스트")
    void kakaoLogin() {
        System.out.println("Test4");
    }

    @Test
    @Order(5)
    @Rollback
    @Transactional
    @DisplayName(value = "테스트")
    void updateProfile() {
        System.out.println("Test5");
    }

    @Test
    @Order(6)
    @Rollback
    @Transactional
    @DisplayName(value = "테스트")
    void updateLocation() {
        System.out.println("Test6");
    }

    @Test
    @Order(7)
    @Rollback
    @Transactional
    @DisplayName(value = "테스트")
    void askIfExists() {
        System.out.println("Test7");
    }
}