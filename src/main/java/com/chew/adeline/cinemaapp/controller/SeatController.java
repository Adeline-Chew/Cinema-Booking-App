package com.chew.adeline.cinemaapp.controller;

import com.chew.adeline.cinemaapp.model.Seat;
import com.chew.adeline.cinemaapp.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Seat Controller Class
 * 
 * @author Adeline Chew Yao Yi
 */
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/seats")
public class SeatController {
    private final SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    /**
     * Get Seat by ID
     * 
     * @param seatId Seat ID
     * @return If found, return Seat object
     */
    @GetMapping("/{id}")
    public ResponseEntity<Seat> getSeatById(@PathVariable("id") Long seatId) {
        Seat seat = seatService.getSeatById(seatId);
        return ResponseEntity.ok(seat);
    }

    /**
     * Get seat booking status
     * 
     * @param seatId Seat ID
     * @return Single field `booked` with boolean value
     */
    @GetMapping("/{id}/status")
    public ResponseEntity<Map<String, Boolean>> getSeatStatus(@PathVariable("id") Long seatId) {
        Seat seat = seatService.getSeatById(seatId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("booked", seat.isBooked());
        return ResponseEntity.ok(response);
    }

}
