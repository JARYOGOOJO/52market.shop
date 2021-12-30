package com.sparta.cucumber.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static org.springframework.web.util.HtmlUtils.htmlEscape;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "comment")
@TableGenerator(
        name = "COMMENT_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "COMMENT_SEQ", allocationSize = 30)
public class Comment extends Timestamped {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "COMMENT_GENERATOR")
    private Long id;
    private String content;
    @ManyToOne
    @JoinColumn(name = "user")
    private User user;
    @ManyToOne
    @JoinColumn(name = "article")
    @JsonIgnore
    private Article article;

    @Builder
    public Comment(User user, Article article, String content) {
        this.user = user;
        this.article = article;
        this.content = htmlEscape(content);
    }
}