package com.sparta.cucumber.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("52market.shop")
                .pathsToMatch("/api/**")
                .packagesToScan("com.sparta.cucumber.controller")
                .build();
    }
}
