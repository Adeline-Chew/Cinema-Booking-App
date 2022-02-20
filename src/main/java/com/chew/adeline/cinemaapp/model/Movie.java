package com.chew.adeline.cinemaapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "showtime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime showtime;

    @Column(name = "hall")
    private int hall;

    @Column(name = "price")
    private Double price;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    @Value("${some.key:0}")
    @OrderBy("id")
    private List<Seat> seats;

    public Movie () {
        this.initialiseSeats();
    }

    public Movie(String name, LocalDateTime showtime, int hall, Double price) {
        this.name = name;
        this.showtime = showtime;
        this.hall = hall;
        this.price = price;
        this.initialiseSeats();
    }

    public void initialiseSeats() {
        List<Seat> newSeats = new ArrayList<>();
        for (int row = 65; row < 70; row++) {
            for (int col = 1; col <= 10; col++) {
                Seat newSeat = new Seat((char) row, col);
                newSeats.add(newSeat);
            }
        }
        this.setSeats(newSeats);
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getShowtime() {
        return showtime;
    }

    public void setShowtime(LocalDateTime showtime) {
        this.showtime = showtime;
    }

    public int getHall() {
        return hall;
    }

    public void setHall(int hall) {
        this.hall = hall;
    }
}
