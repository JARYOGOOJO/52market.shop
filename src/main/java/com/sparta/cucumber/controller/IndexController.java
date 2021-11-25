package com.sparta.cucumber.controller;

import com.sparta.cucumber.config.auth.LoginUser;
import com.sparta.cucumber.config.auth.dto.SessionUser;
import com.sparta.cucumber.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final ArticleService articleService;
}
