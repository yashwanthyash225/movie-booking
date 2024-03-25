package com.example.moviebooking.service;

import com.example.moviebooking.dto.FrontendShowsDto;

import java.util.List;

public interface FrontendService {
    List<FrontendShowsDto> getShowsByMovieId(final Long movieId);

    List<FrontendShowsDto> getShowsByTheatreId(final Long theatreId);
}
