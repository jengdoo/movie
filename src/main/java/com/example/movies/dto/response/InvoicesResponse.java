package com.example.movies.dto.response;

import com.example.movies.entity.Invoices;
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
public class InvoicesResponse {
    private Long id;
    private String code;
    private UserResponse userResponse;
    private Double totalAmount;
    private Instant createdAt;

    public static InvoicesResponse convert(Invoices invoices) {
        return InvoicesResponse.builder()
                .id(invoices.getId())
                .code(invoices.getCode())
                .userResponse(UserResponse.convertUserToUserResponse(invoices.getUser()))
                .totalAmount(invoices.getTotalAmount())
                .createdAt(invoices.getCreatedAt())
                .build();
    }
}
