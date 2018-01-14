package com.shrimpcolo.springbootrestapplication.controller;

import com.shrimpcolo.springbootrestapplication.jpa.User;
import com.shrimpcolo.springbootrestapplication.jpa.UserCommandLineRunner;
import com.shrimpcolo.springbootrestapplication.jpa.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository repository;

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

//    @GetMapping
//    public Iterable<User> findUserByName(@RequestParam("name") String name) {
//        List<User> list = repository.findByName(name);
//        log.info("TANHQ===> list.size = " + list.size());
//        return repository.findByName(name);
//    }


}
