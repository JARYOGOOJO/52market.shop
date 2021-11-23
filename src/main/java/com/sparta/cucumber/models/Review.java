package com.sparta.cucumber.models;

import com.sparta.cucumber.dto.ReviewRequestDto;
import com.sparta.cucumber.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name="review")
@NoArgsConstructor
public class Review extends Timestamped {
    @Id
    @Column(name="reviewId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn
    private User reviewUser;
    @ManyToOne
    @JoinColumn
    private User reviewTargetUser;
    @Column
    private Integer score;
    @Column
    private String content;

    public Review (ReviewRequestDto requestDto) {
        this.reviewUser.setId(requestDto.getReviewUserid());
        this.reviewTargetUser.setId(requestDto.getReviewTargetUserId());
        this.score = requestDto.getScore();
        this.content = requestDto.getContent();
    }
}
