package com.sparta.cucumber.models;

import com.sparta.cucumber.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Columns;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name="comment")
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @Column(name="commentId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn
    private User user;
    @Column
    private String content;
    @ManyToOne
    @JoinColumn
    private Article article;

    public Comment(CommentRequestDto requestDto) {
        this.article.setId(requestDto.getArticleId());
        this.user.setId(requestDto.getUserId());
        this.content = requestDto.getContent();
    }
}