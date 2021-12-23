package com.sparta.cucumber.chat;

import com.sparta.cucumber.models.User;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ChatRoomTest {

    private User host;
    private User guest;
    private ChatRoom chatRoom;

    @BeforeEach
    void setup() {
        host = new User();
        guest = new User();
        chatRoom = ChatRoom
                .builder()
                .title("대화방 개설 테스트")
                .host(host)
                .build();
    }

    @AfterEach
    void clear() {

    }

    @Test
    @Order(1)
    @DisplayName("대화방 만들기")
    void builder() {
        chatRoom = ChatRoom
                .builder()
                .title("대화방 개설 테스트")
                .host(host)
                .build();
        assertEquals(chatRoom.getHost(), host);
        assertTrue(chatRoom.isActive());
        System.out.println(chatRoom.toString());
    }

    @Test
    @Order(2)
    @DisplayName("대화방 입장하기")
    void enter() {
        chatRoom.enter(guest);
        assertEquals(chatRoom.getGuest(), guest);
        assertTrue(chatRoom.isActive());
        System.out.println(chatRoom.toString());
    }

    @Test
    @Order(3)
    @DisplayName("대화방 대화하기")
    void talk() {
        chatRoom.enter(guest);
        Notice msg1 = Notice.builder().chatRoom(chatRoom).senderId(host.getId()).content("안녕하세요.").build();
        System.out.println(msg1);
        Notice msg2 = Notice.builder().chatRoom(chatRoom).senderId(guest.getId()).content("안녕하세요.").build();
        System.out.println(msg2);
        assertEquals(chatRoom.getHost(), host);
        assertEquals(chatRoom.getGuest(), guest);
        assertTrue(chatRoom.isActive());
        System.out.println(chatRoom.toString());
    }

    @Test
    @Order(4)
    @DisplayName("대화방 나가기")
    void exit() {
        chatRoom.exit(guest);
        assertNull(chatRoom.getGuest());
        assertFalse(chatRoom.isActive());
        System.out.println(chatRoom.toString());
    }
}