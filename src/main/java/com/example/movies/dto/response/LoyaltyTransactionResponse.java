package com.example.movies.dto.response;

import com.example.movies.entity.Movies;
import com.example.movies.entity.TransactionType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoyaltyTransactionResponse {
    private Long id;
    private UserResponse user;
    private MoviesResponse movie;
    private Integer points;
    private TransactionType transactionType;
    private String description;
    private Instant createdAt;
    private Long payment;
}
