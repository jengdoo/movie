package com.example.movies.service.implement;

import com.example.movies.dto.request.VoucherRequest;
import com.example.movies.dto.response.VoucherResponse;
import com.example.movies.entity.StatusPromotion;
import com.example.movies.entity.TypeVoucher;
import com.example.movies.entity.User;
import com.example.movies.entity.Vouchers;
import com.example.movies.repository.UserRepository;
import com.example.movies.repository.VoucherRepository;
import com.example.movies.service.VoucherService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VoucherServiceImpl implements VoucherService {
    private final VoucherRepository voucherRepository;
    private final UserRepository userRepository;

    @Override
    public List<VoucherResponse> getAllVouchers() {
        return voucherRepository.findAll().stream().map(VoucherResponse::convert).collect(Collectors.toList());
    }

    @Override
    public VoucherResponse getVoucherById(Long id) {
        return voucherRepository.findById(id).map(VoucherResponse::convert).orElseThrow(()->new RuntimeException("Voucher Not Found with id: "+id));
    }

    @Override
    @Transactional
    public VoucherResponse createVoucher(VoucherRequest voucherRequest) {
        User user = null;
        if (voucherRequest.getVoucherType() == TypeVoucher.LOYAL_CUSTOMER) {
            user = userRepository.findById(voucherRequest.getUserId()).orElse(null);
            if (user == null) {
                throw new IllegalArgumentException("User not found for the given ID");
            }
        }

        String code = "V" + new Random().ints(9, 0, 10)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());

        if (voucherRequest.getExpiryDate().isBefore(Instant.now())) {
            throw new IllegalArgumentException("Expiry date must be greater than the current time.");
        }
        voucherRequest.validateVoucherType();
        voucherRequest.validateDiscount();
        Vouchers vouchers = new Vouchers();
        vouchers.setUser(user);
        vouchers.setCode(code.toString());
        vouchers.setName(voucherRequest.getName());
        vouchers.setDiscountAmount(voucherRequest.getDiscountAmount());
        vouchers.setDiscountPercent(voucherRequest.getDiscountPercent());
        vouchers.setTermsAndConditions(voucherRequest.getTermsAndConditions());
        vouchers.setQuantity(voucherRequest.getQuantity());
        vouchers.setTypeVoucher(voucherRequest.getVoucherType());
        vouchers.setMinLoyaltyPoints(voucherRequest.getMinLoyaltyPoints());
        vouchers.setMinPurchaseAmount(voucherRequest.getMinPurchaseAmount());
        vouchers.setMaxDiscountAmount(voucherRequest.getMaxDiscountAmount());
        vouchers.setCreatedAt(Instant.now());
        vouchers.setUpdatedAt(Instant.now());
        vouchers.setExpiryDate(voucherRequest.getExpiryDate());
        vouchers.setStatus(StatusPromotion.ACTIVE);
        vouchers.setIsUsed(false);
        voucherRepository.save(vouchers);
        return VoucherResponse.convert(vouchers);
    }

    @Override
    @Transactional
    public VoucherResponse updateVoucher(Long id, VoucherRequest voucherRequest) {
        Vouchers vouchers = voucherRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Voucher Not Found with id: "+id));
        User user = null;
        if (voucherRequest.getVoucherType() == TypeVoucher.LOYAL_CUSTOMER) {
            user = userRepository.findById(voucherRequest.getUserId()).orElse(null);
            if (user == null) {
                throw new IllegalArgumentException("User not found for the given ID");
            }
        }

        if (voucherRequest.getExpiryDate().isBefore(Instant.now())) {
            throw new IllegalArgumentException("Expiry date must be greater than the current time.");
        }
        voucherRequest.validateVoucherType();
        voucherRequest.validateDiscount();
        vouchers.setUser(user);
        vouchers.setName(voucherRequest.getName());
        vouchers.setDiscountAmount(voucherRequest.getDiscountAmount());
        vouchers.setDiscountPercent(voucherRequest.getDiscountPercent());
        vouchers.setTermsAndConditions(voucherRequest.getTermsAndConditions());
        vouchers.setQuantity(voucherRequest.getQuantity());
        vouchers.setTypeVoucher(voucherRequest.getVoucherType());
        vouchers.setMinLoyaltyPoints(voucherRequest.getMinLoyaltyPoints());
        vouchers.setMinPurchaseAmount(voucherRequest.getMinPurchaseAmount());
        vouchers.setMaxDiscountAmount(voucherRequest.getMaxDiscountAmount());
        vouchers.setUpdatedAt(Instant.now());
        vouchers.setExpiryDate(voucherRequest.getExpiryDate());
        vouchers.setStatus(StatusPromotion.ACTIVE);
        vouchers.setIsUsed(false);
        voucherRepository.save(vouchers);
        return VoucherResponse.convert(vouchers);
    }

    @Override
    public void deleteVoucher(Long id) {
    }
}
