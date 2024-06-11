package com.example.moviebooking.mapper;

import com.example.moviebooking.dto.MovieDto;
import com.example.moviebooking.model.MovieEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieMapper {
    public MovieDto entityToDto(final MovieEntity movieEntity) {
        return MovieDto.builder()
                .id(movieEntity.getId())
                .title(movieEntity.getTitle())
                .description(movieEntity.getDescription())
                .releaseDate(movieEntity.getReleaseDate())
                .length(movieEntity.getLength())
                .genre(movieEntity.getGenre())
                .build();
    }

    public MovieEntity dtoToEntity(final MovieDto movieDto) {
        return MovieEntity.builder()
                .id(movieDto.getId())
                .title(movieDto.getTitle())
                .description(movieDto.getDescription())
                .releaseDate(movieDto.getReleaseDate())
                .length(movieDto.getLength())
                .genre(movieDto.getGenre())
                .build();
    }

    public List<MovieDto> entitiesToDtos(final List<MovieEntity> movieEntities) {
        return movieEntities.stream().map(this::entityToDto).toList();
    }

    public List<MovieEntity> dtosToEntities(final List<MovieDto> movieDtos) {
        return movieDtos.stream().map(this::dtoToEntity).toList();
    }
}
