package com.example.moviebooking.service.impl;

import com.example.moviebooking.dto.UserDto;
import com.example.moviebooking.mapper.UserMapper;
import com.example.moviebooking.model.UserEntity;
import com.example.moviebooking.repo.UserRepo;
import com.example.moviebooking.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;

    public UserServiceImpl(final UserRepo userRepo, final UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDto> findAll() {
        final List<UserEntity> userEntities = userRepo.findAll();
        if (userEntities.isEmpty()) {
            return null;
        }
        return userMapper.entitiesToDtos(userEntities);
    }

    @Override
    public UserDto findById(final Long id) {
        final UserEntity userEntity = userRepo.findById(id).orElse(null);
        if (Objects.isNull(userEntity)) {
            return null;
        }
        return userMapper.entityToDto(userEntity);
    }

    @Override
    public UserDto save(final UserDto userDto) {
        UserEntity userEntity = userMapper.dtoToEntity(userDto);
        userEntity = userRepo.save(userEntity);
        return userMapper.entityToDto(userEntity);
    }
}
