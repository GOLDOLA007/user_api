package com.api.user_api.controller;

import com.api.user_api.model.User;
import com.api.user_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    /*
    @GetMapping
    public String status(){
        return "The API is online!";
    }*/

    @PostMapping
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping
    public List<User> listAll(){
        return userRepository.findAll();
    }
}
