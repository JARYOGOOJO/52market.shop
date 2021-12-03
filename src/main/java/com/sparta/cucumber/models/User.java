package com.sparta.cucumber.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@TableGenerator(
        name = "USER_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "USER_SEQ", allocationSize = 50)
@Entity(name = "user")
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "USER_GENERATOR")
    private Long id;
    @Column(nullable = false)
    private String name;
    private Long socialId;
    @Column(nullable = false)
    private String email;
    private String picture;
    private String password;
    private String phoneNumber;
    private Double latitude;
    private Double longitude;
    private Double star;
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

    public User(String nickname, String encodedPassword, String email, Role role, Long kakaoId) {
        this.name = nickname;
        this.email = email;
        this.password = encodedPassword;
        this.role = role;
        this.socialId = kakaoId;
    }
}