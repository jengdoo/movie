package com.example.movies.dto.response;

import com.example.movies.entity.FavoriteMovies;
import com.example.movies.entity.UserMovies;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserMoviesResponse {
    Long id;
    UserResponse user;
    MoviesResponse movie;
    Instant purchaseDate;
    Double priceAtPurchase;
    public static UserMoviesResponse convert(UserMovies userMovies) {
        return UserMoviesResponse.builder()
                .id(userMovies.getId())
                .movie(MoviesResponse.convertMoviesToMoviesResponse(userMovies.getMovie()))
                .user(UserResponse.convertUserToUserResponse(userMovies.getUser()))
                .purchaseDate(userMovies.getPurchaseDate())
                .priceAtPurchase(userMovies.getPriceAtPurchase())
                .build();
    }
}
