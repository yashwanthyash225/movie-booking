package com.example.moviebooking.controller;

import com.example.moviebooking.dto.UserDto;
import com.example.moviebooking.service.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(final UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/byId")
    public ResponseEntity<UserDto> findById(@RequestParam("id") @NotNull final Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<UserDto> save(@RequestBody final UserDto userDto) {
        return ResponseEntity.ok(userService.save(userDto));
    }
}
