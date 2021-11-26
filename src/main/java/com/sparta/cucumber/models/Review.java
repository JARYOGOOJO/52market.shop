package com.sparta.cucumber.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "review")
public class Review extends Timestamped {
    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "review_user")
    private User reviewUser;
    @ManyToOne
    @JoinColumn(name = "review_target")
    private User reviewTargetUser;
    private Integer score;
    private String content;

    @Builder
    public Review(User from, User to, Integer star, String content) {
        this.reviewUser = from;
        this.reviewTargetUser = to;
        this.score = star;
        this.content = content;
    }
}
