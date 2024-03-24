package com.example.moviebooking.service;

import com.example.moviebooking.dto.MovieDto;

public interface MovieService {
    MovieDto findById(final Long id);

    MovieDto findByTitle(final String title);

    MovieDto save(final MovieDto movieDto);
}
