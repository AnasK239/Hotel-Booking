package com.example.hotel;

import java.util.List;

public class Admin extends User {
    private String role;
    private String department;

    Admin (String name, String userName, String email, String password, String phoneNumber, String role, String department) {
        super(name, userName, email, password, phoneNumber);
        this.role = role;
        this.department = department;
    }

    void updateRole(String role) {
        this.role = role;
    }
    void updateDepartment(String department) {
        this.department = department;
    }
    String getRole() {
        return role;
    }
    String getDepartment() {
        return department;
    }

    boolean manageUser(Customer customer , String action) {
        if (action.equals("remove") || action.equals("delete")) {
            User.removeUser(customer);
            return true;
        } else if (action.equals("update")) {
            customer.updateProfile(customer.getName(), customer.getUserName(), customer.getEmail(), customer.getPassword(), customer.getPhoneNumber());
            return true;
        } else {
            System.out.println("Invalid action for user management.");
            return false;
        }
    }

    List<Booking> viewAllBookings() {
        // code to view all bookings
        return Booking.getAllBookings();
    }
    List<User> viewAllUsers() {
        return User.getAllUsers();
    }
   List<Room> viewAllRooms() {
       return Room.getAllRooms();
   }
    List<Hotel> viewAllHotels() {
        // code to view all hotels
        return null;
    }
    List<Transaction> viewAllTransactions() {
        // code to view all transactions
        return null;
    }

    @Override
    public void printUserDetails(){
        System.out.println("Name: " + this.getName());
        System.out.println("User Name: " + this.getUserName());
        System.out.println("Email: " + this.getEmail());
        System.out.println("Password: " + this.getPassword());
        System.out.println("Phone Number: " + this.getPhoneNumber());
        System.out.println("Role: " + this.role);
        System.out.println("Department: " + this.department);
    }

    @Override
    public void updateProfile(String name, String userName, String email, String password, String phoneNumber) {
        this.updateEmail(email);
        this.updatePassword(password);
        this.updatePhoneNumber(phoneNumber);
    }

}
