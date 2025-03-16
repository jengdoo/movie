package com.example.movies.repository;

import com.example.movies.entity.UserMovies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMoviesRepository extends JpaRepository<UserMovies, Long> {
}
