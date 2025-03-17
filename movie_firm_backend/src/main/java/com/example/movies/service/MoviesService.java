package com.example.movies.service;

import com.example.movies.dto.request.MoviesRequest;
import com.example.movies.dto.response.MoviesResponse;
import com.example.movies.entity.Movies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MoviesService {
    Page<MoviesResponse> pageMovies(Integer page, Integer size);
    List<MoviesResponse> getALlMovies();

    MoviesResponse createMovie(MoviesRequest moviesRequest);

    MoviesResponse updateMovie(Long id,MoviesRequest moviesRequest);

    void deleteMovie(Long movieId);
    String uploadImage(MultipartFile file);
    List<String> uploadVideos(List<MultipartFile> files);
}
