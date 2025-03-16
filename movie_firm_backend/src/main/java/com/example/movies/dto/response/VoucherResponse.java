package com.example.movies.dto.response;

import com.example.movies.entity.StatusPromotion;
import com.example.movies.entity.TypeVoucher;
import com.example.movies.entity.Vouchers;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VoucherResponse {
    private Long id;
    private String code;
    private String name;
    private int quantity;
    private Double discountAmount;
    private Double discountPercent;
    private Integer minLoyaltyPoints;
    private Double minPurchaseAmount;
    private Double maxDiscountAmount;
    private String termsAndConditions;
    private String expiryDate;
    private Boolean isUsed;
    private StatusPromotion status;
    private String createdAt;
    private String updatedAt;
    private TypeVoucher voucherType;
    private UserResponse user;

    public static VoucherResponse convert(Vouchers vouchers) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT; // Chuyển Instant thành chuỗi ISO 8601

        return VoucherResponse.builder()
                .id(vouchers.getId())
                .code(vouchers.getCode())
                .name(vouchers.getName())
                .quantity(vouchers.getQuantity())
                .discountAmount(vouchers.getDiscountAmount())
                .discountPercent(vouchers.getDiscountPercent())
                .minLoyaltyPoints(vouchers.getMinLoyaltyPoints())
                .minPurchaseAmount(vouchers.getMinPurchaseAmount())
                .maxDiscountAmount(vouchers.getMaxDiscountAmount())
                .termsAndConditions(vouchers.getTermsAndConditions())
                .expiryDate(vouchers.getExpiryDate()!=null?vouchers.getExpiryDate().atZone(ZoneOffset.UTC).format(formatter):null)
                .isUsed(vouchers.getIsUsed())
                .status(vouchers.getStatus())
                .updatedAt(vouchers.getUpdatedAt()!=null?vouchers.getUpdatedAt().atZone(ZoneOffset.UTC).format(formatter):null)
                .createdAt(vouchers.getCreatedAt()!=null?vouchers.getCreatedAt().atZone(ZoneOffset.UTC).format(formatter):null)
                .voucherType(vouchers.getTypeVoucher())
                .user(vouchers.getTypeVoucher()==TypeVoucher.LOYAL_CUSTOMER? UserResponse.convertUserToUserResponse(vouchers.getUser()):null)
                .build();
    }
}
