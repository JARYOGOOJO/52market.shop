package com.sparta.cucumber.models;

import com.sparta.cucumber.dto.ReviewRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static org.springframework.web.util.HtmlUtils.htmlEscape;

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
        this.title = htmlEscape(title);
        this.content = htmlEscape(content);
        this.user = user;
    }

    public void update(ReviewRequestDto reviewRequestDto) {
        this.title = htmlEscape(reviewRequestDto.getTitle());
        this.content = htmlEscape(reviewRequestDto.getContent());
    }
}
