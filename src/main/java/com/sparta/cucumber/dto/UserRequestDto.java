package com.sparta.cucumber.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequestDto {
    private String name;
    private String password;
    private String phoneNumber;
    private String email;
    private Double latitude;
    private Double longitude;
    private String picture;

}