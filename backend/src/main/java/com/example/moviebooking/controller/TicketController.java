package com.example.moviebooking.controller;

import com.example.moviebooking.dto.TicketDto;
import com.example.moviebooking.service.impl.TicketServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {
    private final TicketServiceImpl ticketService;

    public TicketController(final TicketServiceImpl ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/byId")
    public ResponseEntity<TicketDto> findById(@RequestParam("id") @NotNull final Long id) {
        return ResponseEntity.ok(ticketService.findById(id));
    }

    @GetMapping("/byUserId")
    public ResponseEntity<List<TicketDto>> findAllByUserId(@RequestParam("userId") @NotNull final Long userId) {
        return ResponseEntity.ok(ticketService.findAllByUserId(userId));
    }

    @GetMapping("/bySeatId")
    public ResponseEntity<List<TicketDto>> findBySeatId(@RequestParam("seatId") @NotNull final Long seatId) {
        return ResponseEntity.ok(ticketService.findBySeatId(seatId));
    }

    @PostMapping("/save")
    public ResponseEntity<TicketDto> save(@RequestBody final TicketDto ticketDto) {
        return ResponseEntity.ok(ticketService.save(ticketDto));
    }
}
