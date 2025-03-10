package com.example.movies.repository;

import com.example.movies.entity.Episodes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;
import java.util.List;
import java.util.Optional;

@Repository
public interface EpisodesRepository extends JpaRepository<Episodes, Integer> {
    Optional<Episodes> findById(Long id);
}
