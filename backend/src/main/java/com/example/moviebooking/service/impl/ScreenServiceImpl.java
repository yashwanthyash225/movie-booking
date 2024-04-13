package com.example.moviebooking.service.impl;

import com.example.moviebooking.dto.ScreenDto;
import com.example.moviebooking.mapper.ScreenMapper;
import com.example.moviebooking.model.ScreenEntity;
import com.example.moviebooking.repo.ScreenRepo;
import com.example.moviebooking.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ScreenServiceImpl implements ScreenService {
    private final ScreenRepo screenRepo;
    private final ScreenMapper screenMapper;

    public ScreenServiceImpl(final ScreenRepo screenRepo,
                             final ScreenMapper screenMapper) {
        this.screenRepo = screenRepo;
        this.screenMapper = screenMapper;
    }

    @Override
    public List<ScreenDto> findAll() {
        final List<ScreenEntity> screenEntities = screenRepo.findAll();
        if (screenEntities.isEmpty()) {
            return null;
        }
        return screenMapper.entitiesToDtos(screenEntities);
    }

    @Override
    public ScreenDto findById(final Long id) {
        final ScreenEntity screenEntity = screenRepo.findById(id).orElse(null);
        if (Objects.isNull(screenEntity)) {
            return null;
        }
        return screenMapper.entityToDto(screenEntity);
    }

    @Override
    public List<ScreenDto> findAllByTheatreId(final Long theatreId) {
        final List<ScreenEntity> screenEntities = screenRepo.findAllByTheatreId(theatreId);
        if (screenEntities.isEmpty()) {
            return null;
        }
        return screenMapper.entitiesToDtos(screenEntities);    }

    @Override
    public ScreenDto findByTheatreIdAndName(final Long theatreId, final String name) {
        final ScreenEntity screenEntity = screenRepo.findByTheatreIdAndName(theatreId, name);
        if (Objects.isNull(screenEntity)) {
            return null;
        }
        return screenMapper.entityToDto(screenEntity);
    }

    @Override
    public ScreenDto save(final ScreenDto screenDto) {
        ScreenEntity screenEntity = screenMapper.dtoToEntity(screenDto);
        screenEntity = screenRepo.save(screenEntity);
        return screenMapper.entityToDto(screenEntity);
    }
}
