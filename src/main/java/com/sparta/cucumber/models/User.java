package com.sparta.cucumber.models;

import com.sparta.cucumber.dto.UserRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.geo.GeoResult;

import javax.persistence.*;

@Getter
@Setter
@Entity(name="user")
@NoArgsConstructor
public class User extends Timestamped {
    @Id
    @Column(name="userId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String username;
    @Column
    private String nickname;
    @Column
    private String phoneNumber;
    @Column
    private String email;
    @Column
    private Float latitude;
    @Column
    private Float longitude;
    @Column
    private Float star;

    public User(UserRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.nickname = requestDto.getNickname();
        this.phoneNumber = requestDto.getPhoneNumber();
        this.email = requestDto.getEmail();
        this.latitude = requestDto.getLatitude();
        this.longitude = requestDto.getLongitude();
        this.star = null;
    }
}