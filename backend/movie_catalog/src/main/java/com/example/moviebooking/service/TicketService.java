package com.example.moviebooking.service;

import com.example.moviebooking.dto.TicketDto;

import java.util.List;

public interface TicketService {
    TicketDto findById(final Long id);

    List<TicketDto> findAllByUserId(final Long userId);

    List<TicketDto> findBySeatId(final Long seatId);

    TicketDto save(final TicketDto ticketDto);
}
