package com.example.moviebooking.controller;

import com.example.moviebooking.dto.ShowsDto;
import com.example.moviebooking.service.impl.ShowsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/v1/shows")
public class ShowsController {
    private final ShowsServiceImpl showsService;

    public ShowsController(final ShowsServiceImpl showsService) {
        this.showsService = showsService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ShowsDto>> findAll() {
        return ResponseEntity.ok(showsService.findAll());
    }

    @GetMapping("/byId")
    public ResponseEntity<ShowsDto> findById(@RequestParam("id") @NotNull final Long id) {
        return ResponseEntity.ok(showsService.findById(id));
    }

    @GetMapping("/byScreenId")
    public ResponseEntity<List<ShowsDto>> findAllByScreenId(@RequestParam("screenId") @NotNull final Long screenId) {
        return ResponseEntity.ok(showsService.findAllByScreenId(screenId));
    }

    @GetMapping("/byMovieId")
    public ResponseEntity<List<ShowsDto>> findAllByMovieId(@RequestParam("movieId") @NotNull final Long movieId) {
        return ResponseEntity.ok(showsService.findAllByMovieId(movieId));
    }

    @GetMapping("/byScreenIdAndMovieId")
    public ResponseEntity<List<ShowsDto>> findAllByScreenIdAndMovieId(
            @RequestParam("screenId") @NotNull final Long screenId,
            @RequestParam("movieId") @NotNull final Long movieId) {
        return ResponseEntity.ok(showsService.findAllByScreenIdAndMovieId(screenId, movieId));
    }

    @PostMapping("/save")
    public ResponseEntity<ShowsDto> save(@RequestBody final ShowsDto showsDto) {
        return ResponseEntity.ok(showsService.save(showsDto));
    }

    @PostMapping("/create")
    public ResponseEntity<ShowsDto> create(@RequestBody final ShowsDto showsDto) {
        return ResponseEntity.ok(showsService.create(showsDto));
    }

}
