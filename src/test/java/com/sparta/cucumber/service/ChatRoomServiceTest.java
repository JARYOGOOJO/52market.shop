package com.sparta.cucumber.service;

import com.sparta.cucumber.chat.ChatRoomRepository;
import com.sparta.cucumber.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ChatRoomServiceTest {

    @Autowired
    private ChatRoomRepository chatRoomRepository;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        System.out.println("------테스트시작------");
    }

    @AfterEach
    void tearDown() {
        System.out.println("------테스트끄읏------");
    }

    @Test
    void createRoom() {
    }

    @Test
    void enterRoom() {
    }

    @Test
    void exitRoom() {
    }
}