package com.example.moviebooking.mapper;

import com.example.moviebooking.dto.TheatreDto;
import com.example.moviebooking.model.TheatreEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TheatreMapper {
    public TheatreDto entityToDto(final TheatreEntity theatreEntity) {
        return TheatreDto.builder()
                .id(theatreEntity.getId())
                .name(theatreEntity.getName())
                .address(theatreEntity.getAddress())
                .locationId(theatreEntity.getLocationId())
                .build();
    }

    public TheatreEntity dtoToEntity(final TheatreDto theatreDto) {
        return TheatreEntity.builder()
                .id(theatreDto.getId())
                .name(theatreDto.getName())
                .address(theatreDto.getAddress())
                .locationId(theatreDto.getLocationId())
                .build();
    }

    public List<TheatreDto> entitiesToDtos(final List<TheatreEntity> theatreEntities) {
        return theatreEntities.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public List<TheatreEntity> dtosToEntities(final List<TheatreDto> theatreDtos) {
        return theatreDtos.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }
}
