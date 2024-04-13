package com.example.moviebooking.repo;

import com.example.moviebooking.model.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepo extends JpaRepository<TicketEntity, Long> {
    List<TicketEntity> findAllByUserId(final Long userId);
    List<TicketEntity> findBySeatId(final Long seatId);

    TicketEntity findBySeatIdAndIsCancelled(final Long seatId, final Boolean isCancelled);
}
