package com.example.movies.dto.response;

import com.example.movies.entity.Payment;
import com.example.movies.entity.PaymentMethod;
import com.example.movies.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentResponse {
    private Long id;
    private InvoicesResponse invoices;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private String createdAt;

    public static PaymentResponse convertEntityToPaymentResponse(Payment payment) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT; // Chuyển Instant thành chuỗi ISO 8601
        return PaymentResponse.builder()
                .id(payment.getId())
                .invoices(InvoicesResponse.convert(payment.getInvoices()))
                .paymentMethod(payment.getPaymentMethod())
                .paymentStatus(payment.getPaymentStatus())
                .createdAt(payment.getCreatedAt()!=null?payment.getCreatedAt().atZone(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT):null)
                .build();
    }
}
