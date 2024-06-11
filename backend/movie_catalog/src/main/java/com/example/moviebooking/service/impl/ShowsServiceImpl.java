package com.example.moviebooking.service.impl;

import com.example.moviebooking.dto.ShowsDto;
import com.example.moviebooking.mapper.ShowsMapper;
import com.example.moviebooking.model.ScreenEntity;
import com.example.moviebooking.model.SeatEntity;
import com.example.moviebooking.model.ShowsEntity;
import com.example.moviebooking.repo.ScreenRepo;
import com.example.moviebooking.repo.SeatRepo;
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
    private final SeatRepo seatRepo;
    private final ScreenRepo screenRepo;

    public ShowsServiceImpl(final ShowsMapper showsMapper,
                            final ShowsRepo showsRepo,
                            final SeatRepo seatRepo,
                            final ScreenRepo screenRepo) {
        this.showsMapper = showsMapper;
        this.showsRepo = showsRepo;
        this.seatRepo = seatRepo;
        this.screenRepo = screenRepo;
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

    @Override
    public ShowsDto create(final ShowsDto showsDto) {
        ShowsEntity showsEntity = showsMapper.dtoToEntity(showsDto);
        final ScreenEntity screenEntity = screenRepo.findById(showsEntity.getScreenId()).orElse(null);
        if(Objects.isNull(screenEntity)) {
            return null;
        }
        showsEntity = showsRepo.save(showsEntity);
        for (int i = 1; i <= screenEntity.getCapacity(); i++) {
            seatRepo.save(SeatEntity.builder()
                    .showsId(showsEntity.getId())
                    .name("A" + i)
                    .available(Boolean.TRUE).build());
        }
        return showsMapper.entityToDto(showsEntity);
    }
}
