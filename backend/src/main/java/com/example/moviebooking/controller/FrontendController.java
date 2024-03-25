package com.example.moviebooking.controller;

import com.example.moviebooking.dto.FrontendShowsDto;
import com.example.moviebooking.service.impl.FrontendServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/v1/frontend")
public class FrontendController {
    private final FrontendServiceImpl frontendService;

    public FrontendController(final FrontendServiceImpl frontendService) {
        this.frontendService = frontendService;
    }

    @GetMapping("/getShowsByMovieId")
    public ResponseEntity<List<FrontendShowsDto>> getShowsByMovieId(
            @RequestParam("movieId") @NotNull final Long movieId) {
        return ResponseEntity.ok(frontendService.getShowsByMovieId(movieId));
    }

    @GetMapping("/getShowsByTheatreId")
    public ResponseEntity<List<FrontendShowsDto>> getShowsByTheatreId(
            @RequestParam("theatreId") @NotNull final Long theatreId) {
        return ResponseEntity.ok(frontendService.getShowsByTheatreId(theatreId));
    }
}
