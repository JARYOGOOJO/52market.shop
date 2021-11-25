package com.sparta.cucumber.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MeetRequestDto {

    private Long commenterId;
    private Long articleId;
}