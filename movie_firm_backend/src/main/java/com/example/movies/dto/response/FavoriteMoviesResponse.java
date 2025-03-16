package com.example.movies.dto.response;

import com.example.movies.entity.FavoriteMovies;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FavoriteMoviesResponse {
    private Long Id;
    private UserResponse userResponse;
    private MoviesResponse moviesResponse;
    private Instant favoritedAt;

    public static FavoriteMoviesResponse convert(FavoriteMovies favoriteMovies) {
        return FavoriteMoviesResponse.builder()
                .Id(favoriteMovies.getId())
                .moviesResponse(MoviesResponse.convertMoviesToMoviesResponse(favoriteMovies.getMovies()))
                .userResponse(UserResponse.convertUserToUserResponse(favoriteMovies.getUser()))
                .favoritedAt(favoriteMovies.getFavoritedAt())
                .build();
    }
}
