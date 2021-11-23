package com.sparta.cucumber.user;

import com.sparta.cucumber.models.Timestamped;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name="user")
@NoArgsConstructor
public class User extends Timestamped {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String email;
    @Column
    private String picture;
    @Column
    private Float latitude;
    @Column
    private Float longitude;
    @Column
    private Float star;
    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    @Builder
    public User (String name, String email, String picture, Role role) {
        this.username = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User (UserRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.nickname = requestDto.getNickname();
        this.phoneNumber = requestDto.getPhoneNumber();
        this.email = requestDto.getEmail();
        this.latitude = requestDto.getLatitude();
        this.longitude = requestDto.getLongitude();
        this.star = null;
    }

    public User update(String name, String picture) {
        this.username = name;
        this.picture = picture;
        return this;
    }
}