package com.example.moviebooking.repo;

import com.example.moviebooking.model.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepo extends JpaRepository<SeatEntity, Long> {
    List<SeatEntity> findAllByShowsId(final Long showsId);
    List<SeatEntity> findAllByShowsIdAndAvailable(final Long showsId, final Boolean available);
    SeatEntity findByShowsIdAndName(final Long showsId, final String name);
}
