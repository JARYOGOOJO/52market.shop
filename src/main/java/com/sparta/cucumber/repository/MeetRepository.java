package com.sparta.cucumber.repository;

import com.sparta.cucumber.models.Article;
import com.sparta.cucumber.models.Meet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetRepository extends JpaRepository<Meet, Long> {
}
