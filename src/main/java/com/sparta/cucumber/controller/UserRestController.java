package com.sparta.cucumber.controller;

import com.sparta.cucumber.dto.JwtRequestDto;
import com.sparta.cucumber.dto.JwtResponseDto;
import com.sparta.cucumber.dto.SocialLoginDto;
import com.sparta.cucumber.dto.UserRequestDto;
import com.sparta.cucumber.redis.RedisSubscriber;
import com.sparta.cucumber.security.UserDetailsImpl;
import com.sparta.cucumber.security.UserDetailsServiceImpl;
import com.sparta.cucumber.service.S3Uploader;
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
    private final UserService userService;
    private final S3Uploader s3Uploader;
    private final RedisMessageListenerContainer redisMessageListener;
    private final RedisSubscriber redisSubscriber;

    @Operation(description = "카카오 로그인", method = "POST")
    @PostMapping(value = "/user/kakao")
    public ResponseEntity<?> createAuthenticationTokenByKakao(@RequestBody SocialLoginDto socialLoginDto) {
        String email = userService.kakaoLogin(socialLoginDto.getToken());
        final UserDetailsImpl userDetails = userDetailsService.loadUserByEmail(email);
        final String token = jwtTokenUtil.generateToken(userDetails);
        JwtResponseDto result = new JwtResponseDto(token, userDetails.getUser().getId(), userDetails.getUser().getSubscribeId());
        System.out.println(userDetails.isEnabled());

        // redis 유저 subscribeId로 subscribe
//        ChannelTopic userTopic = new ChannelTopic(userDetails.getUser().getSubscribeId());
        ChannelTopic userTopic = new ChannelTopic(String.valueOf(userDetails.getUser().getId()));
        redisMessageListener.addMessageListener(redisSubscriber, userTopic);

        // redis 전체메시지 보내기 구독
        ChannelTopic messageAllTopic = new ChannelTopic("messageAll");
        redisMessageListener.addMessageListener(redisSubscriber, messageAllTopic);

        // redis 게시글 작성시 전체알림 구독
        ChannelTopic articleNoticeTopic = new ChannelTopic("articleNotice");
        redisMessageListener.addMessageListener(redisSubscriber, articleNoticeTopic);

        // redis 댓글 작성시 전체알림 구독
        ChannelTopic commentNoticeTopic = new ChannelTopic("commentNotice");
        redisMessageListener.addMessageListener(redisSubscriber, commentNoticeTopic);

        // redis 댓글 삭제시 전체알림 구독
        ChannelTopic commentDeleteNoticeTopic = new ChannelTopic("commentDeleteNotice");
        redisMessageListener.addMessageListener(redisSubscriber, commentNoticeTopic);

        return ResponseEntity.ok(result);
    }

    @Operation(description = "회원가입", method = "POST")
    @PostMapping("/user/signup")
    public ResponseEntity<?> signup(@RequestBody UserRequestDto userDTO) {
        System.out.println(userDTO);
        userService.signup(userDTO);
        final UserDetailsImpl userDetails = userDetailsService.loadUserByEmail(userDTO.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponseDto(token, userDetails.getUser().getId(), userDetails.getUser().getSubscribeId()));
    }

    @Operation(description = "로그인", method = "POST")
    @PostMapping("/user/signin")
    public ResponseEntity<?> signin(@RequestBody UserRequestDto userDTO) {
        System.out.println(userDTO);
        userService.signin(userDTO);
        final UserDetailsImpl userDetails = userDetailsService.loadUserByEmail(userDTO.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        System.out.println(userDetails.isEnabled());
        //        System.out.println(auth.isAuthenticated());
        // redis 유저subscribeId로 subscribe
//        ChannelTopic topic = new ChannelTopic(userDetails.getUser().getSubscribeId());
        ChannelTopic userTopic = new ChannelTopic(String.valueOf(userDetails.getUser().getId()));
        redisMessageListener.addMessageListener(redisSubscriber, userTopic);

        // redis 전체메시지 보내기 구독
        ChannelTopic messageAllTopic = new ChannelTopic("messageAll");
        redisMessageListener.addMessageListener(redisSubscriber, messageAllTopic);

        // redis 게시글 작성시 전체알림 구독
        ChannelTopic articleNoticeTopic = new ChannelTopic("articleNotice");
        redisMessageListener.addMessageListener(redisSubscriber, articleNoticeTopic);

        // redis 댓글 작성시 전체알림 구독
        ChannelTopic commentNoticeTopic = new ChannelTopic("commentNotice");
        redisMessageListener.addMessageListener(redisSubscriber, commentNoticeTopic);

        // redis 댓글 삭제시 전체알림 구독
        ChannelTopic commentDeleteNoticeTopic = new ChannelTopic("commentDeleteNotice");
        redisMessageListener.addMessageListener(redisSubscriber, commentNoticeTopic);

        return ResponseEntity.ok(new JwtResponseDto(token, userDetails.getUser().getId(), userDetails.getUser().getSubscribeId()));
    }

    @Operation(description = "유저 확인", method = "POST")
    @PostMapping("/user/validate")
    public ResponseEntity<?> whoAmI(@RequestBody JwtRequestDto jwtDTO) {
        System.out.println(jwtDTO);
        JwtResponseDto jwtResponseDto = userService.validate(jwtDTO);
        return ResponseEntity.ok(jwtResponseDto);
    }

    private Authentication authenticate(String email, String password) throws Exception {
        try {
            String username = email.split("@")[0];
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
