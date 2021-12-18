package com.sparta.cucumber.chat;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Notice, Long> {
}
