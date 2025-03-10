package com.example.movies.dto.request;

import com.example.movies.entity.AuthProvider;
import com.example.movies.entity.Role;
import com.example.movies.entity.Status;
import com.example.movies.entity.VipLevel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRegisterRequest {
    private String username;
    @Email
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Status status;
    private Boolean isVerified;
    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;
    private Integer loyaltyPoints;
    private VipLevel vipLevel;
}
