package com.sparta.cucumber.user;

import com.sparta.cucumber.repository.ArticleRepository;
import com.sparta.cucumber.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User signup(UserRequestDto userDTO){
        User user = User
                .builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .build();
        userRepository.save(user);
        return user;
    }
}
