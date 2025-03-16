package com.example.movies.dto.response;

import com.example.movies.entity.Movies;
import com.example.movies.entity.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDateTime;

@Builder
@Getter
public class MoviesResponse {
    private Long id;
    private String title;
    private String description;
    private String genre;
    private Integer rating;
    private String thumbnailUrl;
    private Type type;
    private Boolean isFree;
    private Double price;
    private Double discountPercent;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime discountExpiry;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime releaseDate;
    private String director;
    private Integer duration;
    private Integer views;
    private Instant createdAt;

    public static MoviesResponse convertMoviesToMoviesResponse(Movies movies) {
        return MoviesResponse.builder()
                .id(movies.getId())
                .title(movies.getTitle())
                .description(movies.getDescription())
                .genre(movies.getGenre())
                .rating(movies.getRating())
                .thumbnailUrl(movies.getThumbnailUrl())
                .type(movies.getType())
                .isFree(movies.getIsFree())
                .price(movies.getPrice())
                .discountPercent(movies.getDiscountPercent())
                .discountExpiry(movies.getDiscountExpiry())
                .releaseDate(movies.getReleaseDate())
                .director(movies.getDirector())
                .duration(movies.getDuration())
                .views(movies.getViews())
                .createdAt(movies.getCreatedAt())
                .build();
    }
}
