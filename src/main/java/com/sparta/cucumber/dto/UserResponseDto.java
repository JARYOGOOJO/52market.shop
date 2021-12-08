package com.sparta.cucumber.dto;

import com.sparta.cucumber.models.User;
import lombok.Data;

@Data
public class UserResponseDto {
    private String name;
    private String email;
    private String picture;

    public UserResponseDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}