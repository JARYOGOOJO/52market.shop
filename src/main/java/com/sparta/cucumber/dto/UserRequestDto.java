package com.sparta.cucumber.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    private String username;
    private String nickname;
    private String phoneNumber;
    private String Email;
    private Float latitude;
    private Float longitude;
    private Float star;
}