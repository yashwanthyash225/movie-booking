package com.example.moviebooking.mapper;

import com.example.moviebooking.dto.SeatDto;
import com.example.moviebooking.model.SeatEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SeatMapper {
    public SeatDto entityToDto(final SeatEntity seatEntity) {
        return SeatDto.builder()
                .id(seatEntity.getId())
                .showsId(seatEntity.getShowsId())
                .name(seatEntity.getName())
                .available(seatEntity.getAvailable())
                .build();
    }

    public SeatEntity dtoToEntity(final SeatDto seatDto) {
        return SeatEntity.builder()
                .id(seatDto.getId())
                .showsId(seatDto.getShowsId())
                .name(seatDto.getName())
                .available(seatDto.getAvailable())
                .build();
    }

    public List<SeatDto> entitiesToDtos(final List<SeatEntity> seatEntities) {
        return seatEntities.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public List<SeatEntity> dtosToEntites(final List<SeatDto> seatDtos) {
        return seatDtos.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }
}
