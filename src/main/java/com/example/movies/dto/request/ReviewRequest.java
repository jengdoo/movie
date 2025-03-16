package com.example.movies.dto.request;

import com.example.movies.entity.Movies;
import com.example.movies.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewRequest {
    @NotNull(message = "User is required")
    private Long userId;
    @NotNull(message = "Movie is required")
    private Long movieId;
    @Min(value = 1,message = "Rating can not be less than 1")
    @Max(value = 5,message = "Rating can not be greater than 5 ")
    private Integer rating;
    private String comment;
    private Instant createdAt;
    private Instant updatedAt;
}
