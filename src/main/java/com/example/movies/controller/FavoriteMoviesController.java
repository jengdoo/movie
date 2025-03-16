package com.example.movies.controller;

import com.example.movies.dto.request.FavoriteMoviesRequest;
import com.example.movies.dto.response.FavoriteMoviesResponse;
import com.example.movies.entity.FavoriteMovies;
import com.example.movies.service.FavoriteMoviesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.path}/favorite-movies")
public class FavoriteMoviesController {
    private final FavoriteMoviesService favoriteMoviesService;
    @GetMapping("/list-favorite")
    public ResponseEntity<?> getAllFavoriteMovies() {
        return ResponseEntity.ok(favoriteMoviesService.getAllFavoriteMovies());
    }
    @GetMapping("/favoriteFindById/{id}")
    public ResponseEntity<?> findByIdFavoriteMovie(@PathVariable Long id) {
        try {
            FavoriteMoviesResponse favoriteMoviesResponse = favoriteMoviesService.getFavoriteMoviesById(id);
            return ResponseEntity.ok(favoriteMoviesResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/create-favorite")
    public ResponseEntity<?> addFavoriteMovie(@Valid @RequestBody FavoriteMoviesRequest favoriteMoviesRequest, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errorMessage = bindingResult.getFieldErrors()
                        .stream().map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessage);
            }
            FavoriteMoviesResponse favoriteMoviesResponse = favoriteMoviesService.addFavoriteMovie(favoriteMoviesRequest);
            return ResponseEntity.ok(favoriteMoviesResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/update-favorite/{id}")
    public ResponseEntity<?> updateFavoriteMovie(@PathVariable Long id,@Valid @RequestBody FavoriteMoviesRequest favoriteMoviesRequest, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errorMessage = bindingResult.getFieldErrors()
                        .stream().map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessage);
            }
            FavoriteMoviesResponse favoriteMoviesResponse = favoriteMoviesService.updateFavoriteMovie(id,favoriteMoviesRequest);
            return ResponseEntity.ok(favoriteMoviesResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/delete-favorite")
    public ResponseEntity<?> deleteFavoriteMovie(@RequestParam Long id) {
        try {
             favoriteMoviesService.deleteFavoriteMovie(id);
            return ResponseEntity.ok("favorite movie deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
