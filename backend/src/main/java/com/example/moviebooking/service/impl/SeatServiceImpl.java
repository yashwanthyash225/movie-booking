package com.example.moviebooking.service.impl;

import com.example.moviebooking.dto.SeatDto;
import com.example.moviebooking.mapper.SeatMapper;
import com.example.moviebooking.model.SeatEntity;
import com.example.moviebooking.repo.SeatRepo;
import com.example.moviebooking.service.SeatService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SeatServiceImpl implements SeatService {
    private final SeatMapper seatMapper;
    private final SeatRepo seatRepo;

    public SeatServiceImpl(final SeatMapper seatMapper,
                           final SeatRepo seatRepo) {
        this.seatMapper = seatMapper;
        this.seatRepo = seatRepo;
    }

    @Override
    public List<SeatDto> findAll() {
        final List<SeatEntity> seatEntities = seatRepo.findAll();
        if (seatEntities.isEmpty()) {
            return null;
        }
        return seatMapper.entitiesToDtos(seatEntities);
    }

    @Override
    @Transactional
    public SeatDto findById(final Long id) {
        final SeatEntity seatEntity = seatRepo.findById(id).orElse(null);
        if (Objects.isNull(seatEntity)) {
            return null;
        }
        return seatMapper.entityToDto(seatEntity);
    }

    @Override
    public List<SeatDto> findAllByShowsId(final Long showsId) {
        final List<SeatEntity> seatEntities = seatRepo.findAllByShowsId(showsId);
        if (seatEntities.isEmpty()) {
            return null;
        }
        return seatMapper.entitiesToDtos(seatEntities);
    }

    @Override
    public List<SeatDto> findAllByShowsIdAndAvailable(final Long showsId, final Boolean available) {
        final List<SeatEntity> seatEntities = seatRepo.findAllByShowsIdAndAvailable(showsId, available);
        if (seatEntities.isEmpty()) {
            return null;
        }
        return seatMapper.entitiesToDtos(seatEntities);
    }

    @Override
    public SeatDto findByShowsIdAndName(final Long showsId, final String name) {
        final SeatEntity seatEntity = seatRepo.findByShowsIdAndName(showsId, name);
        if (Objects.isNull(seatEntity)) {
            return null;
        }
        return seatMapper.entityToDto(seatEntity);
    }

    @Override
    @Transactional
    public SeatDto save(final SeatDto seatDto) {
        SeatEntity seatEntity = seatMapper.dtoToEntity(seatDto);
        seatEntity = seatRepo.save(seatEntity);
        return seatMapper.entityToDto(seatEntity);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public SeatDto blockSeat(final Long seatId, final Long userId) {
        final SeatEntity seatEntity = seatRepo.findById(seatId).orElse(null);
        if (Objects.isNull(seatEntity) || seatEntity.getBlockedBy() != null) {
            return null;
        }
        seatEntity.setBlockedBy(userId);
        seatRepo.save(seatEntity);
        return seatMapper.entityToDto(seatEntity);
    }

    @Override
    @Transactional
    public SeatDto unblockSeat(final Long seatId) {
        final SeatEntity seatEntity = seatRepo.findById(seatId).orElse(null);
        if (Objects.isNull(seatEntity)) {
            return null;
        }
        seatEntity.setBlockedBy(null);
        seatRepo.save(seatEntity);
        return seatMapper.entityToDto(seatEntity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public List<SeatDto> blockSeats(final List<Long> seatIds, final Long userId) {
        return seatIds.stream().map(e -> blockSeat(e, userId)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<SeatDto> unblockSeats(final List<Long> seatIds) {
        return seatIds.stream().map(this::unblockSeat).collect(Collectors.toList());
    }
}
