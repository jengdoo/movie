package com.example.movies.dto.request;

import com.example.movies.entity.PaymentMethod;
import com.example.movies.entity.PaymentStatus;
import com.example.movies.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
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
public class InvoicesRequest {
    @NotNull(message = "user is required")
    private Long userId;
    @Min(value = 0,message = "total amount can not be less than 0")
    private Double totalAmount;
    private Instant createdAt;
}
