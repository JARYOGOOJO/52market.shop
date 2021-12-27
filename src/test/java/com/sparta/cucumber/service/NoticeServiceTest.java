package com.sparta.cucumber.service;

import com.sparta.cucumber.chat.*;
import com.sparta.cucumber.error.CustomException;
import com.sparta.cucumber.models.User;
import com.sparta.cucumber.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class NoticeServiceTest {

    @Autowired
    private NoticeService noticeService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Nested
    @DisplayName("메세지 보내기 테스트")
    class MessageTest {

        User user;
        ChatRoom chatRoom;

        @BeforeEach
        void signIn() {
            user = User.builder()
                    .name("<이름>🥒🥒")
                    .email("email@address.com")
                    .build();
            userRepository.save(user);
            chatRoom = ChatRoom.builder()
                    .title("새로운 대화방 1234")
                    .host(user)
                    .build();
            chatRoomRepository.save(chatRoom);
        }

        @AfterEach
        void clear() {
            userRepository.delete(user);
            chatRoomRepository.delete(chatRoom);
        }

        @Test
        @Rollback
        @Transactional
        @DisplayName("메세지 보내기 성공")
        void success() {
            ChatRequestDto chatRequestDto = new ChatRequestDto();
            chatRequestDto.setUserId(user.getId());
            String roomId = chatRoom.getRoomSubscribeId();
            chatRequestDto.setRoomSubscribeId(roomId);
            String content = "안녕하세요. 반갑습니다.";
            chatRequestDto.setContent(content);
            System.out.println(chatRequestDto);
            Notice notice = noticeService.message(chatRequestDto);
            System.out.println(notice);
            assertEquals(notice.getContent(), content);
            assertEquals(notice.getType(), NoticeType.MESSAGE);
            assertEquals(notice.getChatRoom().getRoomSubscribeId(), chatRoom.getRoomSubscribeId());
        }

        @Test
        @Rollback
        @Transactional
        @DisplayName("메세지 보내기 실패")
        void fail() {
            ChatRequestDto chatRequestDto = new ChatRequestDto();
            chatRequestDto.setUserId(user.getId());
            String roomId = RandomStringUtils.random(16, true, true);
            System.out.println(roomId);
            chatRequestDto.setRoomSubscribeId(roomId);
            String content = "안녕하세요. 저도 만나서 반갑습니다.";
            chatRequestDto.setContent(content);
            System.out.println(chatRequestDto);
            CustomException exception = assertThrows(CustomException.class,
                    () -> noticeService.message(chatRequestDto));
            assertEquals(
                    "해당 대화방 정보를 찾을 수 없습니다.",
                    exception.getErrorCode().getDetail());
            assertEquals(
                    HttpStatus.NOT_FOUND,
                    exception.getErrorCode().getHttpStatus()
            );
            System.out.println(exception.getErrorCode().getHttpStatus());
            System.out.println(exception.getErrorCode().getDetail());
        }
    }

    @Nested
    @DisplayName("대화방 초대 테스트")
    class InviteTest {

        User sender;
        User subscriber;
        ChatRequestDto chatRequestDto;

        @BeforeEach
        void setting() {
            sender = User.builder()
                    .name("보내는사람🥒🥒")
                    .email("address@email.com")
                    .build();
            userRepository.save(sender);
            subscriber = User.builder()
                    .name("받는사람🥒🥒")
                    .email("email@address.com")
                    .build();
            userRepository.save(subscriber);
            chatRequestDto = new ChatRequestDto();
            chatRequestDto.setUserId(sender.getId());
            chatRequestDto.setTargetId(subscriber.getId());
        }

        @AfterEach
        void reset() {
            userRepository.delete(sender);
            userRepository.delete(subscriber);
        }

        @Test
        @Rollback
        @Transactional
        @DisplayName("새로운 대화 요청 성공")
        void success() {
            chatRequestDto.setUserId(sender.getId());
            chatRequestDto.setTargetId(subscriber.getId());
            String content = "새로운 대화 요청";
            chatRequestDto.setContent(content);
            Notice message = noticeService.invite(chatRequestDto);
            assertEquals(content, message.getContent());
        }

        @Test
        @Rollback
        @Transactional
        @DisplayName("새로운 대화 요청 실패")
        void fail() {
            chatRequestDto.setUserId(sender.getId());
            chatRequestDto.setTargetId(sender.getId());
            String content = "새로운 대화 요청";
            chatRequestDto.setContent(content);
            CustomException exception = assertThrows(CustomException.class,
                    () -> noticeService.invite(chatRequestDto));
            assertEquals(
                    HttpStatus.BAD_REQUEST,
                    exception.getErrorCode().getHttpStatus()
            );
            assertEquals(
                    "유효하지 않은 대화 요청입니다.",
                    exception.getErrorCode().getDetail()
            );
            System.out.println(exception.getErrorCode().getHttpStatus());
            System.out.println(exception.getErrorCode().getDetail());
        }
    }
}