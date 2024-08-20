package com.example.Demo.dto.request;

import com.example.Demo.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostCreationRequest {
    String title;
    String body;
    User user;
    String status;
}
