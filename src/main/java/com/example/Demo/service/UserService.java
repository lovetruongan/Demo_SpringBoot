package com.example.Demo.service;

import com.example.Demo.dto.request.UserCreationRequest;
import com.example.Demo.dto.request.UserUpdateRequest;
import com.example.Demo.dto.response.UserResponse;
import com.example.Demo.entity.User;

import com.example.Demo.exception.CustomException;
import com.example.Demo.exception.ErrorCode;
import com.example.Demo.repository.UserRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Builder
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class UserService {
    @Autowired
    UserRepository userRepository;


    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .birth(user.getBirth())
                        .createdAt(user.getCreatedAt())
                        .build())
                .toList();
    }

    public UserResponse getUser(String userId) {
        User user = userRepository.findById(Integer.valueOf(userId))
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_EXISTED));
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .role(user.getRole())
                .birth(user.getBirth())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new CustomException(ErrorCode.USER_EXISTED);
        }
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .role(request.getRole())
                .birth(request.getBirth())
                .build();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return UserResponse.builder()
                .id(userRepository.save(user).getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .role(user.getRole())
                .birth(user.getBirth())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(Integer.valueOf(userId))
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_EXISTED));
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        user.setBirth(request.getBirth());

        return UserResponse.builder()
                .id(userRepository.save(user).getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .birth(user.getBirth())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(Integer.valueOf(userId));
    }
}
