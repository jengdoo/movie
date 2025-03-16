package com.example.movies.service;

import com.example.movies.dto.request.UserMoviesRequest;
import com.example.movies.dto.response.UserMoviesResponse;

import java.util.List;

public interface UserMoviesService {
    List<UserMoviesResponse> getAllUserMovies();
    UserMoviesResponse getUserMoviesById(Long id);
    UserMoviesResponse addUserMovie(UserMoviesRequest userMoviesRequest);
    UserMoviesResponse updateUserMovie(Long id,UserMoviesRequest userMoviesRequest);
    void deleteUserMovie(Long id);
}
