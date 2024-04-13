package com.example.moviebooking.service;

import com.example.moviebooking.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();
    UserDto findById(final Long id);

    UserDto save(final UserDto userDto);
}
