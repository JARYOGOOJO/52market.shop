package com.sparta.cucumber.service;

import com.sparta.cucumber.models.ChatRoom;
import com.sparta.cucumber.repository.ChatRoomRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class ChatServiceTest {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Test
    @Transactional
    void getRoom() {
        ChatRoom chatRoom = ChatRoom
                .builder()
                .title("roomTitle")
                .roomSubscribeId(UUID.randomUUID().toString())
                .isActive(false)
                .build();
        ChatRoom saveRoom = chatRoomRepository.save(chatRoom);

        ChatRoom chatRoom2 = ChatRoom
                .builder()
                .title("roomTitle2")
                .roomSubscribeId(UUID.randomUUID().toString())
                .isActive(false)
                .build();
        ChatRoom saveRoom2 = chatRoomRepository.save(chatRoom2);

        Optional<ChatRoom> findRoom = chatRoomRepository.findById(saveRoom.getId());
        if(findRoom.isPresent()){
            System.out.println("확인!");
            assertThat(saveRoom).isEqualTo(findRoom.get());
        }
        assertThat(saveRoom).isEqualTo(findRoom.get());

        Optional<ChatRoom> findRoom2 = chatRoomRepository.findById(saveRoom2.getId());
        if(findRoom2.isPresent()){
            System.out.println("확인!");
            assertThat(saveRoom2).isEqualTo(findRoom2.get());
        }
        assertThat(saveRoom2).isEqualTo(findRoom2.get());
    }

    @Test
    @Transactional
    void getRooms() {
        ChatRoom chatRoom = ChatRoom
                .builder()
                .title("roomTitle")
                .roomSubscribeId(UUID.randomUUID().toString())
                .isActive(false)
                .build();
        ChatRoom saveRoom = chatRoomRepository.save(chatRoom);

        ChatRoom chatRoom2 = ChatRoom
                .builder()
                .title("roomTitle2")
                .roomSubscribeId(UUID.randomUUID().toString())
                .isActive(false)
                .build();
        ChatRoom saveRoom2 = chatRoomRepository.save(chatRoom2);

        ChatRoom chatRoom3 = ChatRoom
                .builder()
                .title("roomTitle2")
                .roomSubscribeId(UUID.randomUUID().toString())
                .isActive(false)
                .build();
        ChatRoom saveRoom3 = chatRoomRepository.save(chatRoom3);

        ChatRoom[] chatRooms = {saveRoom,saveRoom2,saveRoom3};
        List<ChatRoom> chatRoomList = chatRoomRepository.findAll();
        for(int i=0;i<chatRoomList.size();i++){
            System.out.println("확인!");
            assertThat(chatRooms[i]).isEqualTo(chatRoomList.get(i));
        }
        System.out.println("size : "+chatRoomList.size());
        assertThat(chatRoomList.size()).isEqualTo(3);


    }

    @Test
    @Transactional
    void createRoom() {

        ChatRoom chatRoom = ChatRoom
                .builder()
                .title("roomTitle")
                .roomSubscribeId(UUID.randomUUID().toString())
                .isActive(false)
                .build();
        ChatRoom saveRoom = chatRoomRepository.save(chatRoom);
        Optional<ChatRoom> findRoom = chatRoomRepository.findById(saveRoom.getId());
        if(findRoom.isPresent()){
            System.out.println("확인!");
            assertThat(saveRoom).isEqualTo(findRoom.get());
        }
        assertThat(saveRoom).isEqualTo(findRoom.get());
    }
}