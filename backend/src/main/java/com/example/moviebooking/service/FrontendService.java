package com.example.moviebooking.service;

import com.example.moviebooking.dto.MovieTheatreResponseDto;
import com.example.moviebooking.dto.TheatreMoviesResponseDto;
import com.example.moviebooking.dto.TicketDto;

import java.util.List;

public interface FrontendService {
    List<MovieTheatreResponseDto> getShowsByMovieId(final Long movieId);

    List<TheatreMoviesResponseDto> getShowsByTheatreId(final Long theatreId);

    TicketDto bookTicket(final Long seatId, final Long userId);

    List<TicketDto> bookTickets(final List<Long> seatIds, final Long userId);

    void addSeatByShowId(final Long showId);

}
