package com.example.hotel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer extends User {

    private int loyaltyPoints;
    private List<String> paymentMethods;
    private List<Booking> bookings_prv;
    private List<Transaction> payments;

    Customer(String name, String userName, String email, String password, String phoneNumber) {
        super(name, userName, email, password, phoneNumber);
        this.loyaltyPoints = 0;
        this.paymentMethods = new ArrayList<>();
        this.bookings_prv = new ArrayList<>();
        this.payments = new ArrayList<>();
    }
   Booking bookRoom(Hotel hotel, Room room, Date checkInDate, Date checkOutDate) {
       Booking booking = new Booking(this, hotel, room, checkInDate, checkOutDate);
       bookings_prv.add(booking);
       return booking;
   }
    boolean cancelBooking(Booking booking) {
        if (bookings_prv.contains(booking)) {
            bookings_prv.remove(booking);
            return true;
        }
        return false;
    }
    List<Booking> viewBookingsHistory() {
        return bookings_prv;
    }
    void addPaymentMethod(String paymentMethod) {
        paymentMethods.add(paymentMethod);
    }
    void removePaymentMethod(String paymentMethod) {
        paymentMethods.remove(paymentMethod);
    }
    List<String> viewPaymentMethods() {
        return paymentMethods;
    }
    void addLoyaltyPoints(int points) {
        this.loyaltyPoints += points;
    }
    void redeemLoyaltyPoints(int points) {
        if (points <= loyaltyPoints) {
            this.loyaltyPoints -= points;
        } else {
            System.out.println("Not enough loyalty points to redeem.");
        }
    }
    int getLoyaltyPoints() {
        return loyaltyPoints;
    }
    List<Transaction> viewPaymentsHistory() {
        return payments;
    }
    void addPayment(Transaction payment) {
        payments.add(payment);
    }
    void removePayment(Transaction payment) {
        payments.remove(payment);
    }

    @Override
    public void printUserDetails(){
        System.out.println("Name: " + this.getName());
        System.out.println("User Name: " + this.getUserName());
        System.out.println("Email: " + this.getEmail());
        System.out.println("Password: " + this.getPassword());
        System.out.println("Phone Number: " + this.getPhoneNumber());
        System.out.println("Loyalty Points: " + this.loyaltyPoints);
        System.out.println("Payment Methods: " + this.paymentMethods);
        System.out.println("Bookings History: " + this.bookings_prv);
        System.out.println("Payments History: " + this.payments);
    }

    @Override
    public void updateProfile(String name, String userName, String email, String password, String phoneNumber) {
        this.updateName(name);
        this.updateUserName(userName);
        this.updateEmail(email);
        this.updatePassword(password);
        this.updatePhoneNumber(phoneNumber);
    }


}
