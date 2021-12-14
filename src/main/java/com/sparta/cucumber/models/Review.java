package com.sparta.cucumber.models;

import com.sparta.cucumber.dto.ReviewRequestDto;
import com.sparta.cucumber.dto.UserRequestDto;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@TableGenerator(
        name = "REVIEW_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "REVIEW_SEQ", allocationSize = 30)
@Entity(name = "review")
public class Review extends Timestamped {
    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "REVIEW_GENERATOR")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user")
    private User user;
    private String title;
    private String content;

    @Builder
    public Review(ReviewRequestDto reviewRequestDto, User user) {
        this.user = user;
        this.title = reviewRequestDto.getTitle();
        this.content = reviewRequestDto.getContent();
    }

    public void update(ReviewRequestDto reviewRequestDto) {
        this.title = reviewRequestDto.getTitle();
        this.content = reviewRequestDto.getContent();
    }
}
