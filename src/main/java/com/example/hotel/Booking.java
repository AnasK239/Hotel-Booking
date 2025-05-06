package com.example.hotel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Booking {
    private Customer customer;
    private Hotel hotel;
    private Room room;
    private Date checkInDate;
    private Date checkOutDate;
    //list of all bookings
    private static List<Booking> bookings = new ArrayList<>();

    Booking(Customer customer, Hotel hotel, Room room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.hotel = hotel;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        bookings.add(this); // Add the booking to the static list on creation
    }

    public Customer getCustomer() {
        return customer;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Room getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }
    public static List<Booking> getAllBookings() {
        return bookings;
    }
}
