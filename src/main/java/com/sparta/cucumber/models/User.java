package com.sparta.cucumber.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
}