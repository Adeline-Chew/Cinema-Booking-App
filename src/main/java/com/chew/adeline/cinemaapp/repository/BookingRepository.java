package com.chew.adeline.cinemaapp.repository;

import com.chew.adeline.cinemaapp.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
