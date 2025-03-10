package com.example.movies.dto.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class EpisodesRequest {
    private Long movieId;
    @Min(value = 0,message = "episodeNumber can not be less than 0")
    private Integer episodeNumber;
    @NotNull(message = "title is required")
    private String title;
    private String videoUrl;
    @Min(value = 0,message = "duration can not be less than 0")
    private Integer duration;
    @Min(value = 0,message = "views can not be less than 0")
    private Integer views;
}
