package com.example.movies.service.implement;

import com.example.movies.dto.request.InvoicesRequest;
import com.example.movies.dto.response.InvoicesResponse;
import com.example.movies.entity.Invoices;
import com.example.movies.entity.User;
import com.example.movies.repository.InvoicesRepository;
import com.example.movies.repository.UserRepository;
import com.example.movies.service.InvoicesService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoicesServiceImpl implements InvoicesService {
    private final InvoicesRepository invoicesRepository;
    private final UserRepository userRepository;
    @Override
    public List<InvoicesResponse> getAllInvoices() {
        return invoicesRepository.findAll().stream().map(InvoicesResponse::convert).collect(Collectors.toList());
    }

    @Override
    public InvoicesResponse getInvoice(Long id) {
        return InvoicesResponse.convert(invoicesRepository.findById(id).orElseThrow(()->new RuntimeException("not found invoice with id"+ id)));
    }

    @Override
    @Transactional
    public InvoicesResponse addInvoice(InvoicesRequest invoicesRequest) {
        User user = userRepository.findById(invoicesRequest.getUserId()).orElseThrow(()->new RuntimeException("not found user with id"+ invoicesRequest.getUserId()));
        Random random = new Random();
        StringBuilder code = new StringBuilder("HD");
        for (int i = 0; i < 9; i++) {
            code.append(random.nextInt(10));
        }
        Invoices invoices = new Invoices();
        invoices.setUser(user);
        invoices.setCode(code.toString());
        invoices.setTotalAmount(invoicesRequest.getTotalAmount());
        invoices.setCreatedAt(Instant.now());
        invoicesRepository.save(invoices);
        return InvoicesResponse.convert(invoices);
    }

    @Override
    @Transactional
    public InvoicesResponse updateInvoice(Long id, InvoicesRequest invoicesRequest) {
        User user = userRepository.findById(invoicesRequest.getUserId()).orElseThrow(()->new RuntimeException("not found user with id"+ invoicesRequest.getUserId()));
        Invoices invoices = invoicesRepository.findById(id).orElseThrow(()->new RuntimeException("not found invoice with id"+ id));
        invoices.setUser(user);
        invoices.setTotalAmount(invoicesRequest.getTotalAmount());
        invoices.setCreatedAt(Instant.now());
        invoicesRepository.save(invoices);
        return InvoicesResponse.convert(invoices);
    }

    @Override
    public void deleteInvoice(Long id) {
        Invoices invoices = invoicesRepository.findById(id).orElseThrow(()->new RuntimeException("not found invoice with id"+ id));
        invoicesRepository.delete(invoices);
    }
}
