package com.example.movies.dto.request;

import com.example.movies.entity.Movies;
import com.example.movies.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class FavoriteMoviesRequest {
    @NotNull(message = "User is required")
    private Long userId;
    @NotNull(message = "Movie is required")
    private Long movieId;
    private Instant favoritedAt;
}
