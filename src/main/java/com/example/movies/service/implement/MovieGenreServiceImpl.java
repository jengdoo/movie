package com.example.movies.service.implement;

import com.example.movies.dto.request.MovieGenreRequest;
import com.example.movies.dto.response.MovieGenreResponse;
import com.example.movies.entity.MovieGeners;
import com.example.movies.entity.Movies;
import com.example.movies.repository.MovieGenreRepository;
import com.example.movies.repository.MoviesRepository;
import com.example.movies.service.MovieGenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieGenreServiceImpl implements MovieGenreService {
    private final MovieGenreRepository movieGenreRepository;
    private final MoviesRepository moviesRepository;
    @Override
    public List<MovieGenreResponse> getMovieGenre() {
        return movieGenreRepository.findAll().stream().map(MovieGenreResponse::convertMovieGenreResponse).toList();
    }

    @Override
    public MovieGenreResponse addMovieGenre(MovieGenreRequest movieGenreRequest) {
        Movies movies = (Movies) moviesRepository.findById(movieGenreRequest.getMovieId()).orElseThrow(()->new RuntimeException("not found movies with id:"+movieGenreRequest.getMovieId()));
        MovieGeners movieGeners = new MovieGeners();
        movieGeners.setMovie(movies);
        movieGeners.setGenre(movieGenreRequest.getGenre());
        movieGenreRepository.save(movieGeners);
        return MovieGenreResponse.convertMovieGenreResponse(movieGeners);
    }

    @Override
    public MovieGenreResponse updateMovieGenre(Long id, MovieGenreRequest movieGenreRequest) {
        Movies movies = (Movies) moviesRepository.findById(movieGenreRequest.getMovieId()).orElseThrow(()->new RuntimeException("not found movies with id:"+movieGenreRequest.getMovieId()));
        MovieGeners movieGeners = movieGenreRepository.findById(id).orElseThrow(()-> new RuntimeException("not found movie genre with id:"+id));
        movieGeners.setGenre(movieGenreRequest.getGenre());
        movieGeners.setMovie(movies);
        movieGenreRepository.save(movieGeners);
        return MovieGenreResponse.convertMovieGenreResponse(movieGeners);
    }

    @Override
    public void deleteMovieGenre(Long id) {
        MovieGeners movieGeners = movieGenreRepository.findById(id).orElseThrow(()-> new RuntimeException("not found movie genre with id:"+id));
        movieGenreRepository.delete(movieGeners);
    }
}
