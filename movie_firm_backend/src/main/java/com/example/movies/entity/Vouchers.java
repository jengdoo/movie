package com.example.movies.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "vouchers")
public class Vouchers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "discount_amount")
    private Double discountAmount;
    @Column(name = "min_loyalty_points")
    private Integer minLoyaltyPoints;
    @Column(name = "expiry_date")
    private Instant expiryDate;
    @Column(name = "is_used")
    private Boolean isUsed;
    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
}
