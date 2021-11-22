package com.sparta.cucumber.repository;

import com.sparta.cucumber.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
