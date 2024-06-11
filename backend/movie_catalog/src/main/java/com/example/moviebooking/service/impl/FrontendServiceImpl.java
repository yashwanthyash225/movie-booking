package com.example.moviebooking.service.impl;

import com.example.moviebooking.dto.FrontendShowsDto;
import com.example.moviebooking.dto.MovieDetailsDto;
import com.example.moviebooking.dto.MovieDto;
import com.example.moviebooking.dto.MovieTheatreResponseDto;
import com.example.moviebooking.dto.ShowTimingsDto;
import com.example.moviebooking.dto.TheatreDetailsDto;
import com.example.moviebooking.dto.TheatreDto;
import com.example.moviebooking.dto.TheatreMoviesResponseDto;
import com.example.moviebooking.dto.TicketDto;
import com.example.moviebooking.mapper.MovieMapper;
import com.example.moviebooking.mapper.TheatreMapper;
import com.example.moviebooking.mapper.TicketMapper;
import com.example.moviebooking.model.MovieEntity;
import com.example.moviebooking.model.ScreenEntity;
import com.example.moviebooking.model.SeatEntity;
import com.example.moviebooking.model.ShowsEntity;
import com.example.moviebooking.model.TheatreEntity;
import com.example.moviebooking.model.TicketEntity;
import com.example.moviebooking.repo.MovieRepo;
import com.example.moviebooking.repo.ScreenRepo;
import com.example.moviebooking.repo.SeatRepo;
import com.example.moviebooking.repo.ShowsRepo;
import com.example.moviebooking.repo.TheatreRepo;
import com.example.moviebooking.repo.TicketRepo;
import com.example.moviebooking.service.FrontendService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.mapping;

@Service
public class FrontendServiceImpl implements FrontendService {
    private final ShowsRepo showsRepo;
    private final ScreenRepo screenRepo;
    private final SeatRepo seatRepo;
    private final TicketRepo ticketRepo;
    private final TicketMapper ticketMapper;
    private final TheatreRepo theatreRepo;
    private final MovieRepo movieRepo;
    private final MovieMapper movieMapper;
    private final TheatreMapper theatreMapper;

    public FrontendServiceImpl(final ShowsRepo showsRepo,
                               final ScreenRepo screenRepo,
                               final SeatRepo seatRepo,
                               final TicketRepo ticketRepo,
                               final TicketMapper ticketMapper,
                               final TheatreRepo theatreRepo,
                               final MovieRepo movieRepo,
                               final MovieMapper movieMapper,
                               final TheatreMapper theatreMapper) {
        this.showsRepo = showsRepo;
        this.screenRepo = screenRepo;
        this.seatRepo = seatRepo;
        this.ticketRepo = ticketRepo;
        this.ticketMapper = ticketMapper;
        this.theatreRepo = theatreRepo;
        this.movieRepo = movieRepo;
        this.movieMapper = movieMapper;
        this.theatreMapper = theatreMapper;
    }

    @Override
    public List<MovieTheatreResponseDto> getShowsByMovieId(final Long movieId) {
        final List<ShowsEntity> showsEntities = showsRepo
                .findAllByMovieIdAndValid(movieId, Boolean.TRUE);
        if (showsEntities.isEmpty()) {
            return Collections.emptyList();
        }
        final HashMap<LocalDate, List<ShowTimingsDto>> showMap = getShowTimingsMap(showsEntities);
        final List<ScreenEntity> screenEntities = screenRepo.findAllById(
                showsEntities.stream()
                        .map(ShowsEntity::getScreenId)
                        .collect(Collectors.toSet()));
        final List<TheatreEntity> theatreEntities = theatreRepo.findAllById(
                screenEntities.stream()
                        .map(ScreenEntity::getTheatreId)
                        .collect(Collectors.toSet()));
        final Map<Long, Long> theatreMap = screenEntities.stream()
                .collect(Collectors.toMap(ScreenEntity::getId, ScreenEntity::getTheatreId));
        final Map<Long, Long> screenMap = showsEntities.stream()
                .collect(Collectors.toMap(ShowsEntity::getId, ShowsEntity::getScreenId));
        final Map<Long, TheatreDto> thMap = theatreEntities.stream()
                .collect(Collectors.toMap(TheatreEntity::getId, theatreMapper::entityToDto));
        final List<MovieTheatreResponseDto> movieTheatreResponseDtos = new ArrayList<>();
        for (final Map.Entry<LocalDate, List<ShowTimingsDto>> entry : showMap.entrySet()) {
            final HashMap<Long, List<ShowTimingsDto>> theatreShowMap = entry.getValue().stream().sorted(Comparator.comparing(ShowTimingsDto::getTiming))
                    .collect(Collectors.groupingBy(
                            e -> theatreMap.get(screenMap.get(e.getShowId())),
                            HashMap::new,
                            mapping(Function.identity(), Collectors.toList())));
            final List<TheatreDetailsDto> theatreDetailsDtos = new ArrayList<>();
            for (final Map.Entry<Long, List<ShowTimingsDto>> showEntry : theatreShowMap.entrySet()) {
                theatreDetailsDtos.add(TheatreDetailsDto.builder()
                        .theatreDto(thMap.get(showEntry.getKey()))
                        .showTimingsDtos(showEntry.getValue())
                        .build());
            }
            final LocalDate localDate = entry.getKey();
            movieTheatreResponseDtos.add(MovieTheatreResponseDto.builder()
                    .localDate(localDate)
                    .date(localDate.getDayOfMonth())
                    .month(localDate.getMonth().name().substring(0, 3))
                    .week(localDate.getDayOfWeek().name().substring(0, 3))
                    .theatreDetails(theatreDetailsDtos).build());
        }
        System.out.println("Completed -------------------------------");
        movieTheatreResponseDtos.sort(Comparator.comparing(MovieTheatreResponseDto::getLocalDate));
        return movieTheatreResponseDtos;
    }

    @Override
    public List<TheatreMoviesResponseDto> getShowsByTheatreId(final Long theatreId) {
        final List<ScreenEntity> screenEntities = screenRepo.findAllByTheatreId(theatreId);
        if (screenEntities.isEmpty()) {
            return Collections.emptyList();
        }
        final List<ShowsEntity> showsEntities = showsRepo.findAllByScreenIdInAndValid(
                screenEntities.stream().map(ScreenEntity::getId).collect(Collectors.toSet()), Boolean.TRUE);
        final HashMap<LocalDate, List<ShowTimingsDto>> showListMap = getShowTimingsMap(showsEntities);
        final List<MovieEntity> movieEntities = movieRepo.findAllById(showsEntities.stream()
                .map(ShowsEntity::getMovieId).collect(Collectors.toSet()));
        final Map<Long, MovieDto> movieEntityHashMap = movieEntities.stream()
                .collect(Collectors.toMap(MovieEntity::getId, movieMapper::entityToDto));
        final Map<Long, Long> movieShowIdMap = showsEntities.stream()
                .collect(Collectors.toMap(ShowsEntity::getId, ShowsEntity::getMovieId));
        final List<TheatreMoviesResponseDto> theatreMoviesResponseDtos = new ArrayList<>();
        for (Map.Entry<LocalDate, List<ShowTimingsDto>> entry : showListMap.entrySet()) {
            final Map<Long, List<ShowTimingsDto>> movieShowMap = entry.getValue().stream().sorted(Comparator.comparing(ShowTimingsDto::getTiming))
                    .collect(Collectors.groupingBy(
                            e -> movieShowIdMap.get(e.getShowId()),
                            mapping(Function.identity(), Collectors.toList())));
            final LocalDate localDate = entry.getKey();
            theatreMoviesResponseDtos.add(TheatreMoviesResponseDto.builder()
                    .localDate(localDate)
                    .date(localDate.getDayOfMonth())
                    .month(localDate.getMonth().name().substring(0, 3))
                    .week(localDate.getDayOfWeek().name().substring(0, 3))
                    .movieDetails(movieShowMap.entrySet().stream()
                            .map(entry1 ->
                                    MovieDetailsDto.builder()
                                            .movieDto(movieEntityHashMap.get(entry1.getKey()))
                                            .showTimingsDtos(entry1.getValue())
                                            .build()).toList())
                    .build());
        }
        theatreMoviesResponseDtos.sort(Comparator.comparing(TheatreMoviesResponseDto::getLocalDate));
        return theatreMoviesResponseDtos;
    }

    private HashMap<LocalDate, List<ShowTimingsDto>> getShowTimingsMap(final List<ShowsEntity> showsEntities) {
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm");
        return showsEntities.stream()
                .collect(Collectors.groupingBy(e -> e.getTiming().toLocalDate(),
                        HashMap::new,
                        mapping(sh -> ShowTimingsDto.builder()
                                .timing(sh.getTiming().format(dateTimeFormatter))
                                .showId(sh.getId()).build(), Collectors.toList())));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public TicketDto bookTicket(final Long seatId, final Long userId) {
        final List<TicketEntity> ticketEntities = ticketRepo.findBySeatId(seatId);
        final Optional<TicketEntity> optionalTicketEntity =  ticketEntities.stream()
                .filter(ticket -> Boolean.TRUE.equals(ticket.getIsCancelled())).findAny();
        if (optionalTicketEntity.isPresent()) {
            return null;
        }
        final SeatEntity seatEntity = seatRepo.findById(seatId).orElse(null);
        if (Objects.isNull(seatEntity) || !userId.equals(seatEntity.getBlockedBy())) {
            return null;
        }
        final TicketEntity ticketEntity = TicketEntity.builder()
                .userId(userId)
                .seatId(seatId)
                .build();
        ticketRepo.save(ticketEntity);
        return ticketMapper.entityToDto(ticketEntity);
    }

    @Override
    public List<TicketDto> bookTickets(final List<Long> seatIds, final Long userId) {
        return seatIds.stream()
                .map(s -> bookTicket(s, userId))
                .collect(Collectors.toList());
    }

    @Override
    public void addSeatByShowId(final Long showId) {
        final ShowsEntity showsEntity = showsRepo.findById(showId).orElse(null);
        if (Objects.isNull(showsEntity)) {
            return;
        }
        final ScreenEntity screenEntity = screenRepo.findById(showsEntity.getScreenId()).orElse(null);
        if (Objects.isNull(screenEntity)) {
            return;
        }
        for (int i = 1; i <= screenEntity.getCapacity(); i++) {
            seatRepo.save(SeatEntity.builder()
                    .showsId(showId)
                    .name("Seat-" + i)
                    .available(Boolean.TRUE)
                    .build());
        }
    }
}
