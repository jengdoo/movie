package com.example.movies.repository;

import com.example.movies.entity.MovieGeners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieGenreRepository extends JpaRepository<MovieGeners, Long> {
}
