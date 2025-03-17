package com.example.movies.service.implement;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.movies.dto.request.MoviesRequest;
import com.example.movies.dto.response.MoviesResponse;
import com.example.movies.entity.Movies;
import com.example.movies.repository.MoviesRepository;
import com.example.movies.service.MoviesService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MoviesServiceImpl implements MoviesService {
    private final MoviesRepository moviesRepository;

    private final Cloudinary cloudinary;

    @Override
    public Page<MoviesResponse> pageMovies(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Movies> movies = moviesRepository.findAll(pageable);
        return movies.map(MoviesResponse::convertMoviesToMoviesResponse);
    }

    @Override
    public List<MoviesResponse> getALlMovies() {
        List<Movies> movies = moviesRepository.findAll();
        return movies.stream().map(MoviesResponse::convertMoviesToMoviesResponse).toList();
    }

    @Override
    @Transactional
    public MoviesResponse createMovie(MoviesRequest moviesRequest) {
        Movies movieses = moviesRepository.save(
                Movies.builder()
                        .title(moviesRequest.getTitle())
                        .description(moviesRequest.getDescription())
                        .genre(moviesRequest.getGenre())
                        .rating(moviesRequest.getRating()!=null?moviesRequest.getRating():0)
                        .thumbnailUrl(moviesRequest.getThumbnailUrl())
                        .type(moviesRequest.getType())
                        .isFree(moviesRequest.getIsFree()!=null?moviesRequest.getIsFree():false)
                        .price(moviesRequest.getPrice()!=null?moviesRequest.getPrice():0)
                        .discountPercent(moviesRequest.getDiscountPercent())
                        .discountExpiry(moviesRequest.getDiscountExpiry())
                        .releaseDate(moviesRequest.getReleaseDate())
                        .director(moviesRequest.getDirector())
                        .duration(moviesRequest.getDuration()!=null?moviesRequest.getDuration():0)
                        .views(0)
                        .createdAt(Instant.now())
                        .build()
        );
        return MoviesResponse.convertMoviesToMoviesResponse(movieses);
    }

    @Override
    @Transactional
    public MoviesResponse updateMovie(Long id,MoviesRequest moviesRequest) {
        Movies moviese = (Movies) moviesRepository.findById((id)).orElseThrow(()->new RuntimeException("not found movie with id:"+ id));
        try {
                moviese.setTitle(moviesRequest.getTitle());
                moviese.setDescription(moviesRequest.getDescription());
                moviese.setGenre(moviesRequest.getGenre());
                moviese.setRating(moviesRequest.getRating());
                moviese.setThumbnailUrl(moviesRequest.getThumbnailUrl());
                moviese.setType(moviesRequest.getType());
                moviese.setIsFree(moviesRequest.getIsFree());
                moviese.setPrice(moviesRequest.getPrice());
                moviese.setDiscountPercent(moviesRequest.getDiscountPercent());
                moviese.setDiscountExpiry(moviesRequest.getDiscountExpiry());
                moviese.setReleaseDate(moviesRequest.getReleaseDate());
                moviese.setDirector(moviesRequest.getDirector());
                moviese.setDuration(moviesRequest.getDuration());
                moviese.setViews(moviesRequest.getViews());
                Movies movies = moviesRepository.save(moviese);
                return MoviesResponse.convertMoviesToMoviesResponse(movies);
        }catch (Exception e) {
            return null;
        }

    }

    @Override
    public void deleteMovie(Long movieId) {
        Movies moviese = (Movies) moviesRepository.findById((movieId)).orElseThrow(()->new RuntimeException("not found movie with id:"+ movieId));
        moviesRepository.delete(moviese);
    }

    @Override
    public String uploadImage(MultipartFile file) {
        assert file.getOriginalFilename() != null;
        String publicValue = generateValue(file.getOriginalFilename());
        String extension = getFieldName(file.getOriginalFilename())[1];
        File fileUpload = convert(file);
        log.info("uploading image to Cloudinary: {}", fileUpload);
        log.info("publicValue: {}", publicValue);
        log.info("extension: {}", extension);
        try {
            cloudinary.uploader().upload(fileUpload, ObjectUtils.asMap("public_id", publicValue));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        cleanDisk(fileUpload);
        return cloudinary.url().generate(StringUtils.join(publicValue, ".",extension));
    }
    private void cleanDisk(File file){
        try {
            log.info("file toPath: {}", file.toPath());
            Path filePath = file.toPath();
            Files.delete(filePath);
        }catch (Exception e){
            log.error("ERROR");
        }
    }
    private File convert(MultipartFile file) {
        assert file.getOriginalFilename() != null;
        File converFile = new File(StringUtils.join(generateValue(file.getOriginalFilename()),getFieldName(file.getOriginalFilename())[1]));
        try (InputStream is = file.getInputStream()){
            Files.copy(is, converFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return converFile;
    }
    public String generateValue(String originalName){
        String fileName = getFieldName(originalName)[0];
        return StringUtils.join(UUID.randomUUID().toString(), "_",fileName);
    }
    public String[] getFieldName(String originalName){
        return originalName.split("\\.");
    }
    @Override
    public List<String> uploadVideos(List<MultipartFile> files) {
        List<String> videoUrls = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                        ObjectUtils.asMap("resource_type", "video"));
                videoUrls.add(uploadResult.get("secure_url").toString());
            } catch (IOException e) {
                throw new RuntimeException("Upload failed: " + file.getOriginalFilename(), e);
            }
        }
        return videoUrls;
    }
}
