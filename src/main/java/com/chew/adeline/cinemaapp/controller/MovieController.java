package com.chew.adeline.cinemaapp.controller;

import com.chew.adeline.cinemaapp.exception.BadRequestException;
import com.chew.adeline.cinemaapp.exception.ResourceNotFoundException;
import com.chew.adeline.cinemaapp.model.Movie;
import com.chew.adeline.cinemaapp.model.Seat;
import com.chew.adeline.cinemaapp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    //    Get all movies
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(){
        List<Movie> movies = new ArrayList<Movie>();
        movieRepository.findAll().forEach(movies::add);
        if (movies.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

//    Get movie details by ID
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not exist with id: " + id));
        return ResponseEntity.ok(movie);
    }

    //    Add new movies
    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        return movieRepository.save(movie);
    }

    // Initialise seats of the movie
    @PostMapping("/add_seats/{id}")
    public ResponseEntity<Movie> addNewSeats(@PathVariable Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not exist with id: " + id));
        if (movie.getSeats() != null) {
            throw new BadRequestException("Movie with id: " + id + " already has seats");
        }
        List<Seat> newSeats = new ArrayList<>();
        for (int row = 65; row < 70; row++) {
            for (int col = 1; col <= 10; col++) {
                Seat newSeat = new Seat((char) row, col);
                newSeats.add(newSeat);
            }
        }
        movie.setSeats(newSeats);
        movieRepository.save(movie);
        return ResponseEntity.ok(movie);
    }

    // Update movie details by ID
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movieDetails) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not exist with id: " + id));
        movie.setName(movieDetails.getName());
        movie.setHall(movieDetails.getHall());
        movie.setShowtime(movieDetails.getShowtime());
        movie.setPrice(movieDetails.getPrice());

        Movie updatedMovie = movieRepository.save(movie);
        return ResponseEntity.ok(updatedMovie);
    }
}

