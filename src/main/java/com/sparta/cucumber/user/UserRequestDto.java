package com.sparta.cucumber.user;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    private String name;
    private String password;
    private String phoneNumber;
    private String email;
    private Float latitude;
    private Float longitude;
    private Float star;
}