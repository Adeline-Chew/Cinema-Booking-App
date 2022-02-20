package com.chew.adeline.cinemaapp.service;

import com.chew.adeline.cinemaapp.model.Seat;
import com.chew.adeline.cinemaapp.repository.SeatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SeatServiceTest {

    @Autowired
    private SeatService seatService;

    @MockBean
    private SeatRepository seatRepository;

    @BeforeEach
    void setUp() {
        Seat seat = new Seat('A', 1);
    }

    @Test
    public void whenValidSeatId_thenSeatShouldFound() {
        Long id = 1L;
        Seat found = seatService.getSeatById(id);
        assertEquals(id, found.getId());
    }
}