package com.example.movies.service;

import com.example.movies.dto.request.PaymentRequest;
import com.example.movies.dto.response.PaymentResponse;

import java.util.List;

public interface PaymentService {
    List<PaymentResponse> getAllPayments();
    PaymentResponse getPaymentById(Long id);
    PaymentResponse createPayment(PaymentRequest paymentRequest);
    PaymentResponse updatePayment(Long id,PaymentRequest paymentRequest);
    void deletePayment(Long id);
}
