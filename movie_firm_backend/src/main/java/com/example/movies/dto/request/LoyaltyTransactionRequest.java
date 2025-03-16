package com.example.movies.dto.request;

import com.example.movies.entity.Movies;
import com.example.movies.entity.Payment;
import com.example.movies.entity.TransactionType;
import com.example.movies.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class LoyaltyTransactionRequest {
    @NotNull(message = "User is required")
    private Long userId;
    @NotNull(message = "Movie is required")
    private Long movieId;
    private Integer points;
    private TransactionType transactionType;
    private String description;
    private Instant createdAt;
    @NotNull(message = "Payment is required")
    private Long paymentId;
}
