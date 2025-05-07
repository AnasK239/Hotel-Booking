package com.example.hotel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Room{

    private int ID;
    private int capacity;
    private float price;
    private boolean booked;
    private Date availableDate;
    private final Hotel hotel;
    private String description;

    // Static list to hold all rooms
    private static List<Room> rooms = new ArrayList<>();
    
    protected Room(int capacity, float price, boolean booked, Date availableDate, Hotel hotel, String description) {
        this.ID = rooms.size();
        this.capacity = capacity;
        this.price = price;
        this.booked = booked;
        this.availableDate = availableDate;
        this.hotel = hotel;
        this.description = description;
        rooms.add(this);// Add the room to the static list on creation
        hotel.addRoom(this);
    }

    // Abstract Methods
    public abstract void displayRoomDetails();
    public abstract float calculateTotalPrice(int numberOfDays);

    // Setters and Getters
    int getID() {
        return ID;
    }
    void setID(int ID) {
        this.ID = ID;
    }

    int getCapacity() {
        return capacity;
    }
    void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    float getPrice() {
        return price;
    }
    void setPrice(float price) {
        this.price = price;
    }

    boolean isBooked() {
        return booked;
    }
    void setBooked(boolean booked) {
        this.booked = booked;
    }

    Date getAvailableDate() {
        return availableDate;
    }
    void setAvailableDate(Date availableDate) {
        this.availableDate = availableDate;
    }

    Hotel getHotel() {
        return hotel;
    }

    String getDescription() {
        return description;
    }
    void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Room room = (Room) obj;
        return(
        ID == room.ID && 
        capacity == room.capacity && 
        Float.compare(room.price, price) == 0 && 
        booked == room.booked &&
        hotel.equals(room.hotel) &&
        description.equals(room.description));
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public static List<Room> getAllRooms() {
        return rooms;
    }

}