package com.example.movies.service.implement;

import com.example.movies.dto.request.PromotionRequest;
import com.example.movies.dto.request.PromotionToMovieIdsRequest;
import com.example.movies.dto.response.PromotionResponse;
import com.example.movies.entity.Movies;
import com.example.movies.entity.Promotions;
import com.example.movies.entity.StatusPromotion;
import com.example.movies.repository.MoviesRepository;
import com.example.movies.repository.PromotionRepository;
import com.example.movies.service.PromotionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {
    private final PromotionRepository promotionRepository;
    private final MoviesRepository moviesRepository;
    @Override
    public List<PromotionResponse> getAllPromotions() {
        List<Promotions> promotions= promotionRepository.findAll();
        return promotions.stream().map(PromotionResponse::convertToPromotionRes).collect(Collectors.toList());
    }

    @Override
    public PromotionResponse getPromotionById(Long id) {
        return PromotionResponse.convertToPromotionRes( promotionRepository.findById(id).orElseThrow(()->new RuntimeException("not found promotion with id: "+id)));
    }

    @Override
    @Transactional
    public PromotionResponse addPromotion(PromotionRequest promotionRequest) {
        Promotions promotion = new Promotions();
        if (!promotionRequest.getEndDate().isAfter(promotionRequest.getStartDate())) {
            throw new RuntimeException("End date must be after start date");
        }
        promotionRequest.validateDiscount();
        promotion.setName(promotionRequest.getName());
        promotion.setDescription(promotionRequest.getDescription());
        promotion.setQuantity(promotionRequest.getQuantity());
        promotion.setStatus(StatusPromotion.ACTIVE);
        promotion.setDiscountAmount(promotionRequest.getDiscountAmount());
        promotion.setDiscountPercent(promotionRequest.getDiscountPercent());
        promotion.setStartDate(promotionRequest.getStartDate());
        promotion.setEndDate(promotionRequest.getEndDate());
        promotionRepository.save(promotion);
        return PromotionResponse.convertToPromotionRes(promotion);
    }

    @Override
    @Transactional
    public PromotionResponse updatePromotion(Long id, PromotionRequest promotionRequest) {
        Promotions promotion = promotionRepository.findById(id).orElseThrow(()->new RuntimeException("not found promotion with id: "+id));
        if (!promotionRequest.getEndDate().isAfter(promotionRequest.getStartDate())) {
            throw new RuntimeException("End date must be after start date");
        }
        promotion.setName(promotionRequest.getName());
        promotion.setDescription(promotionRequest.getDescription());
        promotion.setQuantity(promotionRequest.getQuantity());
        promotion.setStatus(promotionRequest.getStatus());
        promotion.setDiscountAmount(promotionRequest.getDiscountAmount());
        promotion.setDiscountPercent(promotionRequest.getDiscountPercent());
        promotion.setStartDate(promotionRequest.getStartDate());
        promotion.setEndDate(promotionRequest.getEndDate());
        promotionRepository.save(promotion);
        return PromotionResponse.convertToPromotionRes(promotion);
    }

    @Override
    @Transactional
    public PromotionResponse applyPromotionToMovies(Long promotionId, PromotionToMovieIdsRequest promotionToMovieIdsRequest) {
        Promotions promotions = promotionRepository.findById(promotionId).orElseThrow(()->new RuntimeException("not found promotion with id: "+promotionId));
        List<Movies> movies = moviesRepository.findAllById(promotionToMovieIdsRequest.getPromotionToMovieIds());
        if (movies.isEmpty()){
            throw new RuntimeException("No movies found for the given IDs");
        }
        for (Movies movie : movies) {
            movie.setPromotion(promotions);
        }
        moviesRepository.saveAll(movies);
        return PromotionResponse.convertToPromotionRes(promotions);
    }
}
