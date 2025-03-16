package com.example.movies.service;

import com.example.movies.dto.request.EpisodesRequest;
import com.example.movies.dto.response.EpisodesResponse;
import com.example.movies.entity.Episodes;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EpisodesService {
    List<EpisodesResponse> getAllEpisodes();
    EpisodesResponse getEpisodeById(Long id);
    EpisodesResponse addEpisode(EpisodesRequest episodesRequest);
    EpisodesResponse updateEpisode(Long id,EpisodesRequest episodesRequest);
    void deleteEpisode(Long id);
    String uploadVideo(MultipartFile file);
}
