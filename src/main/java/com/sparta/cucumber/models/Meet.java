package com.sparta.cucumber.models;

import com.sparta.cucumber.dto.MeetRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@Getter
@Setter
@Entity(name="meet")
@NoArgsConstructor
public class Meet extends Timestamped {
    @Id
    @Column(name="meetId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn
    private User commenter;
    @ManyToOne
    @JoinColumn
    private Article article;

    public Meet (MeetRequestDto requestDto) {
        this.commenter.setId(requestDto.getCommenterId());
        this.article.setId(requestDto.getArticleId());
    }
}