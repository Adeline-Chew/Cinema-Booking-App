package com.chew.adeline.cinemaapp.controller;

import com.chew.adeline.cinemaapp.model.Movie;
import com.chew.adeline.cinemaapp.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    private Movie movie;

    @BeforeEach
    void setUp() {
        movie = new Movie("Spider Man: Homecoming", LocalDateTime.of(2022, 3, 1, 14, 30),
                1, 10.8);
    }

    @Test
    void getMovieById() {
    }

//    @Test
//    void createMovie() throws Exception {
//        Movie inputMovie = new Movie("Spider Man: Homecoming", LocalDateTime.of(2022, 3, 1, 14, 30),
//                1, 10.8);
//        Mockito.when(movieService.saveMovie(inputMovie)).thenReturn(movie);
//        mockMvc.perform(MockMvcRequestBuilders.post("")).contentType(MediaType.APPLICATION_JSON).content();
//
//    }
}