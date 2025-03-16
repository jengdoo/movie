package com.example.movies.dto.request;

import com.example.movies.entity.Invoices;
import com.example.movies.entity.LoyaltyTransactions;
import com.example.movies.entity.PaymentMethod;
import com.example.movies.entity.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentRequest {
    private Long invoicesId;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private Instant createdAt;
}
