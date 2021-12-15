package com.sparta.cucumber.repository;

import com.sparta.cucumber.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long userId);

    Optional<User> findByEmail(String email);

    Optional<User> findByName(String username);

    Optional<User> findBySocialId(Long kakaoId);
}
