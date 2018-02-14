package com.shrimpcolo.springbootrestapplication.controller;

import com.shrimpcolo.springbootrestapplication.jpa.User;
import com.shrimpcolo.springbootrestapplication.jpa.UserInfo;
import com.shrimpcolo.springbootrestapplication.jpa.UserRestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

//    @Autowired
//    private UserRepository repository;

    @Autowired
    private UserRestRepository repository;

    // path/user/
    @GetMapping
    public Iterable<User> findUser(@RequestParam(value = "name", required = false) String name) {
        log.info("TANHQ===> findUser name = " + name);
        if (name == null) {
            return repository.findAll();
        } else {
            return repository.findByNameContainingIgnoreCase(name);
        }
    }

    @GetMapping("/{id}")
    public Optional<User> findUserById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping
    public User createNewUser(@RequestBody UserInfo info) {
        System.out.println("TANHQ====> info = " + info);
        User user = new User(info.getName(), info.getRole());
        repository.save(user);

        return user;
    }


}
