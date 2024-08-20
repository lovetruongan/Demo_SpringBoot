package com.example.Demo.service;

import com.example.Demo.dto.request.UserCreationRequest;
import com.example.Demo.entity.User;
import com.example.Demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User createUser(UserCreationRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .role(request.getRole())
                .build();

        return userRepository.save(user);
    }

    public User updateUser(String userId, UserCreationRequest request) {
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
