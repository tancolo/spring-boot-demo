package com.shrimpcolo.springbootrestapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
//@ComponentScan("com.shrimpcolo.springbootrestapplication")
//@EnableJpaRepositories("com.shrimpcolo.springbootrestapplication.jpa")
//@EntityScan("com.shrimpcolo.springbootrestapplication.jpa")
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
