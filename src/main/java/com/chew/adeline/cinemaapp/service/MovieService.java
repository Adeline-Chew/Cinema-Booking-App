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

/**
 * Movie service class
 * 
 * Represents business logic for movie
 * 
 * @author Adeline Chew Yao Yi
 */
@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /**
     * Get all movies from DB
     * 
     * @return List of Movie objects
     */
    public List<Movie> getAllMovies() {
        return new ArrayList<>(movieRepository.findAll());
    }

    /**
     * Get movie details by ID
     * 
     * @param id Movie ID
     * @return If found, return movie object
     */
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not exist with id: " + id));
    }

    /**
     * Add new movie, name and showtime must not be null
     * 
     * @param newMovie New movie
     * @return Newly created Movie object
     */
    public Movie addNewMovie(Movie newMovie) {
        if (newMovie.getName() == null || newMovie.getName().equals("")) {
            throw new BadRequestException("Movie name cannot be null.");
        } else if (newMovie.getShowtime() == null) {
            throw new BadRequestException("Movie showtime is not valid.");
        }
        return movieRepository.save(newMovie);
    }

    /**
     * Update movie details
     * 
     * @param id              Movie ID
     * @param newMovieDetails Movie object with updated details
     * @return Movie object with updated details
     */
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
