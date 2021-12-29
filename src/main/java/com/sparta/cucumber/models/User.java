package com.sparta.cucumber.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sparta.cucumber.dto.UserRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;

import static org.springframework.web.util.HtmlUtils.htmlEscape;

@NamedEntityGraph(
        name = "user"
)

@Getter
@NoArgsConstructor
@TableGenerator(
        name = "USER_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "USER_SEQ", allocationSize = 30)
@Entity(name = "user")
@JsonIgnoreProperties({"socialId", "password", "phoneNumber", "latitude", "longitude", "star", "refreshToken"})
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "USER_GENERATOR")
    private Long id;
    @Column(nullable = false)
    private String name;
    private Long socialId;
    @Column(nullable = false)
    @Email
    private String email;
    private String picture;
    private String password;
    private String phoneNumber;
    private Double latitude;
    private Double longitude;
    private Double star;
    private String subscribeId;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String refreshToken;

    @Builder
    public User(String name, String email,
                String picture, String encodedPassword,
                String phoneNumber, Long kakaoId, String refreshToken) {
        this.name = htmlEscape(name);
        this.email = email;
        this.picture = picture;
        this.password = encodedPassword;
        this.phoneNumber = phoneNumber;
        this.star = 0.0;
        this.role = Role.USER;
        this.socialId = kakaoId;
        this.subscribeId = RandomStringUtils.random(16, true, true);
        this.refreshToken = refreshToken;
    }

    public User(String nickname, String encodedPassword, String email, Role role, Long kakaoId) {
        this.name = htmlEscape(nickname);
        this.email = email;
        this.password = encodedPassword;
        this.role = role;
        this.socialId = kakaoId;
        this.star = 0.0;
        this.subscribeId = RandomStringUtils.random(16, true, true);
    }

    public User updateKakao(String nickname, String encodedPassword, String email, Role role, Long kakaoId) {
        this.name = nickname;
        this.email = email;
        this.password = encodedPassword;
        this.role = role;
        this.socialId = kakaoId;
        return this;
    }

    public User updateMyPage(UserRequestDto userDTO) {
        this.picture = userDTO.getPicture();
        this.name = userDTO.getName();
        this.phoneNumber = userDTO.getPhoneNumber();
        this.password = userDTO.getPassword();
        return this;
    }

    public User updateLocation(UserRequestDto userDTO) {
        this.latitude = userDTO.getLatitude();
        this.longitude = userDTO.getLongitude();
        return this;
    }

    public User refresh(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }
}