package com.sparta.cucumber.models;

import com.sparta.cucumber.dto.MeetRequestDto;
import com.sparta.cucumber.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name="meet")
@NoArgsConstructor
public class Meet extends Timestamped {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn
    private User commenter;
    @ManyToOne
    @JoinColumn
    private Article article;
}