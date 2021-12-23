package com.sparta.cucumber.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequestDto {
    private String name;
    private String password;
    private String phoneNumber;
    private String email;
    private Double latitude;
    private Double longitude;
    private String picture;

}