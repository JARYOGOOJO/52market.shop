package com.sparta.cucumber.user;

import com.sparta.cucumber.dto.CommentRequestDto;
import com.sparta.cucumber.models.Article;
import com.sparta.cucumber.models.Comment;
import com.sparta.cucumber.repository.ArticleRepository;
import com.sparta.cucumber.repository.CommentRepository;
import com.sparta.cucumber.user.User;
import com.sparta.cucumber.user.UserRepository;
import com.sparta.cucumber.user.UserRequestDto;
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
    public User upload (Long userId) {
        List<Article> articles = articleRepository.findAllByUser_Id(userId);
        List<Comment> comments = commentRepository.findAllByUser_Id(userId);
        User user = User
                .builder()
                .articles(articles)
                .comments(comments)
                .build();
        return userRepository.save(user);
    }

    @Transactional
    public User signup(UserRequestDto userDTO){
        User user = User
                .builder()
                .username(userDTO.getUsername())
                .nickname(userDTO.getNickname())
                .phoneNumber(userDTO.getPhoneNumber()).build();
        userRepository.save(user);
        return user;
    }
}
