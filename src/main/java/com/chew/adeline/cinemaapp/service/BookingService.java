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

    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookingRepository.findAll());
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not exist with id: " + id));
    }

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
