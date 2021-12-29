package com.sparta.cucumber.security;

import com.sparta.cucumber.error.CustomException;
import com.sparta.cucumber.models.User;
import com.sparta.cucumber.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.sparta.cucumber.error.ErrorCode.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        return new UserDetailsImpl(user);
    }
}