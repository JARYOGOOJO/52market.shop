package com.sparta.cucumber.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sparta.cucumber.dto.CommentRequestDto;
import com.sparta.cucumber.user.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@Entity(name="comment")
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,property = "id")
public class Comment extends Timestamped {
    @Id
    @Column(name="commentId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="user")
    private User user;
    private String content;
    @ManyToOne
    @JoinColumn(name="article")
    private Article article;
}