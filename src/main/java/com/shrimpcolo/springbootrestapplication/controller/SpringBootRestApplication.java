package com.shrimpcolo.springbootrestapplication.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;


@SpringBootApplication
@ComponentScan("com.shrimpcolo.springbootrestapplication")
public class SpringBootRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestApplication.class, args);
    }

    @Profile("dev")
    @Bean
    public String dummy11() {
        return "something";
    }
}
