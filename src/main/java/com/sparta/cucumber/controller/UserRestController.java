package com.sparta.cucumber.controller;

import com.sparta.cucumber.dto.JwtResponseDto;
import com.sparta.cucumber.dto.SocialLoginDto;
import com.sparta.cucumber.dto.UserRequestDto;
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
        String username = userService.kakaoLogin(socialLoginDto.getToken());
        final UserDetailsImpl userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        JwtResponseDto result = new JwtResponseDto(token, userDetails.getUser().getId(), userDetails.getUser().getSubscribeId());
        System.out.println(userDetails.isEnabled());
        return ResponseEntity.ok(result);
    }

    @Operation(description = "회원가입", method = "POST")
    @PostMapping("/user/signup")
    public ResponseEntity<?> signup(@RequestBody UserRequestDto userDTO) throws Exception {
        System.out.println(userDTO);
        userService.signup(userDTO);
        //        Authentication auth = authenticate(userDTO.getEmail(), userDTO.getPassword());
        final UserDetailsImpl userDetails = userDetailsService.loadUserByEmail(userDTO.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        //        System.out.println(auth.isAuthenticated());
        return ResponseEntity.ok(new JwtResponseDto(token, userDetails.getUser().getId(), userDetails.getUser().getSubscribeId()));
    }

    @Operation(description = "로그인", method = "POST")
    @PostMapping("/user/signin")
    public ResponseEntity<?> signin(@RequestBody UserRequestDto userDTO) throws Exception {
        System.out.println(userDTO);
        userService.signin(userDTO);
        //        Authentication auth = authenticate(userDTO.getEmail(), userDTO.getPassword());
        final UserDetailsImpl userDetails = userDetailsService.loadUserByEmail(userDTO.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        System.out.println(userDetails.isEnabled());
        //        System.out.println(auth.isAuthenticated());
        return ResponseEntity.ok(new JwtResponseDto(token, userDetails.getUser().getId(), userDetails.getUser().getSubscribeId()));
    }

//    @Operation(description = "유저 프로필사진 변경", method = "PUT")
//    @PutMapping("/user/update")
//    public ResponseEntity<UserResponseDto> updateProfileImage(UserRequestDto userDTO,
//                                                              @ModelAttribute MultipartFile profile) throws IOException {
//        String profileImage = s3Uploader.upload(userDTO, profile, "Profile");
//        User user = userService.updateProfileImage(userDTO, profileImage);
//        UserResponseDto updateUser = new UserResponseDto(user);
//        return ResponseEntity.ok().body(updateUser);
//    }

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
