package com.example.movies.controller;

import com.example.movies.dto.request.MovieGenreRequest;
import com.example.movies.dto.response.MovieGenreResponse;
import com.example.movies.entity.MovieGeners;
import com.example.movies.service.MovieGenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.path}/movie-genre")
public class MovieGenreController {
    private final MovieGenreService movieGenreService;
    @GetMapping("/list-genre")
    public ResponseEntity<?> findAll() {
        try {
            List<MovieGenreResponse> movieGenreResponses = movieGenreService.getMovieGenre();
            return new ResponseEntity<>(movieGenreResponses, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/create-genre")
    public ResponseEntity<?> create(@Valid @RequestBody MovieGenreRequest movieGenreRequest, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errorMessages = bindingResult.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
            }
            MovieGenreResponse movieGenreResponse = movieGenreService.addMovieGenre(movieGenreRequest);
            return new ResponseEntity<>(movieGenreResponse, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update-genre/{id}")
    public ResponseEntity<?> update( @PathVariable Long id,@Valid @RequestBody MovieGenreRequest movieGenreRequest, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errorMessages = bindingResult.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
            }
            MovieGenreResponse movieGenreResponse = movieGenreService.updateMovieGenre(id,movieGenreRequest);
            return new ResponseEntity<>(movieGenreResponse, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete-genre")
    public ResponseEntity<?> delete(@RequestParam Long id) {
        try {
            movieGenreService.deleteMovieGenre(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
