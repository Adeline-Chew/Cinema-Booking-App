package com.chew.adeline.cinemaapp.service;

import com.chew.adeline.cinemaapp.exception.BadRequestException;
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

    public List<Movie> getAllMovies() {
        return new ArrayList<>(movieRepository.findAll());
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not exist with id: " + id));
    }

    public Movie addNewMovie(Movie newMovie) {
        if (newMovie.getName() == null || newMovie.getName().equals("")) {
            throw new BadRequestException("Movie name cannot be null.");
        }
        else if (newMovie.getShowtime() == null) {
            throw new BadRequestException("Movie showtime is not valid.");
        }
        return movieRepository.save(newMovie);
    }

    public Movie updateMovieById(Long id, Movie newMovieDetails) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not exist with id: " + id));
        movie.setName(newMovieDetails.getName());
        movie.setHall(newMovieDetails.getHall());
        movie.setShowtime(newMovieDetails.getShowtime());
        movie.setPrice(newMovieDetails.getPrice());

        return movieRepository.save(movie);
    }




}
