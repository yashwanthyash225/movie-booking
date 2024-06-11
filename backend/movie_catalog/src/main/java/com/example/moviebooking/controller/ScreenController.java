package com.example.moviebooking.controller;

import com.example.moviebooking.dto.ScreenDto;
import com.example.moviebooking.service.impl.ScreenServiceImpl;
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
@RequestMapping("/api/v1/screen")
@Validated
public class ScreenController {

    private final ScreenServiceImpl screenService;

    public ScreenController(final ScreenServiceImpl screenService) {
        this.screenService = screenService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ScreenDto>> findAll() {
        return ResponseEntity.ok(screenService.findAll());
    }

    @GetMapping("/byId")
    public ResponseEntity<ScreenDto> findById(
            @RequestParam("id") @NotNull final Long id) {
        return ResponseEntity.ok(screenService.findById(id));
    }

    @GetMapping("/byTheatreId")
    public ResponseEntity<List<ScreenDto>> findAllByTheatreId(
            @RequestParam("theatreId") @NotNull final Long theatreId) {
        return ResponseEntity.ok(screenService.findAllByTheatreId(theatreId));
    }

    @GetMapping("/byTheatreIdAndName")
    public ResponseEntity<ScreenDto> findByTheatreIdAndName(
            @RequestParam("theatreId") @NotNull final Long theatreId,
            @RequestParam("name") @NotBlank final String name) {
        return ResponseEntity.ok(screenService.findByTheatreIdAndName(theatreId, name));
    }

    @PostMapping("/save")
    public ResponseEntity<ScreenDto> save(@RequestBody final ScreenDto screenDto) {
        return ResponseEntity.ok(screenService.save(screenDto));
    }

}
