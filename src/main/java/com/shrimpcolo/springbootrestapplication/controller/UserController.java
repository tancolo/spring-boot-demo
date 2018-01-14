package com.shrimpcolo.springbootrestapplication.controller;

import com.shrimpcolo.springbootrestapplication.jpa.User;
import com.shrimpcolo.springbootrestapplication.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository repository;

    // path/user/
    @GetMapping
    public Iterable<User> findUser() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> findUserById(@PathVariable Long id ){
        return repository.findById(id);
    }
}
