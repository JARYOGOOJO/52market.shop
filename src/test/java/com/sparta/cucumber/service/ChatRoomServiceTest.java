package com.sparta.cucumber.service;

import com.sparta.cucumber.chat.ChatRequestDto;
import com.sparta.cucumber.chat.ChatRoom;
import com.sparta.cucumber.chat.ChatRoomRepository;
import com.sparta.cucumber.chat.ChatRoomService;
import com.sparta.cucumber.error.CustomException;
import com.sparta.cucumber.models.User;
import com.sparta.cucumber.repository.UserRepository;
import org.junit.Rule;
import org.junit.jupiter.api.*;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.junit.Assert.assertThrows;

@SpringBootTest
class ChatRoomServiceTest {

    @Autowired
    private ChatRoomRepository chatRoomRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChatRoomService chatRoomService;
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @BeforeEach
    void setUp() {
        System.out.println("------테스트시작------");
    }

    @AfterEach
    void tearDown() {
        System.out.println("------테스트끄읏------");
    }

    @Nested
    @DisplayName(value = "방 만들기 테스트")
    class CreateRoomTest {

        @Test
        @Order(1)
        @Rollback
        @Transactional
        void CreateSuccess() {
            ChatRequestDto chatRequestDto = new ChatRequestDto();
            User host = User.builder()
                    .name("테스트용계정")
                    .email("account@test.test")
                    .build();
            System.out.println(host);
            userRepository.save(host);
            chatRequestDto.setUserId(host.getId());
            chatRequestDto.setTitle("새로운 대화방 테스트");
            System.out.println(chatRequestDto);
            ChatRoom chatRoom = chatRoomService.createRoom(chatRequestDto);
            Assertions.assertNotNull(chatRoom);
        }

        @Test
        @Order(2)
        @Rollback
        @Transactional
        void CreateFailCase() {
            ChatRequestDto chatRequestDto = new ChatRequestDto();
            chatRequestDto.setTitle(null);
            chatRequestDto.setUserId(999L);
            System.out.println(chatRequestDto);
            assertThrows(CustomException.class, () -> chatRoomService.createRoom(chatRequestDto));
        }
    }

    @Nested
    @DisplayName(value = "방 입장하기 테스트")
    class EnterRoomTest {

        @Test
        @Order(3)
        @Rollback
        @Transactional
        void EnterSuccess() {
            ChatRequestDto chatRequestDto = new ChatRequestDto();

        }

        @Test
        @Order(4)
        @Rollback
        @Transactional
        void EnterFailCase() {
            ChatRequestDto chatRequestDto = new ChatRequestDto();

        }
    }

    @Nested
    @DisplayName(value = "방 나가기 테스트")
    class ExitRoomTest {

        @Test
        @Order(5)
        @Rollback
        @Transactional
        void ExitSuccess() {
            ChatRequestDto chatRequestDto = new ChatRequestDto();

        }

        @Test
        @Order(6)
        @Rollback
        @Transactional
        void ExitFailCase() {
            ChatRequestDto chatRequestDto = new ChatRequestDto();

        }
    }
}