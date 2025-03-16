package com.example.movies.service;

import com.example.movies.dto.request.VoucherRequest;
import com.example.movies.dto.response.VoucherResponse;

import java.util.List;

public interface VoucherService {
    List<VoucherResponse> getAllVouchers();
    VoucherResponse getVoucherById(Long id);
    VoucherResponse createVoucher(VoucherRequest voucherRequest);
    VoucherResponse updateVoucher(Long id,VoucherRequest voucherRequest);
    void deleteVoucher(Long id);
}
