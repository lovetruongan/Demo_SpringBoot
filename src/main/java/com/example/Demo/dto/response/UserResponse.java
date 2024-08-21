package com.example.Demo.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UserResponse {
    Integer id;
    String username;
    String email;
    String role;
    LocalDateTime createdAt;
}
