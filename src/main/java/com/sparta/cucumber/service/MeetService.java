package com.sparta.cucumber.service;

import com.sparta.cucumber.dto.MeetRequestDto;
import com.sparta.cucumber.models.Article;
import com.sparta.cucumber.models.Meet;
import com.sparta.cucumber.repository.ArticleRepository;
import com.sparta.cucumber.repository.MeetRepository;
import com.sparta.cucumber.user.User;
import com.sparta.cucumber.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class MeetService {
    private final MeetRepository meetRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Transactional
    public Meet create (MeetRequestDto requestDto){
        Article article = articleRepository
                .findById(requestDto.getArticleId())
                .orElseThrow(
                () -> new NullPointerException("게시물이 존재하지 않습니다.")
        );
        User commenter = userRepository
                .findById(requestDto.getCommenterId())
                .orElseThrow(
                () -> new NullPointerException("작성자가 존재하지 않습니다.")
        );
        assert commenter != article.getUser();
        Meet meet = Meet
                .builder()
                .article(article)
                .commenter(commenter)
                .build();
        return meetRepository.save(meet);
    }
}
