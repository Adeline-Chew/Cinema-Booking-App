package com.chew.adeline.cinemaapp.service;

import com.chew.adeline.cinemaapp.exception.BadRequestException;
import com.chew.adeline.cinemaapp.exception.ResourceNotFoundException;
import com.chew.adeline.cinemaapp.model.Booking;
import com.chew.adeline.cinemaapp.model.Movie;
import com.chew.adeline.cinemaapp.model.Seat;
import com.chew.adeline.cinemaapp.repository.BookingRepository;
import com.chew.adeline.cinemaapp.repository.MovieRepository;
import com.chew.adeline.cinemaapp.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Booking service class
 * 
 * Represents business logic for booking procedure
 * 
 * @author Adeline Chew Yao Yi
 */
@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, SeatRepository seatRepository,
            MovieRepository movieRepository) {
        this.bookingRepository = bookingRepository;
        this.seatRepository = seatRepository;
        this.movieRepository = movieRepository;
    }

    /**
     * Get all bookings from DB
     * 
     * @return List of booking objects
     */
    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookingRepository.findAll());
    }

    /**
     * Get Booking details by ID
     * 
     * @param id Booking ID
     * @return If found, return Booking object
     */
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not exist with id: " + id));
    }

    /**
     * Add new booking, check if the seat selected is taken,
     * issue new booking when all seats are available
     * 
     * @param firstName Customer first name
     * @param lastName  Customer last name
     * @param email     Customer email
     * @param movieId   Selected movie ID
     * @param seatIds   Selected seat(s)
     * @return Newly created Booking details
     */
    public Booking addNewBooking(String firstName, String lastName,
            String email, Long movieId,
            List<Long> seatIds) {
        List<Seat> selectedSeats = new ArrayList<>();
        for (Long seatId : seatIds) {
            Seat seat = seatRepository.findById(seatId)
                    .orElseThrow(() -> new ResourceNotFoundException("Seat not exist with id: " + seatId));
            // Make sure each seat is not booked
            if (seat.isBooked()) {
                throw new BadRequestException("Seat with id: " + seat.getId() + " is already taken.");
            }
            // Change the status of the seat
            seat.setBooked(true);
            seatRepository.save(seat);
            selectedSeats.add(seat);
        }
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not exist with id: " + movieId));
        ;
        Booking newBooking = new Booking(firstName, lastName, email, movie, selectedSeats);
        return bookingRepository.save(newBooking);
    }

}
