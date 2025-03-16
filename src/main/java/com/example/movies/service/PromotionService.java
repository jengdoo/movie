package com.example.movies.service;

import com.example.movies.dto.request.PromotionRequest;
import com.example.movies.dto.request.PromotionToMovieIdsRequest;
import com.example.movies.dto.response.PromotionResponse;

import java.util.List;

public interface PromotionService {
    List<PromotionResponse> getAllPromotions();
    PromotionResponse getPromotionById(Long id);
    PromotionResponse addPromotion(PromotionRequest promotionRequest);
    PromotionResponse updatePromotion(Long id,PromotionRequest promotionRequest);
    PromotionResponse applyPromotionToMovies(Long promotionId, PromotionToMovieIdsRequest promotionToMovieIdsRequest);
}
