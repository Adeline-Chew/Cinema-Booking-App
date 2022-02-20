package com.chew.adeline.cinemaapp.service;

import com.chew.adeline.cinemaapp.model.Movie;
import com.chew.adeline.cinemaapp.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @MockBean
    private MovieRepository movieRepository;

    @BeforeEach
    void setUp() {
        Movie movie = new Movie("Spider Man", LocalDateTime.of(2022, 1, 1, 13, 30),
                1, 9.80);
    }

    @Test
    public void whenValidMovieDetails_thenUpdateMovie() {
        Movie movie = new Movie("Spider Man", LocalDateTime.of(2022, 1, 1, 13, 30),
                1, 9.80);
        Movie updateMovie = movieService.updateMovieById(
                movie.getId(), new Movie("Spider Man2", LocalDateTime.of(2022, 1, 1, 13, 30),
                        1, 9.80)
        );
        assertEquals("Spider Man2", updateMovie.getName());

    }
}