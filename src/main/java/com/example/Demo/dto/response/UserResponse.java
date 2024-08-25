package com.example.Demo.dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UserResponse {
    Integer id;
    String username;
    String password;
    String email;
    String role;
    LocalDate birth;
    LocalDateTime createdAt;
}
