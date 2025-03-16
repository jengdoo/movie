package com.example.movies.repository;

import com.example.movies.entity.FavoriteMovies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteMoviesRepository extends JpaRepository<FavoriteMovies, Long> {
}
