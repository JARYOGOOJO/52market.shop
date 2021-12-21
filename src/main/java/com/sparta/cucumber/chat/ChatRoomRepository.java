package com.sparta.cucumber.chat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {
    Optional<ChatRoom> findByRoomSubscribeId(String roomSubscribeId);
}
