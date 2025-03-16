package com.example.movies.controller;

import com.example.movies.dto.request.VoucherRequest;
import com.example.movies.dto.response.VoucherResponse;
import com.example.movies.service.VoucherService;
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
@RequestMapping("${api.path}/voucher")
public class VoucherController {
    private final VoucherService voucherService;
    @GetMapping("/list-voucher")
    public ResponseEntity<?> getAllVoucher(){
        return ResponseEntity.ok(voucherService.getAllVouchers());
    }
    @GetMapping("/findVoucherById/{id}")
   public ResponseEntity<?> getVoucherById(@PathVariable("id") Long id){
        try {
            VoucherResponse voucherResponse = voucherService.getVoucherById(id);
            return ResponseEntity.ok(voucherResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/create-voucher")
    public ResponseEntity<?> createVoucher(@Valid @RequestBody VoucherRequest voucherRequest, BindingResult bindingResult){
        try {
            if(bindingResult.hasErrors()){
                List<String> errorMessages = bindingResult.getFieldErrors()
                        .stream().map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            VoucherResponse voucherResponse = voucherService.createVoucher(voucherRequest);
            return ResponseEntity.ok(voucherResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/update-voucher/{id}")
    public ResponseEntity<?> updateVoucher(@PathVariable Long id,@Valid @RequestBody VoucherRequest voucherRequest, BindingResult bindingResult){
        try {
            if(bindingResult.hasErrors()){
                List<String> errorMessages = bindingResult.getFieldErrors()
                        .stream().map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            VoucherResponse voucherResponse = voucherService.updateVoucher(id,voucherRequest);
            return ResponseEntity.ok(voucherResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
