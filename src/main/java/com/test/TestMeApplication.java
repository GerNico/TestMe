package com.test;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestMeApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestMeApplication.class, args);
    }
}
