package com.example.movies.service;

import com.example.movies.dto.request.InvoicesRequest;
import com.example.movies.dto.response.InvoicesResponse;
import com.example.movies.entity.Invoices;

import java.util.List;

public interface InvoicesService {
    List<InvoicesResponse> getAllInvoices();
    InvoicesResponse getInvoice(Long id);
    InvoicesResponse addInvoice(InvoicesRequest invoicesRequest);
    InvoicesResponse updateInvoice(Long id, InvoicesRequest invoicesRequest);
    void deleteInvoice(Long id);
}
