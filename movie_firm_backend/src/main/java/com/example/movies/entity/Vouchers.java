package com.example.movies.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "vouchers")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Vouchers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "discount_amount")
    private Double discountAmount;

    @Column(name = "discount_percent")
    private Double discountPercent;

    @Column(name = "min_loyalty_points", nullable = false)
    private Integer minLoyaltyPoints;

    @Column(name = "min_purchase_amount", nullable = false)
    private Double minPurchaseAmount;

    @Column(name = "max_discount_amount")
    private Double maxDiscountAmount;

    @Column(name = "terms_and_conditions")
    private String termsAndConditions;

    @Column(name = "expiry_date", nullable = false)
    private Instant expiryDate;

    @Column(name = "is_used", nullable = false)
    private Boolean isUsed = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TypeVoucher typeVoucher;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusPromotion status = StatusPromotion.ACTIVE;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)
    private User user;
}
