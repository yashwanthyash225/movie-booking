package com.example.moviebooking.repo;

import com.example.moviebooking.model.ScreenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScreenRepo extends JpaRepository<ScreenEntity, Long> {
    List<ScreenEntity> findAllByTheatreId(final Long theatreId);
    ScreenEntity findByTheatreIdAndName(final Long theatreId, final String name);
}
