package com.example.movies.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "episodes")
public class Episodes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movies movie;
    @Column(name = "episode_number")
    private Integer episodeNumber;
    @Column(name = "title")
    private String title;
    @Column(name = "video_url")
    private String videoUrl;
    @Column(name = "duration")
    private Integer duration;
    @Column(name = "views")
    private Integer views;
}
