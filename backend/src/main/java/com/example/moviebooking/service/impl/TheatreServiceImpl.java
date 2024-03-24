package com.example.moviebooking.service.impl;

import com.example.moviebooking.dto.TheatreDto;
import com.example.moviebooking.mapper.TheatreMapper;
import com.example.moviebooking.model.TheatreEntity;
import com.example.moviebooking.repo.TheatreRepo;
import com.example.moviebooking.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TheatreServiceImpl implements TheatreService {
    private final TheatreMapper theatreMapper;
    private final TheatreRepo theatreRepo;

    @Autowired
    public TheatreServiceImpl(final TheatreMapper theatreMapper,
                              final TheatreRepo theatreRepo) {
        this.theatreMapper = theatreMapper;
        this.theatreRepo = theatreRepo;
    }

    @Override
    public List<TheatreDto> findAll() {
        List<TheatreEntity> theatreEntities = theatreRepo.findAll();
        if (theatreEntities.isEmpty()) {
            return null;
        }
        return theatreMapper.entitiesToDtos(theatreEntities);
    }

    @Override
    public TheatreDto findById(final Long id) {
        final TheatreEntity theatreEntity = theatreRepo.findById(id).orElse(null);
        if (Objects.isNull(theatreEntity)) {
            return null;
        }
        return theatreMapper.entityToDto(theatreEntity);
    }

    @Override
    public List<TheatreDto> findByLocationId(final Integer locationId) {
        final List<TheatreEntity> theatreEntities = theatreRepo.findAllByLocationId(locationId);
        if (theatreEntities.isEmpty()) {
            return null;
        }
        return theatreMapper.entitiesToDtos(theatreEntities);
    }

    @Override
    public List<TheatreDto> findByName(final String name) {
        final List<TheatreEntity> theatreEntities = theatreRepo.findAllByName(name);
        if (theatreEntities.isEmpty()) {
            return null;
        }
        return theatreMapper.entitiesToDtos(theatreEntities);    }

    @Override
    public TheatreDto save(final TheatreDto theatreDto) {
        TheatreEntity theatreEntity = theatreMapper.dtoToEntity(theatreDto);
        theatreEntity = theatreRepo.save(theatreEntity);
        return theatreMapper.entityToDto(theatreEntity);
    }
}
