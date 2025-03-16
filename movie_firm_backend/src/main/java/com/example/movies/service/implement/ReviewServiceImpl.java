package com.example.movies.service.implement;

import com.example.movies.dto.request.ReviewRequest;
import com.example.movies.dto.response.ReviewResponse;
import com.example.movies.entity.Movies;
import com.example.movies.entity.Reviews;
import com.example.movies.entity.User;
import com.example.movies.repository.MoviesRepository;
import com.example.movies.repository.ReviewRepository;
import com.example.movies.repository.UserRepository;
import com.example.movies.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final MoviesRepository moviesRepository;
    private final UserRepository userRepository;
    @Override
    public List<ReviewResponse> getAllReviews() {
        return reviewRepository.findAll().stream().map(ReviewResponse::convert).collect(Collectors.toList());
    }

    @Override
    public ReviewResponse getReviewById(Long id) {
        return reviewRepository.findById(id).map(ReviewResponse::convert).orElseThrow(()-> new RuntimeException("not found review with id:"+id));
    }

    @Override
    public ReviewResponse addReview(ReviewRequest reviewRequest) {
        Movies movies  = moviesRepository.findById(reviewRequest.getMovieId()).orElseThrow(()->new RuntimeException("not found movie with id:"+reviewRequest.getMovieId()));
        User user  = userRepository.findById(reviewRequest.getUserId()).orElseThrow(()->new RuntimeException("not found user with id:"+reviewRequest.getUserId()));
        Reviews reviews = new Reviews();
        reviews.setUser(user);
        reviews.setMovie(movies);
        reviews.setRating(reviewRequest.getRating());
        reviews.setComment(reviewRequest.getComment());
        reviews.setCreatedAt(Instant.now());
        reviews.setUpdatedAt(Instant.now());
        return ReviewResponse.convert(reviewRepository.save(reviews));
    }

    @Override
    public ReviewResponse updateReview(Long id, ReviewRequest reviewRequest) {
        Movies movies  = moviesRepository.findById(reviewRequest.getMovieId()).orElseThrow(()->new RuntimeException("not found movie with id:"+reviewRequest.getMovieId()));
        User user  = userRepository.findById(reviewRequest.getUserId()).orElseThrow(()->new RuntimeException("not found user with id:"+reviewRequest.getUserId()));
        Reviews reviews = reviewRepository.findById(id).orElseThrow(()->new RuntimeException("not found review with id:"+id));
        reviews.setUser(user);
        reviews.setMovie(movies);
        reviews.setRating(reviewRequest.getRating());
        reviews.setComment(reviewRequest.getComment());
        reviews.setCreatedAt(Instant.now());
        reviews.setUpdatedAt(Instant.now());
        return ReviewResponse.convert(reviewRepository.save(reviews));
    }

    @Override
    public void deleteReview(Long id) {
         Reviews reviews = reviewRepository.findById(id).orElseThrow(()->new RuntimeException("not found review with id:"+id));
         reviewRepository.delete(reviews);
    }
}
