package com.chew.adeline.cinemaapp.service;

import com.chew.adeline.cinemaapp.model.Booking;
import com.chew.adeline.cinemaapp.model.Movie;
import com.chew.adeline.cinemaapp.model.Seat;
import com.chew.adeline.cinemaapp.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTest {

    @Autowired
    private BookingService bookingService;

    @MockBean
    private BookingRepository bookingRepository;

    @BeforeEach
    void setUp() {
        Movie movie = new Movie("Spider Man", LocalDateTime.of(2022, 1, 1, 13, 30),
                1, 9.80);
        Seat seat = new Seat('A', 1);
        Booking booking = new Booking("Adeline", "Cinema", "adeline@gmail.com", movie,
                List.of(seat));
    }
}