package com.example.movies.repository;

import com.example.movies.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MoviesRepository extends JpaRepository<Movies, Integer> {
    List<Movies> id(Long id);

    Optional<Movies> findById(Long id);

}
