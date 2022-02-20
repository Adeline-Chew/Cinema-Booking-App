package com.chew.adeline.cinemaapp.controller;

import com.chew.adeline.cinemaapp.model.Booking;
import com.chew.adeline.cinemaapp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Booking Controller Class
 * 
 * @author Adeline Chew Yao Yi
 */

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Get all bookings
     * 
     * @return List of Bookings with HttpStatus
     */
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBooking() {
        List<Booking> bookings = bookingService.getAllBookings();
        if (bookings.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    /**
     * Get booking details by ID
     * 
     * @param id
     * @return Booking details with HttpStatus
     */
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        Booking booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(booking);
    }

    /**
     * Create a new booking
     * 
     * @param firstName Customer first name
     * @param lastName  Customer last name
     * @param email     Customer email
     * @param movieId   Selected movie ID
     * @param seatIds   Selected seat(s)
     * @return Newly created booking with HttpStatus
     */
    @PostMapping("/create")
    public ResponseEntity<Booking> addNewBooking(@RequestParam(name = "firstName") String firstName,
            @RequestParam(name = "lastName") String lastName,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "movieId") Long movieId,
            @RequestParam(name = "seatIds") List<Long> seatIds) {
        Booking newBooking = bookingService.addNewBooking(firstName, lastName, email, movieId, seatIds);
        return ResponseEntity.ok(newBooking);
    }

}
