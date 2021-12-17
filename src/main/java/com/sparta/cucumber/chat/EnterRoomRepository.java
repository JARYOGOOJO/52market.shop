package com.sparta.cucumber.chat;

import com.sparta.cucumber.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnterRoomRepository extends JpaRepository<EnterRoom, Long> {
    Optional<EnterRoom> findByUserAndRoom(User user, ChatRoom chatRoom);
}
