package com.example.movies.controller;

import com.example.movies.dto.request.MoviesRequest;
import com.example.movies.dto.response.MoviesResponse;
import com.example.movies.service.MoviesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("${api.path}/movies")
public class MoviesController {
    private final MoviesService moviesService;
    @GetMapping("/list")
    public ResponseEntity<?> getAllMovies() {
        List<MoviesResponse> moviesResponses = moviesService.getALlMovies();
        return ResponseEntity.ok(moviesResponses);
    }
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createMovie(
            @RequestParam("moviesRequest") String moviesRequestJson, // Nhận JSON dưới dạng String
            @RequestPart(value = "thumbnail", required = false) MultipartFile thumbnail) {
        try {
            // Chuyển JSON string thành Java object
            ObjectMapper objectMapper = new ObjectMapper();
            MoviesRequest moviesRequest = objectMapper.readValue(moviesRequestJson, MoviesRequest.class);

            // Upload ảnh
            if (thumbnail != null && !thumbnail.isEmpty()) {
                String imageUrl = moviesService.uploadImage(thumbnail);
                moviesRequest.setThumbnailUrl(imageUrl);
            }

            // Gọi service tạo phim
            MoviesResponse moviesResponse = moviesService.createMovie(moviesRequest);
            if (moviesResponse != null) {
                return ResponseEntity.ok(moviesResponse);
            }
            return ResponseEntity.badRequest().body("Movie creation failed");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable Long id, @Valid @RequestBody MoviesRequest moviesRequest, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errorMessage = bindingResult.getFieldErrors()
                        .stream().map(FieldError::getDefaultMessage)
                        .toList();
            }
            MoviesResponse moviesResponse = moviesService.updateMovie(id,moviesRequest);
            if(moviesResponse != null) {
                return ResponseEntity.ok(moviesResponse);
            }
            return ResponseEntity.badRequest().body("movie update failed");
        } catch (Exception e) {
           return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteMovie(@RequestParam Long id) {
        try {
            moviesService.deleteMovie(id);
            return ResponseEntity.ok().body("movie deleted");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/upload-videos")
    public ResponseEntity<?> uploadMultipleVideos(@RequestParam("videos") List<MultipartFile> videos) {
        List<String> videoUrls = moviesService.uploadVideos(videos);
        return ResponseEntity.ok(videoUrls);
    }
}
