package com.sparta.cucumber.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://www.52market.shop/", "https://52market.shop/", "https://d3235472kjay5e.cloudfront.net/")
                .allowedMethods("POST", "PUT", "GET", "HEAD", "OPTIONS", "DELETE");
    }
}
