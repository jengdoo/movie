package com.example.movies.controller;

import com.example.movies.dto.request.EpisodesRequest;
import com.example.movies.dto.response.EpisodesResponse;
import com.example.movies.entity.Episodes;
import com.example.movies.service.EpisodesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.path}/videos")
public class EpisodesController {
    private final EpisodesService episodesService;
    @GetMapping("/list-videos")
    public ResponseEntity<?> getAllEpisodes() {
        return ResponseEntity.ok(episodesService.getAllEpisodes());
    }
    @PostMapping(value = "/create-videos",consumes = "multipart/form-data")
    public ResponseEntity<?> createEpisode(@Valid @ModelAttribute("episodesRequest") EpisodesRequest episodesRequest,
                                           @RequestPart("videos") MultipartFile videos,
                                           BindingResult bindingResult
                                           ) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errorMessages = bindingResult.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                return ResponseEntity.badRequest().body(errorMessages);
            }
            if (videos != null && !videos.isEmpty()) {
                String urlVideo = episodesService.uploadVideo(videos);
                episodesRequest.setVideoUrl(urlVideo);
            }
            EpisodesResponse episodesResponse = episodesService.addEpisode(episodesRequest);
            return ResponseEntity.ok(episodesResponse);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping(value = "/update-videos/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateEpisode(
            @PathVariable Long id,
            @Valid @ModelAttribute("episodesRequest") EpisodesRequest episodesRequest, // Dùng ModelAttribute thay vì RequestPart
            BindingResult bindingResult,
            @RequestPart(value = "videos", required = false) MultipartFile videos) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errorMessages = bindingResult.getFieldErrors()
                        .stream().map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                return ResponseEntity.badRequest().body(errorMessages);
            }

            if (videos != null && !videos.isEmpty()) {
                String urlVideo = episodesService.uploadVideo(videos);
                episodesRequest.setVideoUrl(urlVideo);
            }

            EpisodesResponse episodesResponse = episodesService.updateEpisode(id, episodesRequest);
            return ResponseEntity.ok(episodesResponse);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-videos")
    public ResponseEntity<?> deleteEpisode(@RequestParam Long id){
        try {
            episodesService.deleteEpisode(id);
            return ResponseEntity.ok("delete success");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/findById-videos/{id}")
    public ResponseEntity<?> getEpisodeById(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(episodesService.getEpisodeById(id));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
