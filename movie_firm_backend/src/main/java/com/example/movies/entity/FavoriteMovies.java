package com.example.movies.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "favorite_movies")
public class FavoriteMovies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
    @ManyToOne()
    @JoinColumn(name = "movie_id",referencedColumnName = "id")
    private Movies movies;
    @Column(name = "favorited_at")
    private Instant favoritedAt;
}
