package com.sparta.cucumber.controller;

import com.sparta.cucumber.config.auth.LoginUser;
import com.sparta.cucumber.config.auth.dto.SessionUser;
import com.sparta.cucumber.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final ArticleService articleService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("articles", articleService.findAllDesc());
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }
}
