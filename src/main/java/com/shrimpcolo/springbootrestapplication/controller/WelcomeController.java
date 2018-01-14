package com.shrimpcolo.springbootrestapplication.controller;

import com.shrimpcolo.springbootrestapplication.configuration.BasicConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shrimpcolo.springbootrestapplication.service.WelcomeService;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WelcomeController {

    @Autowired
    private WelcomeService service;

    @Autowired
    private BasicConfiguration basicConfiguration;

    @GetMapping("/welcome")
    public String welcome() {
        return service.retrieveWelcomeMessage();
    }

    @GetMapping("/welcome/message")
    public String getWelcomeMessageOnly() {
        return service.retrieveWelcomeMessageOnly();
    }

    @GetMapping("/welcome/num")
    public int getWelcomeNumberOnly() {
        return service.retrieveWelcomeNumber();
    }

    @GetMapping("/welcome/value")
    public boolean getWelcomeValueOnly() {
        return service.retrieveWelcomeValue();
    }

    @GetMapping("/dynamic-configuration")
    public Map<String, Object> dynamicConfiguration() {
        Map map = new HashMap<String, Object>();

        map.put("message", basicConfiguration.getMessage());
        map.put("number", basicConfiguration.getNumber());
        map.put("value", basicConfiguration.isValue());

        return map;
    }

}