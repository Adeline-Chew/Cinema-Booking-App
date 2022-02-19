package com.chew.adeline.cinemaapp.controller;

import com.chew.adeline.cinemaapp.exception.BadRequestException;
import com.chew.adeline.cinemaapp.exception.ResourceNotFoundException;
import com.chew.adeline.cinemaapp.model.Booking;
import com.chew.adeline.cinemaapp.model.Movie;
import com.chew.adeline.cinemaapp.model.Seat;
import com.chew.adeline.cinemaapp.repository.BookingRepository;
import com.chew.adeline.cinemaapp.repository.MovieRepository;
import com.chew.adeline.cinemaapp.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public BookingController(BookingRepository bookingRepository, SeatRepository seatRepository,
                             MovieRepository movieRepository) {
        this.bookingRepository = bookingRepository;
        this.seatRepository = seatRepository;
        this.movieRepository = movieRepository;
    }

//    Get all bookings
    @GetMapping
    public List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not exist with id: " + id));
        return ResponseEntity.ok(booking);
    }

    @PostMapping("/create")
    public ResponseEntity<Booking> addNewBooking
            (@RequestParam String firstName,@RequestParam String lastName,
             @RequestParam String email, @RequestParam Long movieId,
             @RequestParam List<Long> seatIds) {
        List<Seat> selectedSeats = new ArrayList<>();
        for (Long seatId: seatIds) {
            Seat seat = seatRepository.findById(seatId)
                    .orElseThrow(() -> new ResourceNotFoundException("Seat not exist with id: " + seatId));
            if (seat.isBooked()) {
                throw new BadRequestException("Seat with id: " + seat.getId() + " is already taken.");
            }
            seat.setBooked(true);
            seatRepository.save(seat);
            selectedSeats.add(seat);
        }
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not exist with id: " + movieId));;
        Booking newBooking = new Booking(firstName, lastName, email, movie, selectedSeats);
        bookingRepository.save(newBooking);
        return ResponseEntity.ok(newBooking);
    }

}
