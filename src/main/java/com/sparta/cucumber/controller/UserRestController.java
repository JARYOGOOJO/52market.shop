package com.sparta.cucumber.controller;

import com.sparta.cucumber.dto.JwtRequestDto;
import com.sparta.cucumber.dto.JwtResponseDto;
import com.sparta.cucumber.dto.SocialLoginDto;
import com.sparta.cucumber.dto.UserRequestDto;
import com.sparta.cucumber.models.User;
import com.sparta.cucumber.redis.RedisSubscriber;
import com.sparta.cucumber.security.UserDetailsImpl;
import com.sparta.cucumber.security.UserDetailsServiceImpl;
import com.sparta.cucumber.service.UserService;
import com.sparta.cucumber.utils.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserRestController {

    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final RedisMessageListenerContainer redisMessageListener;
    private final RedisSubscriber redisSubscriber;
    private final UserService userService;

    @Operation(description = "카카오 로그인", method = "POST")
    @PostMapping(value = "/user/kakao")
    public ResponseEntity<JwtResponseDto> createAuthenticationTokenByKakao(@RequestBody SocialLoginDto socialLoginDto) {
        System.out.println("카카오 로그인: " + socialLoginDto);
        String nickname = userService.kakaoLogin(socialLoginDto.getToken());
        return ResponseEntity.ok(signInAndSubscribe(nickname));
    }

    @Operation(description = "회원 가입", method = "POST")
    @PostMapping("/user/signup")
    public ResponseEntity<JwtResponseDto> signup(@RequestBody UserRequestDto userRequestDto) {
        System.out.println("회원 가입: " + userRequestDto);
        userService.signup(userRequestDto);
        return ResponseEntity.ok(signInAndSubscribe(userRequestDto.getName()));
    }

    @Operation(description = "로그인", method = "POST")
    @PostMapping("/user/signin")
    public ResponseEntity<JwtResponseDto> signin(@RequestBody UserRequestDto userRequestDto) {
        System.out.println("중복 확인: " + userRequestDto);
        User user = userService.signIn(userRequestDto);
        return ResponseEntity.ok(signInAndSubscribe(user.getName()));
    }

    @Operation(description = "중복 확인", method = "POST")
    @PostMapping("/user/exists")
    public ResponseEntity<Boolean> checkIfExists(@RequestBody UserRequestDto userRequestDto) {
        System.out.println("중복 확인: " + userRequestDto);
        return ResponseEntity.ok().body(userService.askIfExists(userRequestDto));
    }

    @Operation(description = "토큰 갱신", method = "POST")
    @PostMapping("/user/validate")
    public ResponseEntity<JwtResponseDto> whoAmI(@RequestBody JwtRequestDto jwtRequestDto) {
        System.out.println("토큰 갱신: " + jwtRequestDto);
        return ResponseEntity.ok(userService.validate(jwtRequestDto));
    }

    @Operation(description = "유저 위치 확인", method = "POST")
    @PostMapping("/user/location")
    public ResponseEntity<?> whereAmI(@RequestBody UserRequestDto userRequestDto) {
        System.out.println("위치 확인: " + userRequestDto);
        User updatedUser = userService.updateLocation(userRequestDto);
        return ResponseEntity.ok(updatedUser);
    }

    private Authentication authenticate(String username, String password) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    private JwtResponseDto signInAndSubscribe(String username) {
        final UserDetailsImpl userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        System.out.println(userDetails.isEnabled());
        // redis 유저 subscribeId 로 subscribe
        ChannelTopic topic = new ChannelTopic(userDetails.getUser().getSubscribeId());
        redisMessageListener.addMessageListener(redisSubscriber, topic);
        // redis 전체메시지 보내기 구독
        ChannelTopic messageAllTopic = new ChannelTopic("messageAll");
        redisMessageListener.addMessageListener(redisSubscriber, messageAllTopic);
        // redis 게시글 작성시 전체알림 구독
        ChannelTopic articleNoticeTopic = new ChannelTopic("articleNotice");
        redisMessageListener.addMessageListener(redisSubscriber, articleNoticeTopic);
        // redis 댓글 작성시 전체알림 구독
        ChannelTopic commentNoticeTopic = new ChannelTopic("commentNotice");
        redisMessageListener.addMessageListener(redisSubscriber, commentNoticeTopic);
        User user = userDetails.getUser();
        return new JwtResponseDto(token, user);
    }
}
