package com.example.movies.dto.request;

import com.example.movies.entity.AuthProvider;
import com.example.movies.entity.Role;
import com.example.movies.entity.Status;
import com.example.movies.entity.VipLevel;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserLoginRequest {
    @NotNull(message = "username or email are required")
     String usernameOrEmail;
    @NotNull(message = "pass word is required")
     String password;
}
