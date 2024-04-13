package com.example.moviebooking.service.impl;

import com.example.moviebooking.dto.TicketDto;
import com.example.moviebooking.mapper.TicketMapper;
import com.example.moviebooking.model.TicketEntity;
import com.example.moviebooking.repo.TicketRepo;
import com.example.moviebooking.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepo ticketRepo;
    private final TicketMapper ticketMapper;

    public TicketServiceImpl(final TicketRepo ticketRepo, final TicketMapper ticketMapper) {
        this.ticketRepo = ticketRepo;
        this.ticketMapper = ticketMapper;
    }

    @Override
    public TicketDto findById(final Long id) {
        final TicketEntity ticketEntity = ticketRepo.findById(id).orElse(null);
        if (Objects.isNull(ticketEntity)) {
            return null;
        }
        return ticketMapper.entityToDto(ticketEntity);
    }

    @Override
    public List<TicketDto> findAllByUserId(final Long userId) {
        final List<TicketEntity> ticketEntities = ticketRepo.findAllByUserId(userId);
        if (ticketEntities.isEmpty()) {
            return null;
        }
        return ticketMapper.entitiesToDtos(ticketEntities);
    }

    @Override
    public List<TicketDto> findBySeatId(final Long seatId) {
        final List<TicketEntity> ticketEntities = ticketRepo.findBySeatId(seatId);
        if (ticketEntities.isEmpty()) {
            return null;
        }
        return ticketMapper.entitiesToDtos(ticketEntities);
    }

    @Override
    public TicketDto save(final TicketDto ticketDto) {
        TicketEntity ticketEntity = ticketMapper.dtoToEntity(ticketDto);
        ticketEntity = ticketRepo.save(ticketEntity);
        return ticketMapper.entityToDto(ticketEntity);
    }
}
