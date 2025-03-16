package com.example.movies.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "movies")
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "genre")
    private String genre;
    @Column(name = "rating")
    private Integer rating;
    @Column(name = "thumbnail_url")
    private String thumbnailUrl;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(name = "is_free")
    private Boolean isFree;
    @Column(name = "price")
    private Double price;
    @Column(name = "discount_percent")
    private Double discountPercent;
    @Column(name = "discount_expiry")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime discountExpiry;
    @Column(name = "release_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime releaseDate;
    @Column(name = "director")
    private String director;
    @Column(name = "duration")
    private Integer duration;
    @Column(name = "views")
    private Integer views;
    @Column(name = "created_at")
    private Instant createdAt;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Episodes> episodes;
    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<UserMovies> userMovies;
    @ManyToOne()
    @JoinColumn(name = "promotion_id",referencedColumnName = "id")
    private Promotions promotion;
    @OneToMany(mappedBy = "movies",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<FavoriteMovies> favoriteMovies;
}
