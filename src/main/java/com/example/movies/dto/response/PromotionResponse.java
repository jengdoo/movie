package com.example.movies.dto.response;

import com.example.movies.entity.Promotions;
import com.example.movies.entity.StatusPromotion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PromotionResponse {
    private Long id;
    private String name;
    private int quantity;
    private String description;
    private Double discountPercent;
    private Double discountAmount;
    private String startDate;
    private String endDate;
    private StatusPromotion status;
    private List<MoviesResponse> movies;

    public static PromotionResponse convertToPromotionRes(Promotions promotions) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT; // Chuyển Instant thành chuỗi ISO 8601

        return PromotionResponse.builder()
                .id(promotions.getId())
                .name(promotions.getName())
                .quantity(promotions.getQuantity())
                .description(promotions.getDescription())
                .discountPercent(promotions.getDiscountPercent())
                .discountAmount(promotions.getDiscountAmount())
                .startDate(promotions.getStartDate() != null ? promotions.getStartDate().atZone(ZoneOffset.UTC).format(formatter) : null)
                .endDate(promotions.getEndDate() != null ? promotions.getEndDate().atZone(ZoneOffset.UTC).format(formatter) : null)
                .status(promotions.getStatus())
                .movies(promotions.getMovies() != null
                        ? promotions.getMovies().stream().map(MoviesResponse::convertMoviesToMoviesResponse).toList()
                        : null
                )
                .build();
    }

}
