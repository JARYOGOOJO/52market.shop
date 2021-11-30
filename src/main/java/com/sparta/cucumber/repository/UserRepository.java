package com.sparta.cucumber.repository;

import com.sparta.cucumber.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long userId);

    User findByNameAndPassword(String name, String password);

    User findByEmailAndPassword(String email, String password);

    User findByEmail(String email);

}
