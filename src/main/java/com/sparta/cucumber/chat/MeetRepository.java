package com.sparta.cucumber.chat;

import com.sparta.cucumber.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetRepository extends JpaRepository<Meet, Long> {
    List<Meet> findAllByArticle(Article article);
}
