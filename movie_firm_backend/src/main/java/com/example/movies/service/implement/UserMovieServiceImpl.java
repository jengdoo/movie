package com.example.movies.service.implement;

import com.example.movies.dto.request.UserMoviesRequest;
import com.example.movies.dto.response.UserMoviesResponse;
import com.example.movies.entity.Movies;
import com.example.movies.entity.User;
import com.example.movies.entity.UserMovies;
import com.example.movies.repository.MoviesRepository;
import com.example.movies.repository.UserMoviesRepository;
import com.example.movies.repository.UserRepository;
import com.example.movies.service.UserMoviesService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserMovieServiceImpl implements UserMoviesService {
    private final UserMoviesRepository userMoviesRepository;
    private final UserRepository userRepository;
    private final MoviesRepository moviesRepository;
    @Override
    public List<UserMoviesResponse> getAllUserMovies() {
        return userMoviesRepository.findAll().stream().map(UserMoviesResponse::convert).collect(Collectors.toList());
    }

    @Override
    public UserMoviesResponse getUserMoviesById(Long id) {
        return UserMoviesResponse.convert(userMoviesRepository.findById(id).orElseThrow(()->new RuntimeException("not found user_movie by id"+id)));
    }

    @Override
    @Transactional
    public UserMoviesResponse addUserMovie(UserMoviesRequest userMoviesRequest) {
        Movies movies  = moviesRepository.findById(userMoviesRequest.getMovieId()).orElseThrow(()->new RuntimeException("not found movie with id:"+userMoviesRequest.getMovieId()));
        User user  = userRepository.findById(userMoviesRequest.getUserId()).orElseThrow(()->new RuntimeException("not found user with id:"+userMoviesRequest.getUserId()));
        UserMovies userMovies  = new UserMovies();
        userMovies.setMovie(movies);
        userMovies.setUser(user);
        userMovies.setPriceAtPurchase(userMoviesRequest.getPriceAtPurchase());
        userMovies.setPurchaseDate(Instant.now());
        userMoviesRepository.save(userMovies);
        return UserMoviesResponse.convert(userMovies);
    }

    @Override
    @Transactional
    public UserMoviesResponse updateUserMovie(Long id, UserMoviesRequest userMoviesRequest) {
        Movies movies  = moviesRepository.findById(userMoviesRequest.getMovieId()).orElseThrow(()->new RuntimeException("not found movie with id:"+userMoviesRequest.getMovieId()));
        User user  = userRepository.findById(userMoviesRequest.getUserId()).orElseThrow(()->new RuntimeException("not found user with id:"+userMoviesRequest.getUserId()));
        UserMovies userMovies  = userMoviesRepository.findById(id).orElseThrow(()->new RuntimeException("not found user_movie with id:"+id));
        userMovies.setMovie(movies);
        userMovies.setUser(user);
        userMovies.setPriceAtPurchase(userMoviesRequest.getPriceAtPurchase());
        userMovies.setPurchaseDate(Instant.now());
        userMoviesRepository.save(userMovies);
        return UserMoviesResponse.convert(userMovies);
    }

    @Override
    public void deleteUserMovie(Long id) {
        userMoviesRepository.deleteById(id);
    }
}
