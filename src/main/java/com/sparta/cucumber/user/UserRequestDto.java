package com.sparta.cucumber.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    private String username;
    private String nickname;
    private String phoneNumber;
    private String email;
    private Float latitude;
    private Float longitude;
    private Float star;
}