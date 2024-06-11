package com.example.moviebooking.service.impl;

import com.example.moviebooking.dto.MovieDto;
import com.example.moviebooking.mapper.MovieMapper;
import com.example.moviebooking.model.MovieEntity;
import com.example.moviebooking.repo.MovieRepo;
import com.example.moviebooking.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieMapper movieMapper;
    private final MovieRepo movieRepo;

    public MovieServiceImpl(final MovieMapper movieMapper,
                            final MovieRepo movieRepo) {
        this.movieMapper = movieMapper;
        this.movieRepo = movieRepo;
    }

    @Override
    public List<MovieDto> getAll() {
        final List<MovieEntity> movieEntities = movieRepo.findAll();
        if (movieEntities.isEmpty()) {
            return Collections.emptyList();
        }
        return movieMapper.entitiesToDtos(movieEntities);
    }

    @Override
    public MovieDto findById(final Long id) {
        final MovieEntity movieEntity = movieRepo.findById(id).orElse(null);
        if (Objects.isNull(movieEntity)) {
            return null;
        }
        return movieMapper.entityToDto(movieEntity);
    }

    @Override
    public MovieDto findByTitle(final String title) {
        final MovieEntity movieEntity = movieRepo.findByTitle(title);
        if (Objects.isNull(movieEntity)) {
            return null;
        }
        return movieMapper.entityToDto(movieEntity);
    }

    @Override
    public MovieDto save(final MovieDto movieDto) {
        MovieEntity movieEntity = movieMapper.dtoToEntity(movieDto);
        movieEntity = movieRepo.save(movieEntity);
        return movieMapper.entityToDto(movieEntity);
    }

    @Override
    public List<MovieDto> getImageSliderMovies() {
        final List<MovieEntity> movieEntities = movieRepo.findTop3ByOrderByIdDesc();
        if (movieEntities.isEmpty()) {
            return Collections.emptyList();
        }
        return movieMapper.entitiesToDtos(movieEntities);
    }

    @Override
    public List<MovieDto> getRecommendedMovies() {
        final List<MovieEntity> movieEntities = movieRepo.findTop10ByOrderByIdDesc();
        if (movieEntities.isEmpty()) {
            return Collections.emptyList();
        }
        return movieMapper.entitiesToDtos(movieEntities);
    }
}
