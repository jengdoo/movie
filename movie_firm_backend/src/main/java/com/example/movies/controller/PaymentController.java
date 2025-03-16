package com.example.movies.controller;

import com.example.movies.dto.request.PaymentRequest;
import com.example.movies.dto.response.PaymentResponse;
import com.example.movies.entity.Payment;
import com.example.movies.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.path}/payments")
public class PaymentController {
    private final PaymentService paymentService;
    @GetMapping("/list-payment")
    public ResponseEntity<?> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }
    @GetMapping("/findPaymentById/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(paymentService.getPaymentById(id));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/create-payment")
    public ResponseEntity<?> createPayment(@Valid @RequestBody PaymentRequest paymentRequest, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errors = bindingResult.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                return ResponseEntity.badRequest().body(errors);
            }
            PaymentResponse paymentResponse = paymentService.createPayment(paymentRequest);
            return ResponseEntity.ok(paymentResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/update-payment/{id}")
    public ResponseEntity<?> updatePayment(@PathVariable Long id,@Valid @RequestBody PaymentRequest paymentRequest, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errors = bindingResult.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                return ResponseEntity.badRequest().body(errors);
            }
            PaymentResponse paymentResponse = paymentService.updatePayment(id, paymentRequest);
            return ResponseEntity.ok(paymentResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/delete-payment")
    public ResponseEntity<?> deletePayment(@RequestParam Long id) {
        try {
            paymentService.deletePayment(id);
            return ResponseEntity.ok("Deleted payment by id: "+id+" successfully");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
