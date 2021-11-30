package com.sparta.cucumber.service;

import com.sparta.cucumber.dto.MeetRequestDto;
import com.sparta.cucumber.models.Article;
import com.sparta.cucumber.models.Meet;
import com.sparta.cucumber.models.User;
import com.sparta.cucumber.repository.ArticleRepository;
import com.sparta.cucumber.repository.MeetRepository;
import com.sparta.cucumber.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

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
        if (commenter != article.getUser()) {
            throw new NoSuchElementException("자신의 댓글에 거래를 시작할 수 없어요!");
        }
        Meet meet = Meet
                .builder()
                .article(article)
                .commenter(commenter)
                .build();
        return meetRepository.save(meet);
    }

    public List<Meet> readMeets(Long articleId) {
        Article article = articleRepository
                .findById(articleId)
                .orElseThrow(() -> new NullPointerException("게시물이 존재하지 않습니다."));
        return meetRepository.findAllByArticle(article);
    }

    public Long deleteMeet(@RequestBody MeetRequestDto meetDTO) {
        meetRepository.deleteById(meetDTO.getId());
        return meetDTO.getId();
    }
}
