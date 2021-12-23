package com.sparta.cucumber.service;

import com.sparta.cucumber.chat.ChatRequestDto;
import com.sparta.cucumber.chat.ChatRoom;
import com.sparta.cucumber.chat.ChatRoomRepository;
import com.sparta.cucumber.chat.ChatRoomService;
import com.sparta.cucumber.error.CustomException;
import com.sparta.cucumber.models.User;
import com.sparta.cucumber.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertThrows;

@SpringBootTest
class ChatRoomServiceTest {

    @Autowired
    private ChatRoomRepository chatRoomRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChatRoomService chatRoomService;

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
        void CreateExceptionCase() {
            ChatRequestDto chatRequestDto = new ChatRequestDto();
            chatRequestDto.setTitle(null);
            chatRequestDto.setUserId(999L);
            System.out.println(chatRequestDto);
            CustomException exception = assertThrows(
                    CustomException.class,
                    () -> chatRoomService.createRoom(chatRequestDto));
            String exceptionMessage = exception.getErrorCode().getDetail();
            String expectedMessage = "해당 유저 정보를 찾을 수 없습니다.";
            Assertions.assertTrue(exceptionMessage.contains(expectedMessage));
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
            User host = User.builder()
                    .name("테스트용계정")
                    .email("account@test.test")
                    .build();
            System.out.println(host);
            userRepository.save(host);
            chatRequestDto.setUserId(host.getId());
            chatRequestDto.setTitle("대화방 입장하기 테스트");
            System.out.println(chatRequestDto);
            ChatRoom chatRoom = chatRoomService.createRoom(chatRequestDto);
            System.out.println(chatRoom);
            User guest = User.builder()
                    .name("<이름>🥒🥒")
                    .email("email@address.com")
                    .build();
            userRepository.save(guest);
            System.out.println(guest);
            chatRoomRepository.save(chatRoom.enter(guest));
            System.out.println(chatRoom);
            List<ChatRoom> guestHouses = chatRoomRepository.findByGuest(guest).orElse(null);
            List<ChatRoom> hostRooms = chatRoomRepository.findByHost(host).orElse(null);
            assert hostRooms != null;
            Assertions.assertTrue(hostRooms.contains(chatRoom));
            assert guestHouses != null;
            Assertions.assertTrue(guestHouses.contains(chatRoom));
        }

        @Test
        @Order(4)
        @Rollback
        @Transactional
        void EnterExceptionCase() {
            ChatRequestDto chatRequestDto = new ChatRequestDto();
            User host = User.builder()
                    .name("<이름>🥒🥒")
                    .email("email@address.com")
                    .build();
            userRepository.save(host);
            System.out.println(host);
            chatRequestDto.setUserId(host.getId());
            chatRequestDto.setTitle("새로운 대화방 입장 테스트");
            String subscribeId = RandomStringUtils.random(16, true, true);
            chatRequestDto.setRoomSubscribeId(subscribeId);
            System.out.println(chatRequestDto);
            ChatRoom chatRoom = chatRoomService.createRoom(chatRequestDto);
            CustomException exception = assertThrows(
                    CustomException.class,
                    () -> chatRoomService.enterRoom(chatRequestDto));
            System.out.println(exception.getErrorCode().getDetail());
            System.out.println(exception.getErrorCode().getHttpStatus());
            chatRoomRepository.save(chatRoom.enter(host));
            Assertions.assertNotEquals(chatRoom.getRoomSubscribeId(), subscribeId);
            Assertions.assertNull(chatRoom.getGuest());
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
            User host = User.builder()
                    .name("<이름>🥒🥒")
                    .email("email@address.com")
                    .build();
            userRepository.save(host);
            System.out.println(host);
            chatRequestDto.setUserId(host.getId());
            chatRequestDto.setTitle("새로운 대화방 입장 테스트");
            System.out.println(chatRequestDto);
            ChatRoom chatRoom = chatRoomService.createRoom(chatRequestDto);
            User guest = User.builder()
                    .name("<이름>🥒🥒")
                    .email("email@address.com")
                    .build();
            userRepository.save(guest);
            System.out.println(guest);
            chatRoomRepository.save(chatRoom.enter(guest));
            String subscribeId = chatRoom.getRoomSubscribeId();
            chatRequestDto.setRoomSubscribeId(subscribeId);
            chatRoomService.exitRoom(chatRequestDto);
            Assertions.assertNull(chatRoom.getHost());
        }

        @Test
        @Order(6)
        @Rollback
        @Transactional
        void ExitExceptionCase() {
            ChatRequestDto chatRequestDto = new ChatRequestDto();
            User host = User.builder()
                    .name("<이름>🥒🥒")
                    .email("email@address.com")
                    .build();
            userRepository.save(host);
            System.out.println(host);
            chatRequestDto.setUserId(host.getId());
            chatRequestDto.setTitle("새로운 대화방 입장 테스트");
            System.out.println(chatRequestDto);
            ChatRoom chatRoom = chatRoomService.createRoom(chatRequestDto);
            User guest = User.builder()
                    .name("<이름>🥒🥒")
                    .email("email@address.com")
                    .build();
            userRepository.save(guest);
            System.out.println(guest);
            chatRoomRepository.save(chatRoom.enter(guest));
            String subscribeId = chatRoom.getRoomSubscribeId();
            chatRequestDto.setRoomSubscribeId(subscribeId);
            chatRoom = chatRoomService.exitRoom(chatRequestDto);
            Assertions.assertNull(chatRoom.getHost());
            Assertions.assertFalse(chatRoom.isActive());
            Assertions.assertThrows(CustomException.class,
                    () -> chatRoomService.exitRoom(chatRequestDto));
        }
    }
}