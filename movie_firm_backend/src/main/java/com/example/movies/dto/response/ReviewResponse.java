package com.example.movies.dto.response;

import com.example.movies.entity.Movies;
import com.example.movies.entity.Reviews;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewResponse {
    private Long id;
    private UserResponse user;
    private MoviesResponse movie;
    private Integer rating;
    private String comment;
    private Instant createdAt;
    private Instant updatedAt;
    public static ReviewResponse convert(Reviews reviews) {
        return ReviewResponse.builder()
                .id(reviews.getId())
                .user(UserResponse.convertUserToUserResponse(reviews.getUser()))
                .movie(MoviesResponse.convertMoviesToMoviesResponse(reviews.getMovie()))
                .rating(reviews.getRating())
                .comment(reviews.getComment())
                .createdAt(reviews.getCreatedAt())
                .updatedAt(reviews.getUpdatedAt())
                .build();
    }
}
