package com.sparta.cucumber.models;

import com.sparta.cucumber.dto.ArticleRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static org.springframework.web.util.HtmlUtils.htmlEscape;

@Getter
@NoArgsConstructor
@Entity(name = "article")
@TableGenerator(
        name = "ARTICLE_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "ARTICLE_SEQ", allocationSize = 30)
public class Article extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ARTICLE_GENERATOR")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;
    @Column(length = 500, nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    private String imagePath;
    private String imageName;
    private Double latitude;
    private Double longitude;

    @Builder
    public Article(User user, String title,
                   String content, String imagePath,
                   String imageName, Double latitude,
                   Double longitude) {
        this.user = user;
        this.title = htmlEscape(title);
        this.content = htmlEscape(content);
        this.imagePath = imagePath;
        this.imageName = imageName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void addComment(Comment comment) {
        this.commentList.add(comment);
        comment = Comment.builder()
                .article(this)
                .build();
    }


    public Article update(ArticleRequestDto requestDto) {
        this.content = requestDto.getContent();
        return this;
    }
}
