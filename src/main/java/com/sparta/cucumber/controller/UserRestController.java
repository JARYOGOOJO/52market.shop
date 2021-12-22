package com.sparta.cucumber.controller;

import com.sparta.cucumber.dto.JwtRequestDto;
import com.sparta.cucumber.dto.JwtResponseDto;
import com.sparta.cucumber.dto.SocialLoginDto;
import com.sparta.cucumber.dto.UserRequestDto;
import com.sparta.cucumber.models.User;
import com.sparta.cucumber.security.UserDetailsImpl;
import com.sparta.cucumber.security.UserDetailsServiceImpl;
import com.sparta.cucumber.service.S3Uploader;
import com.sparta.cucumber.service.UserService;
import com.sparta.cucumber.utils.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Operation(description = "카카오 로그인", method = "POST")
    @PostMapping(value = "/user/kakao")
    public ResponseEntity<?> createAuthenticationTokenByKakao(@RequestBody SocialLoginDto socialLoginDto) {
        String nickname = userService.kakaoLogin(socialLoginDto.getToken());
        final UserDetailsImpl userDetails = userDetailsService.loadUserByUsername(nickname);
        final String token = jwtTokenUtil.generateToken(userDetails);
        final String refresh = userDetails.getUser().getRefreshToken();
        JwtResponseDto result = new JwtResponseDto(token, refresh, userDetails.getUser().getId(), userDetails.getUser().getSubscribeId());
        System.out.println(userDetails.isEnabled());
        return ResponseEntity.ok(result);
    }

    @Operation(description = "회원가입", method = "POST")
    @PostMapping("/user/signup")
    public ResponseEntity<?> signup(@RequestBody UserRequestDto userDTO) {
        System.out.println(userDTO);
        userService.signup(userDTO);
        final UserDetailsImpl userDetails = userDetailsService.loadUserByUsername(userDTO.getName());
        final String token = jwtTokenUtil.generateToken(userDetails);
        final String refresh = userDetails.getUser().getRefreshToken();
        return ResponseEntity.ok(new JwtResponseDto(token, refresh, userDetails.getUser().getId(), userDetails.getUser().getSubscribeId()));
    }

    @Operation(description = "로그인", method = "POST")
    @PostMapping("/user/signin")
    public ResponseEntity<?> signin(@RequestBody UserRequestDto userDTO) {
        System.out.println(userDTO);
        User user = userService.signIn(userDTO);
        final UserDetailsImpl userDetails = userDetailsService.loadUserByUsername(user.getName());
        final String token = jwtTokenUtil.generateToken(userDetails);
        final String refresh = userDetails.getUser().getRefreshToken();
        System.out.println(userDetails.isEnabled());
        return ResponseEntity.ok(new JwtResponseDto(token, refresh, userDetails.getUser().getId(), userDetails.getUser().getSubscribeId()));
    }

    @Operation(description = "유저 확인", method = "POST")
    @PostMapping("/user/validate")
    public ResponseEntity<?> whoAmI(@RequestBody JwtRequestDto jwtDTO) {
        System.out.println(jwtDTO);
        JwtResponseDto jwtResponseDto = userService.validate(jwtDTO);
        return ResponseEntity.ok(jwtResponseDto);
    }

    @Operation(description = "유저 위치 확인", method = "POST")
    @PostMapping("/user/location")
    public ResponseEntity<?> whereAmI(@RequestBody UserRequestDto userRequestDto) {
        System.out.println(userRequestDto);
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
}
