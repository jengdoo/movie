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
@Table(name = "user_movies")
public class UserMovies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
    @ManyToOne()
    @JoinColumn(name = "movie_id",referencedColumnName = "id",nullable = false)
    private Movies movie;
    @Column(name = "purchase_date")
    private Instant purchaseDate;
    @Column(name = "price_at_purchase")
    private Double priceAtPurchase;
}
