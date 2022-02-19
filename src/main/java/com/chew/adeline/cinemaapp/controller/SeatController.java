package com.chew.adeline.cinemaapp.controller;

import com.chew.adeline.cinemaapp.exception.ResourceNotFoundException;
import com.chew.adeline.cinemaapp.model.Seat;
import com.chew.adeline.cinemaapp.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/seats")
public class SeatController {
    private final SeatRepository seatRepository;

    @Autowired
    public SeatController(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seat> getSeatById(@PathVariable("id") Long seatId) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new ResourceNotFoundException("Seat with id: " + seatId + " doesn't exist."));
        return ResponseEntity.ok(seat);
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<Map<String, Boolean>> getSeatStatus(@PathVariable("id")Long seatId) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new ResourceNotFoundException("Seat with id: " + seatId + " doesn't exist."));
        Map<String, Boolean> response = new HashMap<>();
        response.put("booked", seat.isBooked());
        return ResponseEntity.ok(response);
    }


}
