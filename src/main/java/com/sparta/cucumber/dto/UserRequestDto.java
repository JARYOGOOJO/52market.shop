package com.sparta.cucumber.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserRequestDto {
    private String name;
    @JsonIgnore(true)
    private String password;
    @JsonIgnore(true)
    private String phoneNumber;
    private String email;
    @JsonIgnore(true)
    private Double latitude;
    @JsonIgnore(true)
    private Double longitude;
    private String picture;
}