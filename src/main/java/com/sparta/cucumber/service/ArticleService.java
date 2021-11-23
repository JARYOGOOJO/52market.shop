package com.sparta.cucumber.service;

import com.sparta.cucumber.models.Article;
import com.sparta.cucumber.models.Timestamped;
import com.sparta.cucumber.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> findAllDesc() {
        return articleRepository
                .findAll()
                .stream()
                .sorted(Comparator
                        .comparing(Timestamped::getModifiedAt)
                        .reversed())
                .collect(Collectors.toList());
    }
}
