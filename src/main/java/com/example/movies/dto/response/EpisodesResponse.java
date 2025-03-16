package com.example.movies.dto.response;


import com.example.movies.entity.Episodes;
import com.example.movies.entity.Movies;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EpisodesResponse {
    private Long episodeId;
    private MoviesResponse moviesResponse;
    private Integer episodeNumber;
    private String title;
    private String videoUrl;
    private Integer duration;
    private Integer views;

    public static EpisodesResponse fromEpisodesResponse(Episodes episodes) {
        return EpisodesResponse.builder()
                .episodeId(episodes.getId())
                .moviesResponse(MoviesResponse.convertMoviesToMoviesResponse(episodes.getMovie()))
                .episodeNumber(episodes.getEpisodeNumber())
                .title(episodes.getTitle())
                .videoUrl(episodes.getVideoUrl())
                .duration(episodes.getDuration())
                .views(episodes.getViews())
                .build();
    }
}
