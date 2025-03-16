package com.example.movies.service;

import com.example.movies.dto.request.ReviewRequest;
import com.example.movies.dto.response.ReviewResponse;

import java.util.List;

public interface ReviewService {
    List<ReviewResponse> getAllReviews();
    ReviewResponse getReviewById(Long id);
    ReviewResponse addReview(ReviewRequest reviewRequest);
    ReviewResponse updateReview(Long id,ReviewRequest reviewRequest);
    void deleteReview(Long id);
}
