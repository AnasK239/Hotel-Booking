package com.example.hotel;

import java.util.Date;

public class StandardRoom extends Room{

    private String bedType;
    private boolean hasTV;

    StandardRoom(int capacity, float price, boolean booked, Date availableDate, Hotel hotel,
                    String bedType, boolean hasTV, String description) {

        super(capacity, price,  booked,  availableDate,  hotel,  description);
        this.bedType = bedType;
        this.hasTV = hasTV;
    }

    //Setters and Getters
    String getBedType() {
        return bedType;
    }
    void setBedType(String bedType) {
        this.bedType = bedType;
    }
    boolean hasTV() {
        return hasTV;
    }
    void setHasTV(boolean hasTV) {
        this.hasTV = hasTV;
    }

    // Methods
    @Override
    public void displayRoomDetails() {
        // Waiting implementation
    }

    @Override
    public float calculateTotalPrice(int numberOfDays) {
        return getPrice()*numberOfDays*1.1f;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof StandardRoom)) return false;
        if (!super.equals(obj)) return false;
        StandardRoom that = (StandardRoom) obj;
        return hasTV == that.hasTV &&
                bedType.equals(that.bedType);
    }


}