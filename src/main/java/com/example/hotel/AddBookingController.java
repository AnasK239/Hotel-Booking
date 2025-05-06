package com.example.hotel;

public class AddBookingController implements UserAwareController{

    private Customer customer;

    @Override
    public void setUser(User user) {
        this.customer = (Customer) user;
    }
}
