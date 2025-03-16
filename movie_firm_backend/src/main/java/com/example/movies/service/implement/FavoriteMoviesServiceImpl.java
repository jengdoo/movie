package com.example.movies.service.implement;

import com.example.movies.dto.request.FavoriteMoviesRequest;
import com.example.movies.dto.response.FavoriteMoviesResponse;
import com.example.movies.entity.FavoriteMovies;
import com.example.movies.entity.Movies;
import com.example.movies.entity.User;
import com.example.movies.repository.FavoriteMoviesRepository;
import com.example.movies.repository.MoviesRepository;
import com.example.movies.repository.UserRepository;
import com.example.movies.service.FavoriteMoviesService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteMoviesServiceImpl implements FavoriteMoviesService {
    private final FavoriteMoviesRepository favoriteMoviesRepository;
    private final MoviesRepository moviesRepository;
    private final UserRepository userRepository;
    @Override
    public List<FavoriteMoviesResponse> getAllFavoriteMovies() {
        return favoriteMoviesRepository.findAll().stream().map(FavoriteMoviesResponse::convert).collect(Collectors.toList());
    }

    @Override
    public FavoriteMoviesResponse getFavoriteMoviesById(Long id) {
        return FavoriteMoviesResponse.convert(favoriteMoviesRepository.findById(id).orElseThrow(()->new RuntimeException("not found favourite movie with id:"+id)));
    }

    @Override
    @Transactional
    public FavoriteMoviesResponse addFavoriteMovie(FavoriteMoviesRequest favoriteMoviesRequest) {
        Movies movies  = moviesRepository.findById(favoriteMoviesRequest.getMovieId()).orElseThrow(()->new RuntimeException("not found movie with id:"+favoriteMoviesRequest.getMovieId()));
        User user  = userRepository.findById(favoriteMoviesRequest.getUserId()).orElseThrow(()->new RuntimeException("not found user with id:"+favoriteMoviesRequest.getUserId()));
        FavoriteMovies favoriteMovies = new FavoriteMovies();
        favoriteMovies.setUser(user);
        favoriteMovies.setMovies(movies);
        favoriteMovies.setFavoritedAt(Instant.now());
        favoriteMoviesRepository.save(favoriteMovies);
        return FavoriteMoviesResponse.convert(favoriteMovies);
    }

    @Override
    @Transactional
    public FavoriteMoviesResponse updateFavoriteMovie(Long id, FavoriteMoviesRequest favoriteMoviesRequest) {
        Movies movies  = moviesRepository.findById(favoriteMoviesRequest.getMovieId()).orElseThrow(()->new RuntimeException("not found movie with id:"+favoriteMoviesRequest.getMovieId()));
        User user  = userRepository.findById(favoriteMoviesRequest.getUserId()).orElseThrow(()->new RuntimeException("not found user with id:"+favoriteMoviesRequest.getUserId()));
        FavoriteMovies favoriteMovies = favoriteMoviesRepository.findById(id).orElseThrow(()-> new RuntimeException("not found favourite movie with id:"+id));
        favoriteMovies.setUser(user);
        favoriteMovies.setMovies(movies);
        favoriteMovies.setFavoritedAt(Instant.now());
        favoriteMoviesRepository.save(favoriteMovies);
        return FavoriteMoviesResponse.convert(favoriteMovies);
    }

    @Override
    public void deleteFavoriteMovie(Long id) {
        favoriteMoviesRepository.delete(favoriteMoviesRepository.findById(id).orElseThrow(()->new RuntimeException("not found favourite movie with id:"+id)));
    }
}
