package com.sparta.cucumber.models;

import com.sparta.cucumber.dto.ReviewRequestDto;
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
    public Review(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void update(ReviewRequestDto reviewRequestDto) {
        this.title = reviewRequestDto.getTitle();
        this.content = reviewRequestDto.getContent();
    }
}
