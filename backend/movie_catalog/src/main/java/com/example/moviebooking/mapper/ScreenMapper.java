package com.example.moviebooking.mapper;

import com.example.moviebooking.dto.ScreenDto;
import com.example.moviebooking.model.ScreenEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScreenMapper {
    public ScreenDto entityToDto(final ScreenEntity screenEntity) {
        return ScreenDto.builder()
                .id(screenEntity.getId())
                .theatreId(screenEntity.getTheatreId())
                .capacity(screenEntity.getCapacity())
                .name(screenEntity.getName())
                .build();
    }

    public ScreenEntity dtoToEntity(final ScreenDto screenDto) {
        return ScreenEntity.builder()
                .id(screenDto.getId())
                .theatreId(screenDto.getTheatreId())
                .capacity(screenDto.getCapacity())
                .name(screenDto.getName())
                .build();
    }

    public List<ScreenDto> entitiesToDtos(final List<ScreenEntity> screenEntities) {
        return screenEntities.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public List<ScreenEntity> dtosToEntities(final List<ScreenDto> screenDtos) {
        return screenDtos.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }
}
