package com.sparta.cucumber.repository;

import com.sparta.cucumber.models.ChatRoom;
import com.sparta.cucumber.models.EnterRoom;
import com.sparta.cucumber.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnterRoomRepository extends JpaRepository<EnterRoom,Long> {
    Optional<EnterRoom> findByUserAndRoom(User user, ChatRoom chatRoom);
}
