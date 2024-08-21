package com.example.Demo.controller;

import com.example.Demo.dto.request.UserCreationRequest;
import com.example.Demo.dto.request.UserUpdateRequest;
import com.example.Demo.dto.response.ApiResponse;
import com.example.Demo.dto.response.UserResponse;
import com.example.Demo.entity.User;
import com.example.Demo.repository.UserRepository;
import com.example.Demo.service.UserService;
import jakarta.validation.Valid;
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
    List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));

        return apiResponse;
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return "User has been deleted";
    }


}
