package com.shrimpcolo.springbootrestapplication.service;


import com.shrimpcolo.springbootrestapplication.configuration.BasicConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WelcomeService {

    @Value("${welcome.message}")
    private String welcomeMessage;

    @Autowired
    private BasicConfiguration basicConfiguration;

    public String retrieveWelcomeMessage() {
        return welcomeMessage;
    }

    public String retrieveWelcomeMessageOnly() {
        return basicConfiguration.getMessage();
    }

    public int retrieveWelcomeNumber() {
        return basicConfiguration.getNumber();
    }

    public boolean retrieveWelcomeValue() {
        return basicConfiguration.isValue();
    }
}