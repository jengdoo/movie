package com.example.movies.dto.request;

import com.example.movies.entity.Movies;
import com.example.movies.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserMoviesRequest {
    @NotNull(message = "User is required")
     Long userId;
    @NotNull(message = "Movie is required")
     Long movieId;
     Instant purchaseDate;
    @NotNull(message = "Price at purchase is required")
     Double priceAtPurchase;
}
