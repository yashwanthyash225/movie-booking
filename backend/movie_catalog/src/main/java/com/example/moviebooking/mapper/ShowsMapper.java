package com.example.moviebooking.mapper;

import com.example.moviebooking.dto.ShowsDto;
import com.example.moviebooking.model.ShowsEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShowsMapper {
    public ShowsDto entityToDto(final ShowsEntity showsEntity) {
        return ShowsDto.builder()
                .id(showsEntity.getId())
                .screenId(showsEntity.getScreenId())
                .movieId(showsEntity.getMovieId())
                .valid(showsEntity.getValid())
                .timing(showsEntity.getTiming())
                .build();
    }

    public ShowsEntity dtoToEntity(final ShowsDto showsDto) {
        return ShowsEntity.builder()
                .id(showsDto.getId())
                .screenId(showsDto.getScreenId())
                .movieId(showsDto.getMovieId())
                .valid(showsDto.getValid())
                .timing(showsDto.getTiming())
                .build();
    }

    public List<ShowsDto> entitiesToDtos(final List<ShowsEntity> showsEntities) {
        return showsEntities.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public List<ShowsEntity> dtosToEntities(final List<ShowsDto> showsDtos) {
        return showsDtos.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }
}
