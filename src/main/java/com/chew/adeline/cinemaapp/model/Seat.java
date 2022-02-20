package com.chew.adeline.cinemaapp.model;

import javax.persistence.*;

/**
 * Seat class
 * 
 * Represent seats in cinema, tied with movie id
 * 
 * @author Adeline Chew Yao Yi
 */
@Entity
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "row_name")
    private char rowName;

    @Column(name = "col_number")
    private int colNumber;

    @Column(name = "booked")
    private boolean booked = false;

    public Seat() {
    }

    public Seat(char rowName, int col) {
        this.rowName = rowName;
        this.colNumber = col;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public char getRowName() {
        return rowName;
    }

    public void setRowName(char row) {
        this.rowName = row;
    }

    public int getColNumber() {
        return colNumber;
    }

    public void setColNumber(int col) {
        this.colNumber = col;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }
}
