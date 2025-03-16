package com.example.movies.dto.request;

import com.example.movies.entity.StatusPromotion;
import com.example.movies.entity.TypeVoucher;
import com.example.movies.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VoucherRequest {
    @NotNull(message = "Voucher name cannot be empty")
    private String name;

    @Min(value = 1, message = "Voucher quantity must be greater than 0")
    private int quantity;
    @Min(value = 0, message = "Discount amount must be greater than 0")
    private Double discountAmount;

    @Min(value = 0, message = "Discount percentage must be at least 0%")
    @Max(value = 100, message = "Discount percentage cannot exceed 100%")
    private Double discountPercent;

    @Min(value = 0, message = "Minimum loyalty points must be 0 or higher")
    private Integer minLoyaltyPoints;

    @Min(value = 0, message = "Minimum purchase amount must be 0 or higher")
    private Double minPurchaseAmount;

    @Min(value = 0, message = "Maximum discount amount must be 0 or higher")
    private Double maxDiscountAmount;

    private String termsAndConditions;

    @NotNull(message = "Expiration date cannot be empty")
    private Instant expiryDate;

    private Boolean isUsed = false;

    @NotNull(message = "Voucher status cannot be empty")
    private StatusPromotion status;

    @NotNull(message = "Voucher type cannot be empty")
    private TypeVoucher voucherType; // NEW FIELD

    private Long userId; // Only for PERSONAL vouchers

    // Validate discount type
    public void validateDiscount() {
        if ((discountAmount != null && discountPercent != null) || (discountAmount == null && discountPercent == null)) {
            throw new IllegalArgumentException("Voucher must have either a discount amount or a discount percentage, but not both or neither.");
        }
    }

    // Validate user association for PERSONAL vouchers
    public void validateVoucherType() {
        if (voucherType == TypeVoucher.LOYAL_CUSTOMER && userId == null) {
            throw new IllegalArgumentException("User ID is required for personal vouchers.");
        }
        if (voucherType == TypeVoucher.GLOBAL && userId != null) {
            throw new IllegalArgumentException("Global vouchers should not have a user ID.");
        }
    }
}
