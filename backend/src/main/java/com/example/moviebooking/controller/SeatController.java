package com.example.moviebooking.controller;

import com.example.moviebooking.dto.BookTicketsRequestDto;
import com.example.moviebooking.dto.SeatDto;
import com.example.moviebooking.service.impl.SeatServiceImpl;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/v1/seat")
public class SeatController {
    private final SeatServiceImpl seatService;

    public SeatController(final SeatServiceImpl seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/byId")
    public ResponseEntity<SeatDto> findById(@RequestParam("id") @NotNull final Long id) {
        return ResponseEntity.ok(seatService.findById(id));
    }

    @GetMapping("/byShowsId")
    public ResponseEntity<List<SeatDto>> findAllByShowsId(@RequestParam("showsId") @NotNull final Long showsId) {
        return ResponseEntity.ok(seatService.findAllByShowsId(showsId));
    }

    @GetMapping("/byShowsIdAndAvailable")
    public ResponseEntity<List<SeatDto>> findAllByShowsIdAndAvailable(
            @RequestParam("showsId") @NotNull final Long showsId,
            @RequestParam("available") @NotNull final Boolean available) {
        return ResponseEntity.ok(seatService.findAllByShowsIdAndAvailable(showsId, available));
    }

    @GetMapping("/byShowsIdAndName")
    public ResponseEntity<SeatDto> findByShowsIdAndName(
            @RequestParam("showsId") @NotNull final Long showsId,
            @RequestParam("name") @NotBlank final String name) {
        return ResponseEntity.ok(seatService.findByShowsIdAndName(showsId, name));
    }

    @PostMapping("/save")
    public ResponseEntity<SeatDto> save(@RequestBody final SeatDto seatDto) {
        return ResponseEntity.ok(seatService.save(seatDto));
    }

    @PostMapping("/blockSeat")
    public ResponseEntity<SeatDto> blockSeat(@RequestParam("seatId") @NotNull final Long seatId,
                                             @RequestParam("userId") @NotNull final Long userId) {
        return ResponseEntity.ok(seatService.blockSeat(seatId, userId));
    }

    @PostMapping("/blockSeats")
    public ResponseEntity<List<SeatDto>> blockSeats(@RequestBody final BookTicketsRequestDto bookTicketsRequestDto) {
        return ResponseEntity.ok(seatService.blockSeats(bookTicketsRequestDto.getSeatIds(),
                bookTicketsRequestDto.getUserId()));
    }

    @PostMapping("/unblockSeat")
    public ResponseEntity<SeatDto> unblockSeat(@RequestParam("seatId") @NotNull final Long seatId) {
        return ResponseEntity.ok(seatService.unblockSeat(seatId));
    }

    @PostMapping("/unblockSeats")
    public ResponseEntity<List<SeatDto>> unblockSeats(@RequestBody final BookTicketsRequestDto bookTicketsRequestDto) {
        return ResponseEntity.ok(seatService.unblockSeats(bookTicketsRequestDto.getSeatIds()));
    }

 }
