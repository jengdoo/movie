package com.example.movies.service;

import com.example.movies.dto.request.FavoriteMoviesRequest;
import com.example.movies.dto.response.FavoriteMoviesResponse;

import java.util.List;

public interface FavoriteMoviesService {
    List<FavoriteMoviesResponse> getAllFavoriteMovies();
    FavoriteMoviesResponse getFavoriteMoviesById(Long id);
    FavoriteMoviesResponse addFavoriteMovie(FavoriteMoviesRequest favoriteMoviesRequest);
    FavoriteMoviesResponse updateFavoriteMovie(Long id,FavoriteMoviesRequest favoriteMoviesRequest);
    void deleteFavoriteMovie(Long id);
}
