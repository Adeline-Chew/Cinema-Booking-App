package com.chew.adeline.cinemaapp.repository;

import com.chew.adeline.cinemaapp.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

}
