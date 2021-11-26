package com.sparta.cucumber.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    private String name;
    private String password;
    private String phoneNumber;
    private String email;
    private Double latitude;
    private Double longitude;
    private Double star;
}