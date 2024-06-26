package com.example.moviebooking.controller;

import com.example.moviebooking.dto.MovieDto;
import com.example.moviebooking.service.impl.MovieServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import java.util.List;


@RestController
@RequestMapping("/api/v1/movie")
@CrossOrigin(origins = {"*"})
@Validated
public class MovieController {
    private final MovieServiceImpl movieService;

    public MovieController(final MovieServiceImpl movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovieDto>> getAll() {
        return ResponseEntity.ok(movieService.getAll());
    }

    @GetMapping("/byId")
    public ResponseEntity<MovieDto> findMovieById(
            @RequestParam("id") @NotEmpty(message = "Movie id cannot be null") final Long id) {
        return ResponseEntity.ok(movieService.findById(id));
    }

    @GetMapping("/byTitle")
    public ResponseEntity<MovieDto> findMovieByTitle(
            @RequestParam("title") @NotEmpty(message = "Movie title cannot be null") final String title) {
        return ResponseEntity.ok(movieService.findByTitle(title));
    }

    @PostMapping("/save")
    public ResponseEntity<MovieDto> saveMovie(@RequestBody MovieDto movieDto) {
        System.out.println("Got here-------------");
        return ResponseEntity.ok(movieService.save(movieDto));
    }

    @GetMapping("/getImageSliderMovies")
    public ResponseEntity<List<MovieDto>> getImageSliderMovies() {
        return ResponseEntity.ok(movieService.getImageSliderMovies());
    }

    @GetMapping("/getRecommendedMovies")
    public  ResponseEntity<List<MovieDto>> getRecommendedMovies() {
        return ResponseEntity.ok(movieService.getRecommendedMovies());
    }

}
