package com.example.moviebooking.repo;

import com.example.moviebooking.model.SeatEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepo extends JpaRepository<SeatEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Optional<SeatEntity> findById(final Long id);
    List<SeatEntity> findAllByShowsId(final Long showsId);
    List<SeatEntity> findAllByShowsIdAndAvailable(final Long showsId, final Boolean available);
    SeatEntity findByShowsIdAndName(final Long showsId, final String name);
}
