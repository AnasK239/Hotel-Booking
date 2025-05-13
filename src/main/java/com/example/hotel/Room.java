package com.example.hotel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public abstract class Room implements  Sortable<Room> {

    private final int ID;
    private final int capacity;
    private float price;
    private boolean booked;
    private final Hotel hotel;
    private String description;

    private static List<Room> rooms = new ArrayList<>();
    
    protected Room(int capacity, float price, boolean booked, Hotel hotel, String description) {
        this.ID = hotel.getRooms().size() + 1;
        this.capacity = capacity;
        this.price = price;
        this.booked = booked;
        this.hotel = hotel;
        this.description = description;
        rooms.add(this);
        hotel.addRoom(this);
    }

    // Abstract Methods
    public abstract float calculateTotalPrice(int numberOfDays);
    public abstract int getPriority();

    // Setters and Getters
    int getID() {
        return ID;
    }

    int getCapacity() {
        return capacity;
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
        return "Room " + getID() + " - " + getClass().getSimpleName();
    }

    public static List<Room> getAllRooms() {
        return rooms;
    }

    @Override
    public int compareTo(Room other) {
        return Integer.compare(this.getPriority(), other.getPriority());
    }

}