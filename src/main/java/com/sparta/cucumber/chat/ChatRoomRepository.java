package com.sparta.cucumber.chat;

import com.sparta.cucumber.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {
    Optional<ChatRoom> findByRoomSubscribeId(String roomSubscribeId);
    Optional<List<ChatRoom>> findByGuest(User user);
    Optional<List<ChatRoom>> findByHost(User user);
    Optional<List<ChatRoom>> findByGuestOrHost(User guest,User host);
}
