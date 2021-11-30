package com.sparta.cucumber.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@TableGenerator(
        name = "MEET_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "MEET_SEQ", allocationSize = 50)
@Entity(name = "meet")
public class Meet extends Timestamped {
    @Id
    @Column(name = "meet_id")
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "MEET_GENERATOR")
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