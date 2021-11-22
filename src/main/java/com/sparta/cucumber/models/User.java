package com.sparta.cucumber.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.geo.GeoResult;

import javax.persistence.*;

@Getter
@Setter
@Entity(name="user")
@NoArgsConstructor
public class User extends Timestamped {
    @Id
    @Column(name="userId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String username;
    @Column
    private String nickname;
    @Column
    private String phoneNumber;
    @Column
    private String Email;
    @Column
    private Float latitude;
    @Column
    private Float longitude;
    @Column
    private Float star;
}