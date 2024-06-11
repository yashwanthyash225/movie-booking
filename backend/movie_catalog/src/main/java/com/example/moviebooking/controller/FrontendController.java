package com.example.moviebooking.controller;

import com.example.moviebooking.dto.BookTicketsRequestDto;
import com.example.moviebooking.dto.FrontendShowsDto;
import com.example.moviebooking.dto.MovieTheatreResponseDto;
import com.example.moviebooking.dto.TheatreMoviesResponseDto;
import com.example.moviebooking.dto.TicketDto;
import com.example.moviebooking.service.impl.FrontendServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/v1/frontend")
@CrossOrigin(origins = {"*"})
public class FrontendController {
    private final FrontendServiceImpl frontendService;

    public FrontendController(final FrontendServiceImpl frontendService) {
        this.frontendService = frontendService;
    }

    @GetMapping("/getShowsByMovieId")
    public ResponseEntity<List<MovieTheatreResponseDto>> getShowsByMovieId(
            @RequestParam("movieId") @NotNull final Long movieId) {
        return ResponseEntity.ok(frontendService.getShowsByMovieId(movieId));
    }

    @GetMapping("/getShowsByTheatreId")
    public ResponseEntity<List<TheatreMoviesResponseDto>> getShowsByTheatreId(
            @RequestParam("theatreId") @NotNull final Long theatreId) {
        return ResponseEntity.ok(frontendService.getShowsByTheatreId(theatreId));
    }

    @PostMapping("/bookTicket")
    public ResponseEntity<TicketDto> bookTicket(
            @RequestParam("seatId") @NotNull final Long seatId,
            @RequestParam("userId") @NotNull final Long userId) {
        return ResponseEntity.ok(frontendService.bookTicket(seatId, userId));
    }

    @PostMapping("/bookTickets")
    public ResponseEntity<List<TicketDto>> bookTIckets(@RequestBody final BookTicketsRequestDto bookTicketsRequestDto) {
        return ResponseEntity.ok(frontendService.bookTickets(bookTicketsRequestDto.getSeatIds(),
                bookTicketsRequestDto.getUserId()));
    }

    @PostMapping("/addSeatsByShowId")
    public void saveByShowId(@RequestParam("showId") @NotNull final Long showId) {
        frontendService.addSeatByShowId(showId);
    }
}
