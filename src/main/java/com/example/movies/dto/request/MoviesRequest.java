package com.example.movies.dto.request;

import com.example.movies.entity.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MoviesRequest {
    @NotNull(message = "tile is not null")
    private String title;
    @NotNull(message = "description is not null")
    private String description;
    private String genre;
    @Min(value = 0, message = "Rating must be at least 0")
    @Max(value = 10, message = "Rating must not exceed 10")
    private Integer rating;
    private String thumbnailUrl;
    @NotNull(message = "Type is required")
    private Type type;
    private Boolean isFree;
    @Min(value =0,message = "price not less than 0")
    @NotNull(message = "Price is required")
    private Double price;
    private Double discountPercent;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime discountExpiry;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime releaseDate;
    @NotNull(message = "Director is required")
    private String director;
    private Integer duration;
    private Integer views;
    private Instant createdAt;
}
