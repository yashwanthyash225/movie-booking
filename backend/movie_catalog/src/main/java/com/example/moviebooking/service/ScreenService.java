package com.example.moviebooking.service;

import com.example.moviebooking.dto.ScreenDto;

import java.util.List;

public interface ScreenService {
    List<ScreenDto> findAll();

    ScreenDto findById(final Long id);

    List<ScreenDto> findAllByTheatreId(final Long theatreId);

    ScreenDto findByTheatreIdAndName(final Long theatreId, final String name);

    ScreenDto save(final ScreenDto screenDto);
}
