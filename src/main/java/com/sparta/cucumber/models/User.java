package com.sparta.cucumber.models;

import com.sparta.cucumber.dto.UserRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

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
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "USER_GENERATOR")
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
    private String subscribeId;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String name, String email,
                String picture, String encodedPassword,
                String phoneNumber, Double latitude, Double longitude) {
        this.name = htmlEscape(name);
        this.email = email;
        this.picture = picture;
        this.password = encodedPassword;
        this.phoneNumber = phoneNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.star = 0.0;
        this.role = Role.USER;
        this.subscribeId = UUID.randomUUID().toString();
    }

    public User(String nickname, String encodedPassword, String email, Role role, Long kakaoId) {
        this.name = htmlEscape(nickname);
        this.email = email;
        this.password = encodedPassword;
        this.role = role;
        this.socialId = kakaoId;
        this.star = 0.0;
        this.subscribeId = UUID.randomUUID().toString();
    }

    public User updateKakao(String nickname, String encodedPassword, String email, Role role, Long kakaoId) {
        this.name = nickname;
        this.email = email;
        this.password = encodedPassword;
        this.role = role;
        this.socialId = kakaoId;
        return this;
    }


    public void update(UserRequestDto userDTO) {
        this.picture = userDTO.getPicture();
        this.name = userDTO.getName();
        this.phoneNumber = userDTO.getPhoneNumber();
        this.password = userDTO.getPassword();
    }
}