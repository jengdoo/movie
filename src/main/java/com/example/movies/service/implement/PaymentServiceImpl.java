package com.example.movies.service.implement;

import com.example.movies.dto.request.PaymentRequest;
import com.example.movies.dto.response.PaymentResponse;
import com.example.movies.entity.Invoices;
import com.example.movies.entity.Payment;
import com.example.movies.entity.PaymentStatus;
import com.example.movies.repository.InvoicesRepository;
import com.example.movies.repository.PaymentRepository;
import com.example.movies.repository.UserRepository;
import com.example.movies.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final InvoicesRepository invoicesRepository;
    @Override
    public List<PaymentResponse> getAllPayments() {
        return paymentRepository.findAll().stream().map(PaymentResponse::convertEntityToPaymentResponse).collect(Collectors.toList());
    }

    @Override
    public PaymentResponse getPaymentById(Long id) {
        return PaymentResponse.convertEntityToPaymentResponse(paymentRepository.findById(id).orElseThrow(()-> new RuntimeException("not found payment by id"+ id)));
    }

    @Override
    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        Invoices invoices = invoicesRepository.findById(paymentRequest.getInvoicesId()).orElseThrow(()-> new RuntimeException("not found invoices by id"+ paymentRequest.getInvoicesId()));
        Payment payment = new Payment();
        payment.setInvoices(invoices);
        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setPaymentMethod(paymentRequest.getPaymentMethod());
        payment.setCreatedAt(Instant.now());
        paymentRepository.save(payment);
        return PaymentResponse.convertEntityToPaymentResponse(paymentRepository.save(payment));
    }

    @Override
    public PaymentResponse updatePayment(Long id, PaymentRequest paymentRequest) {
        Invoices invoices = invoicesRepository.findById(paymentRequest.getInvoicesId()).orElseThrow(()-> new RuntimeException("not found invoices by id"+ paymentRequest.getInvoicesId()));
        Payment payment = paymentRepository.findById(id).orElseThrow(()-> new RuntimeException("not found payment by id"+ id));
        payment.setInvoices(invoices);
        payment.setPaymentStatus(paymentRequest.getPaymentStatus());
        payment.setPaymentMethod(paymentRequest.getPaymentMethod());
        payment.setCreatedAt(Instant.now());
        paymentRepository.save(payment);
        return PaymentResponse.convertEntityToPaymentResponse(paymentRepository.save(payment));
    }

    @Override
    public void deletePayment(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(()-> new RuntimeException("not found payment by id"+ id));
        paymentRepository.delete(payment);
    }
}
