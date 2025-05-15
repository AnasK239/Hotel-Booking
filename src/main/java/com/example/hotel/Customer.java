package com.example.hotel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer extends User {

    private int loyaltyPoints;
    private List<Booking> bookings_prv;

    Customer(String name, String userName, String email, String password, String phoneNumber) {
        super(name, userName, email, password, phoneNumber);
        this.loyaltyPoints = 0;
        this.bookings_prv = new ArrayList<>();
    }
    void bookRoom(Hotel hotel, Room room, Date checkInDate, Date checkOutDate) {
        Booking booking = new Booking(this, hotel, room, checkInDate, checkOutDate);
        room.setBooked(true);
        bookings_prv.add(booking);
   }
    List<Booking> viewBookingsHistory() {
        return bookings_prv;
    }
    void addLoyaltyPoints(int points) {
        this.loyaltyPoints += points;
    }
    void removeLoyaltyPoints(int points) {
        if (points <= loyaltyPoints) {
            this.loyaltyPoints -= points;
        } else {
            this.loyaltyPoints = 0; 
        }
    }
    int getLoyaltyPoints() {
        return loyaltyPoints;
    }
}
