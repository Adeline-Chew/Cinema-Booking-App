package com.chew.adeline.cinemaapp.controller;

import com.chew.adeline.cinemaapp.model.Movie;
import com.chew.adeline.cinemaapp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Movie Controller Class
 * 
 * @author Adeline Chew Yao Yi
 */
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * Get All movies
     * 
     * @return List of Movie objects
     */
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        if (movies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    /**
     * Get movie details by ID
     * 
     * @param id Movie ID
     * @return If found, return Movie object
     */
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);
        return ResponseEntity.ok(movie);
    }

    /**
     * Add a new Movie
     * 
     * @param movie JSON object that contains all required properties of Movie
     * @return Newly created Movie object
     */
    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        return movieService.addNewMovie(movie);
    }

    /**
     * Update movie details by ID
     * 
     * @param id           Movie ID
     * @param movieDetails JSON object that contains updated Movie properties
     * @return Newly updated movie
     */
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movieDetails) {
        Movie updatedMovie = movieService.updateMovieById(id, movieDetails);
        return ResponseEntity.ok(updatedMovie);
    }
}
