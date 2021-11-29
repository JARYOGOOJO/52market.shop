package com.sparta.cucumber.service;

import com.sparta.cucumber.models.User;
import com.sparta.cucumber.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void signup() {
        User user = User
                .builder()
                .name("test")
                .password("111")
                .phoneNumber("010-1111-2222")
                .email("test@naver.com")
                .latitude(0.0)
                .longitude(0.0)
                .build();
        User saveUser = userRepository.save(user);
        System.out.println("saveUser!");
        System.out.println(user.getName());
        System.out.println(user.getPassword());
        System.out.println(user.getPhoneNumber());
        System.out.println(user.getEmail());
        System.out.println(user.getLatitude());
        System.out.println(user.getLongitude());

        assertThat(user).isEqualTo(saveUser);

        // 중복체크
        User user2 = User
                .builder()
                .name("test1")
                .password("111")
                .phoneNumber("010-1111-2222")
                .email("test@naver.com")
                .latitude(0.0)
                .longitude(0.0)
                .build();

        assertThat(saveUser.getName()).isNotEqualTo(user2.getName());

        User saveUser2 = userRepository.save(user2);

    }

    @Test
    @Transactional
    void signin() {
        User user = User
                .builder()
                .name("test")
                .password("111")
                .phoneNumber("010-1111-2222")
                .email("test@naver.com")
                .latitude(0.0)
                .longitude(0.0)
                .build();
        User saveUser = userRepository.save(user);

        User findUser = userRepository.findByNameAndPassword(saveUser.getName(),saveUser.getPassword());
        assertThat(saveUser).isEqualTo(findUser);
    }
}