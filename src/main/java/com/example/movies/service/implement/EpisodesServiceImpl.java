package com.example.movies.service.implement;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.movies.dto.request.EpisodesRequest;
import com.example.movies.dto.response.EpisodesResponse;
import com.example.movies.entity.Episodes;
import com.example.movies.entity.Movies;
import com.example.movies.repository.EpisodesRepository;
import com.example.movies.repository.MoviesRepository;
import com.example.movies.service.EpisodesService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EpisodesServiceImpl implements EpisodesService {
    private final EpisodesRepository episodesRepository;
    private final MoviesRepository moviesRepository;
    private final Cloudinary cloudinary;

    @Override
    public List<EpisodesResponse> getAllEpisodes() {
        return episodesRepository.findAll().stream().map(EpisodesResponse::fromEpisodesResponse).collect(Collectors.toList());
    }

    @Override
    public EpisodesResponse getEpisodeById(Long id) {
        return EpisodesResponse.fromEpisodesResponse(
                 episodesRepository.findById(id).orElseThrow(()->new RuntimeException("not found episodes with id:"+id))
        );
    }

    @Override
    @Transactional
    public EpisodesResponse addEpisode(EpisodesRequest episodesRequest) {
        Movies movies = (Movies) moviesRepository.findById(episodesRequest.getMovieId()).orElseThrow(()->new RuntimeException("not found movie with id:"+ episodesRequest.getMovieId()));
        Episodes episodes = new Episodes();
        episodes.setTitle(episodesRequest.getTitle());
        episodes.setEpisodeNumber(episodesRequest.getEpisodeNumber());
        episodes.setMovie(movies);
        episodes.setDuration(episodesRequest.getDuration());
        episodes.setViews(episodesRequest.getViews());
        episodes.setVideoUrl(episodesRequest.getVideoUrl());
        episodesRepository.save(episodes);
        return EpisodesResponse.fromEpisodesResponse(episodes);
    }

    @Override
    @Transactional
    public EpisodesResponse updateEpisode(Long id, EpisodesRequest episodesRequest) {
        Movies movies = moviesRepository.findById(episodesRequest.getMovieId())
                .orElseThrow(() -> new RuntimeException("Not found movie with id: " + episodesRequest.getMovieId()));

        Episodes episodes = episodesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found episode with id: " + id));

        episodes.setTitle(episodesRequest.getTitle());
        episodes.setEpisodeNumber(episodesRequest.getEpisodeNumber());
        episodes.setMovie(movies);
        episodes.setDuration(episodesRequest.getDuration());
        episodes.setViews(episodesRequest.getViews());
        episodes.setVideoUrl(episodesRequest.getVideoUrl());
        episodesRepository.save(episodes);
        return EpisodesResponse.fromEpisodesResponse(episodes);
    }

    @Override
    public void deleteEpisode(Long id) {
        Episodes episodes = episodesRepository.findById(id).orElseThrow(()->new RuntimeException("not found episodes with id:"+id));
        episodesRepository.delete(episodes);
    }

    @Override
    public String uploadVideo(MultipartFile file) {
        try {
            Map uploadVideos = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type","video"));
            return uploadVideos.get("secure_url").toString();
        }catch (IOException e) {
            throw new RuntimeException("Upload failed: " + file.getOriginalFilename(), e);
        }
    }
}
