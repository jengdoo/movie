package com.example.movies.controller;

import com.example.movies.dto.request.PromotionRequest;
import com.example.movies.dto.request.PromotionToMovieIdsRequest;
import com.example.movies.dto.response.PromotionResponse;
import com.example.movies.entity.Promotions;
import com.example.movies.service.PromotionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.path}/promotion")
public class PromotionController {
    private final PromotionService promotionService;

    @GetMapping("/list-promotions")
    public ResponseEntity<?> listPromotions() {
        try {
            List<PromotionResponse> promotions = promotionService.getAllPromotions();
            if(promotions.isEmpty() || promotions == null) {
                throw new IllegalArgumentException("Promotions list is empty");
            }
            return ResponseEntity.ok(promotions);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/findPromotionById/{id}")
    public ResponseEntity<?> findPromotionById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(promotionService.getPromotionById(id));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/create-promotion")
    public ResponseEntity<?> createPromotion(@Valid @RequestBody PromotionRequest promotionRequest, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errors = bindingResult.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                return ResponseEntity.badRequest().body(errors);
            }
            PromotionResponse promotionResponse = promotionService.addPromotion(promotionRequest);
            return ResponseEntity.ok(promotionResponse);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/create-promotion_to_movie/{promotionId}")
    public ResponseEntity<?> createApplyPromotionToMovie(@PathVariable Long promotionId,
                                                         @RequestBody() PromotionToMovieIdsRequest promotionToMovieIds) {
        try {
            PromotionResponse promotionResponse = promotionService.applyPromotionToMovies(promotionId,promotionToMovieIds);
            return ResponseEntity.ok(promotionResponse);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/update-promotion/{id}")
    public ResponseEntity<?> updatePromotion(@PathVariable Long id,@Valid @RequestBody PromotionRequest promotionRequest, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errors = bindingResult.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                return ResponseEntity.badRequest().body(errors);
            }
            PromotionResponse promotionResponse = promotionService.updatePromotion(id, promotionRequest);
            return ResponseEntity.ok(promotionResponse);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
