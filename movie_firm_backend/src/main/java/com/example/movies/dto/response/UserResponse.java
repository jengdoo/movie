package com.example.movies.dto.response;

import com.example.movies.entity.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String passwordHash;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Status status;
    private Boolean isVerified;
    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;
    private Integer loyaltyPoints;
    private VipLevel vipLevel;

    public static UserResponse convertUserToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .passwordHash(user.getPasswordHash())
                .role(user.getRole())
                .status(user.getStatus())
                .isVerified(user.getIsVerified())
                .loyaltyPoints(user.getLoyaltyPoints())
                .vipLevel(user.getVipLevel())
                .build();
    }
}
