package com.chew.adeline.cinemaapp.config;

import com.chew.adeline.cinemaapp.model.Movie;
import com.chew.adeline.cinemaapp.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class MovieConfig {
    @Bean
    CommandLineRunner commandLineRunner(MovieRepository movieRepository) {
        return args -> {
            Movie movie1 = new Movie("Jungle Cruise",
                    LocalDateTime.of(2022, 2, 17, 14, 20),
                    1, 10.30);
            Movie movie2 = new Movie("Shang-Chi",
                    LocalDateTime.of(2022, 2, 16, 14, 30),
                    4, 8.90);
            movieRepository.saveAll(List.of(movie1, movie2));
        };
    }
}
