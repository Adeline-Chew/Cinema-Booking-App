package com.chew.adeline.cinemaapp.service;

import com.chew.adeline.cinemaapp.exception.ResourceNotFoundException;
import com.chew.adeline.cinemaapp.model.Seat;
import com.chew.adeline.cinemaapp.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Seat service class
 * 
 * Represents business logic for seats in cinema
 * 
 * @author Adeline Chew Yao Yi
 */
@Service
public class SeatService {
    private final SeatRepository seatRepository;

    @Autowired
    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    /**
     * Get seat details by ID
     * 
     * @param id Seat ID
     * @return If found, return Seat object. Else, throw ResourceNotFoundException
     */
    public Seat getSeatById(Long id) {
        return seatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seat with id: " + id + " doesn't exist."));
    }

}
