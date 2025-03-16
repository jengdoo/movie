package com.example.movies.controller;

import com.example.movies.dto.request.InvoicesRequest;
import com.example.movies.dto.response.InvoicesResponse;
import com.example.movies.service.InvoicesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.path}/invoices")
public class InvoiceController {
    private final InvoicesService invoicesService;

    @GetMapping("/list-invoice")
    public ResponseEntity<?> listInvoice() {
        return ResponseEntity.ok(invoicesService.getAllInvoices());
    }
    @GetMapping("/findByInvoiceId/{id}")
    public ResponseEntity<?> findByInvoiceId(@PathVariable Long id) {
        try {
            InvoicesResponse invoicesResponse = invoicesService.getInvoice(id);
            return ResponseEntity.ok(invoicesResponse);
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }
    @PostMapping("/create-invoice")
    public ResponseEntity<?> createInvoice(@Valid @RequestBody InvoicesRequest invoicesRequest, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errors = bindingResult.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                return ResponseEntity.badRequest().body(errors);
            }
            InvoicesResponse invoicesResponse = invoicesService.addInvoice(invoicesRequest);
            return ResponseEntity.ok(invoicesResponse);
        }catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }
    @PutMapping("/update-invoice/{id}")
    public ResponseEntity<?> updateInvoice(@PathVariable Long id,@Valid @RequestBody InvoicesRequest invoicesRequest, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errors = bindingResult.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                return ResponseEntity.badRequest().body(errors);
            }
            InvoicesResponse invoicesResponse = invoicesService.updateInvoice(id,invoicesRequest);
            return ResponseEntity.ok(invoicesResponse);
        }catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }
    @DeleteMapping("/delete-invoice")
    public ResponseEntity<?> deleteInvoice(@RequestParam Long id) {
        try {
            invoicesService.deleteInvoice(id);
            return ResponseEntity.ok("Delete invoice with id: "+id+" successfully");
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }
}
