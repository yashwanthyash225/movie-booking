package com.example.moviebooking.mapper;

import com.example.moviebooking.dto.TicketDto;
import com.example.moviebooking.model.TicketEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketMapper {
    public TicketEntity dtoToEntity(final TicketDto ticketDto) {
        return TicketEntity.builder()
                .id(ticketDto.getId())
                .userId(ticketDto.getUserId())
                .seatId(ticketDto.getSeatId())
                .isCancelled(ticketDto.getIsCancelled())
                .build();
    }

    public TicketDto entityToDto(final TicketEntity ticketEntity) {
        return TicketDto.builder()
                .id(ticketEntity.getId())
                .userId(ticketEntity.getUserId())
                .seatId(ticketEntity.getSeatId())
                .isCancelled(ticketEntity.getIsCancelled())
                .build();
    }

    public List<TicketEntity> dtosToEntities(final List<TicketDto> ticketDtos) {
        return ticketDtos.stream().map(this::dtoToEntity).toList();
    }

    public List<TicketDto> entitiesToDtos(final List<TicketEntity> ticketEntities) {
        return ticketEntities.stream().map(this::entityToDto).toList();
    }
}
