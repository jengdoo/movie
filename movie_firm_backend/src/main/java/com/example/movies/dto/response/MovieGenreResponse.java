package com.example.movies.dto.response;

import com.example.movies.entity.MovieGeners;
import com.example.movies.entity.Movies;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MovieGenreResponse {
    private Long genreId;
    private MoviesResponse moviesResponse;
    private String genre;
    public static MovieGenreResponse convertMovieGenreResponse(MovieGeners movieGeners) {
        return MovieGenreResponse.builder()
                .genreId(movieGeners.getId())
                .moviesResponse(MoviesResponse.convertMoviesToMoviesResponse(movieGeners.getMovie()))
                .genre(movieGeners.getGenre())
                .build();
    }
}
