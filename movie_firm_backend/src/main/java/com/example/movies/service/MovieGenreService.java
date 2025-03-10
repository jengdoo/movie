package com.example.movies.service;

import com.example.movies.dto.request.MovieGenreRequest;
import com.example.movies.dto.response.MovieGenreResponse;

import java.util.List;

public interface MovieGenreService {
    List<MovieGenreResponse> getMovieGenre();
    MovieGenreResponse addMovieGenre(MovieGenreRequest movieGenreRequest);
    MovieGenreResponse updateMovieGenre(Long id,MovieGenreRequest movieGenreRequest);
    void deleteMovieGenre(Long id);
}
