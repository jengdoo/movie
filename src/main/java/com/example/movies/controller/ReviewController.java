package com.example.movies.controller;

import com.example.movies.dto.request.ReviewRequest;
import com.example.movies.dto.response.ReviewResponse;
import com.example.movies.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.path}/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/list-review")
    public ResponseEntity<?> listReview() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @GetMapping("/findByReviewId/{id}")
    public ResponseEntity<?> findByReviewId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(reviewService.getReviewById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/create-review")
    public ResponseEntity<?> createReview(@Valid @RequestBody ReviewRequest reviewRequest, BindingResult bindingResult) {
        try {
            if(bindingResult.hasErrors()) {
                List<String> errorMessages = bindingResult.getFieldErrors()
                        .stream().map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                return ResponseEntity.badRequest().body(errorMessages);
            }
            ReviewResponse reviewResponse = reviewService.addReview(reviewRequest);
            return ResponseEntity.ok(reviewResponse);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/update-review/{id}")
    public ResponseEntity<?> updateReview(@PathVariable Long id,@Valid @RequestBody ReviewRequest reviewRequest, BindingResult bindingResult) {
        try {
            if(bindingResult.hasErrors()) {
                List<String> errorMessages = bindingResult.getFieldErrors()
                        .stream().map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                return ResponseEntity.badRequest().body(errorMessages);
            }
            ReviewResponse reviewResponse = reviewService.updateReview(id, reviewRequest);
            return ResponseEntity.ok(reviewResponse);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/delete-review")
    public ResponseEntity<?> deleteReview(@RequestParam Long id) {
        try {
            reviewService.deleteReview(id);
            return ResponseEntity.ok("review with id: "+id +" deleted");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
