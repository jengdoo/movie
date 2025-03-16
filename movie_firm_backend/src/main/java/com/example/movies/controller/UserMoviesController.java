package com.example.movies.controller;

import com.example.movies.dto.request.UserMoviesRequest;
import com.example.movies.dto.response.UserMoviesResponse;
import com.example.movies.service.UserMoviesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.path}/user-movies")
public class UserMoviesController {
    private final UserMoviesService userMoviesService;
    @GetMapping("/list-user_movies")
    public ResponseEntity<?> getAllUserMovies() {
        return ResponseEntity.ok(userMoviesService.getAllUserMovies());
    }
    @GetMapping("/userMoviesFindById/{id}")
    public ResponseEntity<?> findByIdUserMovie(@PathVariable Long id) {
        try {
            UserMoviesResponse userMoviesResponse = userMoviesService.getUserMoviesById(id);
            return ResponseEntity.ok(userMoviesResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/create-user_movie")
    public ResponseEntity<?> addUserMovie(@Valid @RequestBody UserMoviesRequest userMoviesRequest, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errorMessage = bindingResult.getFieldErrors()
                        .stream().map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessage);
            }
            UserMoviesResponse userMoviesResponse = userMoviesService.addUserMovie(userMoviesRequest);
            return ResponseEntity.ok(userMoviesResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/update-user_movie/{id}")
    public ResponseEntity<?> updateUserMovie(@PathVariable Long id,@Valid @RequestBody UserMoviesRequest userMoviesRequest, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errorMessage = bindingResult.getFieldErrors()
                        .stream().map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessage);
            }
            UserMoviesResponse userMoviesResponse = userMoviesService.updateUserMovie(id,userMoviesRequest);
            return ResponseEntity.ok(userMoviesResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/delete-user_movie")
    public ResponseEntity<?> deleteFavoriteMovie(@RequestParam Long id) {
        try {
             userMoviesService.deleteUserMovie(id);
            return ResponseEntity.ok("user_movie deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
