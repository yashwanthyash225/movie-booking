package com.example.moviebooking.service;

import com.example.moviebooking.dto.SeatDto;

import java.util.List;

public interface SeatService {
    List<SeatDto> findAll();

    SeatDto findById(final Long id);
    List<SeatDto> findAllByShowsId(final Long showsId);

    List<SeatDto> findAllByShowsIdAndAvailable(final Long showsId, final Boolean available);

    SeatDto findByShowsIdAndName(final Long showsId, final String name);

    SeatDto save(final SeatDto seatDto);

    SeatDto blockSeat(final Long seatId, final Long userId);

    SeatDto unblockSeat(final Long seatId);

    List<SeatDto> blockSeats(final List<Long> seatIds, final Long userId);

    List<SeatDto> unblockSeats(final List<Long> seatIds);
}
