package com.example.randomuser.controller;

import com.example.randomuser.model.User;
import com.example.randomuser.repo.UserRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private static final String RESULTS = "$.results[INDEX]";
    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping(value = "/post")
    public String postAll(@RequestBody List<User> results) {
        userRepo.saveAll(results);
        return "Completed!";
    }
}
