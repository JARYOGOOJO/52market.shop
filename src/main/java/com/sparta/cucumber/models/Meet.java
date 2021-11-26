package com.sparta.cucumber.models;

import com.sparta.cucumber.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "meet")
public class Meet extends Timestamped {
    @Id
    @Column(name = "meet_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "comment_user")
    private User commenter;
    @ManyToOne
    @JoinColumn(name = "article")
    private Article article;

    @Builder
    public Meet(User commenter, Article article) {
        this.commenter = commenter;
        this.article = article;
    }
}