package com.sparta.cucumber.models;

import com.sparta.cucumber.dto.ArticleRequestDto;
import com.sparta.cucumber.repository.UserRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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
    @JoinColumn
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

    public Article(ArticleRequestDto requestDto) {

        this.user.setId(requestDto.getUserId());
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.image = requestDto.getImage();
        this.address = requestDto.getAddress();
        this.latitude = requestDto.getLatitude();
        this.longitude = requestDto.getLongitude();
    }
}
