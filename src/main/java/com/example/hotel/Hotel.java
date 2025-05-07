package com.example.hotel;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String name;
    private String location;
    private int rating;
    private String description;
    private String contactNumber;
    private String email;
    private String website;
    private List<Room> rooms;
    private static List<Hotel> hotels = new ArrayList<>();

    public Hotel(String name, String location, int rating, String description, String contactNumber, String email, String website) {
        this.name = name;
        this.location = location;
        this.rating = rating;
        this.description = description;
        this.contactNumber = contactNumber;
        this.email = email;
        this.website = website;
        this.rooms = new ArrayList<>();
        hotels.add(this);
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public static List<Hotel> getAllHotels() {
        return hotels;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    void addRoom(Room room) {
        rooms.add(room);
    }

    @Override
    public String toString() {
        return name; // or getHotelName() if the field is private
    }
}
