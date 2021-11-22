package com.sparta.cucumber.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name="article")
@NoArgsConstructor
public class Article extends Timestamped {
    @Id
    @Column(name="articleId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name="userId")
    private User user;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private String image;
    @Column
    private Float latitude;
    @Column
    private Float longitude;
    @Column
    private String address;
}
