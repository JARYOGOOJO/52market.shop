package com.sparta.cucumber.user;

import com.sparta.cucumber.models.Article;
import com.sparta.cucumber.models.Comment;
import com.sparta.cucumber.repository.ArticleRepository;
import com.sparta.cucumber.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
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
