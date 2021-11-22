package com.sparta.cucumber.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name="review")
@NoArgsConstructor
public class Review extends Timestamped {
    @Id
    @Column(name="reviewId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name="reviewUser")
    private User reviewUser;
    @ManyToOne
    @JoinColumn(name="reviewTarget")
    private User reviewTargetUser;
    @Column
    private Integer score;
    @Column
    private String content;
}
