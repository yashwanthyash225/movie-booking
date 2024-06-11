package com.example.moviebooking.repo;

import com.example.moviebooking.model.TheatreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheatreRepo extends JpaRepository<TheatreEntity, Long> {
    List<TheatreEntity> findAllByLocationId(final Integer locationId);
    List<TheatreEntity> findAllByName(final String name);
}
