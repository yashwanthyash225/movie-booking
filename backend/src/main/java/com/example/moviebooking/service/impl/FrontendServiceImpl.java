package com.example.moviebooking.service.impl;

import com.example.moviebooking.dto.FrontendShowsDto;
import com.example.moviebooking.model.ScreenEntity;
import com.example.moviebooking.model.ShowsEntity;
import com.example.moviebooking.repo.ScreenRepo;
import com.example.moviebooking.repo.ShowsRepo;
import com.example.moviebooking.service.FrontendService;
import com.example.moviebooking.service.ScreenService;
import com.example.moviebooking.service.ShowsService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FrontendServiceImpl implements FrontendService {
    private final ShowsRepo showsRepo;
    private final ScreenRepo screenRepo;

    public FrontendServiceImpl(final ShowsRepo showsRepo, final ScreenRepo screenRepo) {
        this.showsRepo = showsRepo;
        this.screenRepo = screenRepo;
    }

    @Override
    public List<FrontendShowsDto> getShowsByMovieId(final Long movieId) {
        final List<ShowsEntity> showsEntities = showsRepo.findAllByMovieIdAndValid(movieId, Boolean.TRUE);
        if (showsEntities.isEmpty()) {
            return Collections.emptyList();
        }
        final List<ScreenEntity> screenEntities = screenRepo.findAllById(
                showsEntities.stream()
                        .map(ShowsEntity::getScreenId)
                        .collect(Collectors.toSet()));
        final Map<Long, Long> mapping = screenEntities.stream()
                .collect(Collectors.toMap(ScreenEntity::getId, ScreenEntity::getTheatreId));
        return showsEntities.stream().map(e ->
                FrontendShowsDto.builder()
                        .showsId(e.getId())
                        .theatreId(mapping.get(e.getScreenId()))
                        .movieId(movieId)
                        .timing(e.getTiming()).build()).toList();
    }

    @Override
    public List<FrontendShowsDto> getShowsByTheatreId(final Long theatreId) {
        final List<ScreenEntity> screenEntities = screenRepo.findAllByTheatreId(theatreId);
        if (screenEntities.isEmpty()) {
            return Collections.emptyList();
        }
        final List<ShowsEntity> showsEntities = showsRepo.findAllByScreenIdInAndValid(
                screenEntities.stream().map(ScreenEntity::getId).collect(Collectors.toSet()), Boolean.TRUE);
        return showsEntities.stream().map(e ->
                FrontendShowsDto.builder()
                        .showsId(e.getId())
                        .theatreId(theatreId)
                        .movieId(e.getMovieId())
                        .timing(e.getTiming()).build()).toList();
    }
}
