package com.sparta.cucumber.user;

import com.sparta.cucumber.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long userId);
    User findByNameAndPassword(String name, String password);
    User findByEmail(String email);
}
