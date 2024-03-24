package com.example.moviebooking.service;

import com.example.moviebooking.dto.TheatreDto;

import java.util.List;

public interface TheatreService {
    List<TheatreDto> findAll();
    TheatreDto findById(final Long id);

    List<TheatreDto> findByLocationId(final Integer locationId);

    List<TheatreDto> findByName(final String name);

    TheatreDto save(final TheatreDto theatreDto);
}
