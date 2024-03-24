package com.example.moviebooking.service.impl;

import com.example.moviebooking.dto.ShowsDto;
import com.example.moviebooking.mapper.ShowsMapper;
import com.example.moviebooking.model.ShowsEntity;
import com.example.moviebooking.repo.ShowsRepo;
import com.example.moviebooking.service.ShowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ShowsServiceImpl implements ShowsService {
    private final ShowsMapper showsMapper;
    private final ShowsRepo showsRepo;

    @Autowired
    public ShowsServiceImpl(final ShowsMapper showsMapper,
                            final ShowsRepo showsRepo) {
        this.showsMapper = showsMapper;
        this.showsRepo = showsRepo;
    }

    @Override
    public List<ShowsDto> findAll() {
        final List<ShowsEntity> showsEntities = showsRepo.findAll();
        if (showsEntities.isEmpty()) {
            return null;
        }
        return showsMapper.entitiesToDtos(showsEntities);
    }

    @Override
    public ShowsDto findById(final Long id) {
        final ShowsEntity showsEntity = showsRepo.findById(id).orElse(null);
        if (Objects.isNull(showsEntity)) {
            return null;
        }
        return showsMapper.entityToDto(showsEntity);
    }

    @Override
    public List<ShowsDto> findAllByScreenId(final Long screenId) {
        final List<ShowsEntity> showsEntities = showsRepo.findAllByScreenId(screenId);
        if (showsEntities.isEmpty()) {
            return null;
        }
        return showsMapper.entitiesToDtos(showsEntities);
    }

    @Override
    public List<ShowsDto> findAllByMovieId(final Long movieId) {
        final List<ShowsEntity> showsEntities = showsRepo.findAllByMovieId(movieId);
        if (showsEntities.isEmpty()) {
            return null;
        }
        return showsMapper.entitiesToDtos(showsEntities);
    }

    @Override
    public List<ShowsDto> findAllByScreenIdAndMovieId(final Long screenId, final Long movieId) {
        final List<ShowsEntity> showsEntities = showsRepo.findAllByScreenIdAndMovieId(screenId, movieId);
        if (showsEntities.isEmpty()) {
            return null;
        }
        return showsMapper.entitiesToDtos(showsEntities);
    }

    @Override
    public ShowsDto save(final ShowsDto showsDto) {
        ShowsEntity showsEntity = showsMapper.dtoToEntity(showsDto);
        showsEntity = showsRepo.save(showsEntity);
        return showsMapper.entityToDto(showsEntity);
    }
}
