package com.sparta.cucumber.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "user")
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    private String picture;
    private String password;
    private String phoneNumber;
    private Double latitude;
    private Double longitude;
    private Double star;
    @OneToMany(mappedBy = "user")
    private List<Article> articles;
    @OneToMany(mappedBy = "user")
    private List<Comment> comments;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String name, String email,
                String picture, String password,
                String phoneNumber, Double latitude, Double longitude) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}