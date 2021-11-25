package com.sparta.cucumber.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sparta.cucumber.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "article")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class Article extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "userId")
    private User user;
    @Column(length = 500, nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    private String image;
    private Float latitude;
    private Float longitude;
    @OneToMany(mappedBy = "article")
    private List<Comment> comments;

    @Builder
    public Article(User user, String title,
                   String content, String image,
                   Float latitude, Float longitude) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
