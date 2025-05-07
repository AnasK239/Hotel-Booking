package com.example.hotel;
import java.util.Date;
import java.util.List;

public class LuxuryRoom extends Room {
    private boolean hasBalcony;
    private boolean hasJacuzzi;
    private boolean hasPrivateBalcony;

    LuxuryRoom(int capacity, float price, boolean booked, Date availableDate, Hotel hotel, String description, boolean hasJacuzzi, boolean hasBalcony) {
        super(capacity,  price,  booked, availableDate, hotel, description);
        this.hasJacuzzi = hasJacuzzi;
        this.hasBalcony = hasBalcony;

    }

    boolean hasJacuzzi() {
        return hasJacuzzi;
    }
    void setHasJacuzzi(boolean hasJacuzzi) {
        this.hasJacuzzi = hasJacuzzi;
    }
    boolean hasPrivateBalcony() {
        return hasPrivateBalcony;
    }
    void setHasPrivateBalcony(boolean hasPrivateBalcony) {
        this.hasPrivateBalcony = hasPrivateBalcony;
    }


    @Override
    public void displayRoomDetails() {
        // Set Text Fields in the UI to the Data
        // Return type ?
    }

    @Override
    public float calculateTotalPrice(int numberOfDays) {
        return getPrice() * numberOfDays;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof LuxuryRoom)) return false;
        if (!super.equals(obj)) return false;
        LuxuryRoom that = (LuxuryRoom) obj;
        return hasJacuzzi == that.hasJacuzzi &&
                hasBalcony == that.hasBalcony;
    }
}
