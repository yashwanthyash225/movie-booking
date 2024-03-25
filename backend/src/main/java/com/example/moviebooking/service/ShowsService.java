package com.example.moviebooking.service;

import com.example.moviebooking.dto.ShowsDto;

import java.util.List;

public interface ShowsService {
    List<ShowsDto> findAll();
    ShowsDto findById(final Long id);

    List<ShowsDto> findAllByScreenId(final Long screenId);

    List<ShowsDto> findAllByMovieId(final Long movieId);

    List<ShowsDto> findAllByScreenIdAndMovieId(final Long screenId, final Long movieId);

    ShowsDto save(final ShowsDto showsDto);

    ShowsDto create(final ShowsDto showsDto);
}
