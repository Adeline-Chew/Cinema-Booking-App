package com.chew.adeline.cinemaapp.service;

import com.chew.adeline.cinemaapp.exception.ResourceNotFoundException;
import com.chew.adeline.cinemaapp.model.Movie;
import com.chew.adeline.cinemaapp.model.Seat;
import com.chew.adeline.cinemaapp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }


}
