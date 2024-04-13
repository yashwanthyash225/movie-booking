package com.example.moviebooking.service;

import com.example.moviebooking.dto.MovieDto;

import java.util.List;

public interface MovieService {

    List<MovieDto> getAll();
    MovieDto findById(final Long id);

    MovieDto findByTitle(final String title);

    MovieDto save(final MovieDto movieDto);

    List<MovieDto> getImageSliderMovies();

    List<MovieDto> getRecommendedMovies();
}
