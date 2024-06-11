package com.example.moviebooking.mapper;

import com.example.moviebooking.dto.UserDto;
import com.example.moviebooking.model.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    public UserDto entityToDto(final UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .age(userEntity.getAge())
                .phone(userEntity.getPhone())
                .name(userEntity.getName())
                .build();
    }

    public UserEntity dtoToEntity(final UserDto userDto) {
        return UserEntity.builder()
                .id(userDto.getId())
                .age(userDto.getAge())
                .name(userDto.getName())
                .phone(userDto.getPhone())
                .build();
    }

    public List<UserDto> entitiesToDtos(final List<UserEntity> userEntities) {
        return userEntities.stream().map(this::entityToDto).toList();
    }

    public List<UserEntity> dtosToEntities(final List<UserDto> userDtos) {
        return userDtos.stream().map(this::dtoToEntity).toList();
    }
}
