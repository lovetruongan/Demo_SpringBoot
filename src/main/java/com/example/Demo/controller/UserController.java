package com.example.Demo.controller;

import com.example.Demo.dto.request.UserCreationRequest;
import com.example.Demo.dto.request.UserUpdateRequest;
import com.example.Demo.dto.response.ApiResponse;
import com.example.Demo.dto.response.UserResponse;
import com.example.Demo.exception.CustomException;
import com.example.Demo.exception.ErrorCode;
import com.example.Demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    ApiResponse<List<UserResponse>> getUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .message("Success")
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable("userId") String userId) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUser(userId))
                .message("Success")
                .build();
    }

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .message("User created successfully")
                .build();
    }

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable @Valid String userId, @RequestBody UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(userId, request))
                .message("User updated successfully")
                .build();
    }

    @DeleteMapping("/{userId}")
    ApiResponse<UserResponse> deleteUser(@PathVariable String userId) {
        try {
            if (userService.getUser(userId) == null) {
                throw new CustomException(ErrorCode.USER_NOT_EXISTED);
            }
        } catch (Exception e) {
            throw new CustomException(ErrorCode.USER_NOT_EXISTED);
        }
        return ApiResponse.<UserResponse>builder()
                .message("User deleted successfully")
                .build();
    }

}
