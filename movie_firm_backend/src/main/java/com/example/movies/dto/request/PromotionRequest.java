package com.example.movies.dto.request;

import com.example.movies.entity.Movies;
import com.example.movies.entity.StatusPromotion;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PromotionRequest {
    @NotNull(message = "Promotion name cannot be empty")
    private String name;
    @Min(value = 1,message = "Promotion quantity must be greater than 1")
    private int quantity;
    @NotNull(message = "Promotion description cannot be empty")
    private String description;
    @Min(value = 1, message = "Discount percentage must be at least 1%")
    @Max(value = 100, message = "Discount percentage cannot exceed 100%")
    private Double discountPercent;
    @Min(value = 0, message = "Discount amount must be least 0")
    private Double discountAmount;
    @NotNull(message = "Start date cannot be empty")
    private Instant startDate;
    @NotNull(message = "End date cannot be empty")
    private Instant endDate;
    @NotNull(message = "Promotion status cannot be empty")
    private StatusPromotion status;

    public void validateDiscount(){
        if((discountAmount == null && discountPercent == null)||(discountAmount != null && discountPercent != null) ){
            throw new IllegalArgumentException("Voucher must have either a discount amount or a discount percentage, but not both or neither.");
        }
    }

}
