package com.chew.adeline.cinemaapp.model;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @ManyToOne(fetch= FetchType.LAZY)
//    @JoinColumn(name="customer_id")
//    private Customer customer;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="movie_id")
    private Movie movie;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    @Value("${some.key:0}")
    @OrderBy("id")
    private List<Seat> seats = new ArrayList<>();

    @Column(name = "total")
    private Double total;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    public Booking() {
        this.createdAt = LocalDateTime.now();
    }

    public Booking(String firstName, String lastName, String email, Movie movie, List<Seat> seats) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.movie = movie;
        this.seats = seats;
        this.total = this.movie.getPrice() * this.seats.size();
        this.createdAt = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
