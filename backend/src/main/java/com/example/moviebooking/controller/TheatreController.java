package com.example.moviebooking.controller;

import com.example.moviebooking.dto.TheatreDto;
import com.example.moviebooking.service.impl.TheatreServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("api/v1/theatre")
@Validated
public class TheatreController {

    private final TheatreServiceImpl theatreService;

    public TheatreController(final TheatreServiceImpl theatreService) {
        this.theatreService = theatreService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TheatreDto>> findAll() {
        return ResponseEntity.ok(theatreService.findAll());
    }

    @GetMapping("/byId")
    public ResponseEntity<TheatreDto> findTheatreById(
            @RequestParam("id") @NotNull final Long id) {
        return ResponseEntity.ok(theatreService.findById(id));
    }

    @GetMapping("/byName")
    public ResponseEntity<List<TheatreDto>> findTheatreByName(
            @RequestParam("name") @NotBlank final String name) {
        return ResponseEntity.ok(theatreService.findByName(name));
    }

    @GetMapping("/byLocationId")
    public ResponseEntity<List<TheatreDto>> findTheatreByLocationId(
            @RequestParam("locationId") @NotNull final Integer locationId) {
        return ResponseEntity.ok(theatreService.findByLocationId(locationId));
    }

    @PostMapping("/save")
    public ResponseEntity<TheatreDto> saveTheatre(@RequestBody final TheatreDto theatreDto) {
        return ResponseEntity.ok(theatreService.save(theatreDto));
    }



}


