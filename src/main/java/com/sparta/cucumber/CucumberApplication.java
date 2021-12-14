package com.sparta.cucumber;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CucumberApplication {

    @Value("${cloud.aws.credentials.access-key}")
    private static String AccessKey;
    @Value("${cloud.aws.credentials.secret-key}")
    private static String SecretKey;

    public static void main(String[] args) {
        System.out.println(AccessKey);
        System.out.println(SecretKey);
        SpringApplication.run(CucumberApplication.class, args);
    }
}
