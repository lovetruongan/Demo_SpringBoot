package com.example.Demo.controller;

import com.example.Demo.dto.request.UserCreationRequest;
import com.example.Demo.entity.User;
import com.example.Demo.repository.UserRepository;
import com.example.Demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public User createUser(@RequestBody UserCreationRequest request) {
        return userService.createUser(request);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable String userId, @RequestBody UserCreationRequest request) {
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return "User has been deleted";
    }


}
