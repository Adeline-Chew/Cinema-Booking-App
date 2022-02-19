package com.chew.adeline.cinemaapp.repository;

import com.chew.adeline.cinemaapp.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
