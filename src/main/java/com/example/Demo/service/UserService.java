package com.example.Demo.service;

import com.example.Demo.dto.request.UserCreationRequest;
import com.example.Demo.dto.request.UserUpdateRequest;
import com.example.Demo.dto.response.UserResponse;
import com.example.Demo.entity.User;

import com.example.Demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .createdAt(user.getCreatedAt())
                        .build())
                .toList();
    }

    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("User already exists");
        }
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .role(request.getRole())
                .build();

        return UserResponse.builder()
                .id(userRepository.save(user).getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public User updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(Integer.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());

        return userRepository.save(user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(Integer.valueOf(userId));
    }
}
