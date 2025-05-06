package com.example.hotel;

import java.util.Date;

// Need to import User and Booking classes

public class Transaction {

// Attributes
    private long transactionId;
    private float amount;
    private String paymentMethod;
    private Date transactionDate;
    private String status;
    private Booking booking;
    private User user;

// Constructor
    public Transaction(long transactionId, float amount, String paymentMethod, 
                       Date transactionDate, String status, User user) {

        this.transactionId = transactionId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.transactionDate = transactionDate;
        this.status = status;
//        this.booking = booking;
        this.user = user;
    }

// Getters and Setters
    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

// Methods
    public boolean processPayment() {
        // Logic to process payment
        // lets assume the payment is processed successfully.
        
        this.status = "Completed";  // Set status to "Completed" after successful payment.
        return true;
    }

    public boolean issueRefund() {
        // Logic to process refund
        // Refund only if the status is "Completed".
        if (this.status.equals("Completed")) {
            this.status = "Refunded";  
            return true;
        } else {
            return false;
        }
    }

    public void generateReceipt() {
        // Generate a receipt message with the transaction details.
        System.out.println(
               "Receipt: \n" +
               "Transaction ID: " + this.transactionId + "\n" +
               "Amount: " + this.amount + "\n" +
               "Payment Method: " + this.paymentMethod + "\n" +
               "Transaction Date: " + this.transactionDate + "\n" +
               "Status: " + this.status + "\n" +
               "Booking Details: " +  "\n" +
               "User Details: " + this.user.toString() + "\n");

        // Note: toString() method should be implemented in Booking and User classes
        // Probably should return this as a string too but we will see
    }
}
