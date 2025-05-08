package com.example.hotel;
import java.util.Date;
import java.util.List;

public class LuxuryRoom extends Room {
    private boolean hasBalcony;
    private boolean hasJacuzzi;

    LuxuryRoom(int capacity, float price, boolean booked, Hotel hotel, String description, boolean hasJacuzzi, boolean hasBalcony) {
        super(capacity,  price,  booked, hotel, description);
        this.hasJacuzzi = hasJacuzzi;
        this.hasBalcony = hasBalcony;

    }

    boolean hasJacuzzi() {
        return hasJacuzzi;
    }
    void setHasJacuzzi(boolean hasJacuzzi) {
        this.hasJacuzzi = hasJacuzzi;
    }
    boolean haseBalcony() {
        return hasBalcony;
    }
    void setHasBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }


    @Override
    public float calculateTotalPrice(int numberOfDays) {
        return getPrice() * numberOfDays * 1.5f;
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

    @Override
    public int getPriority() {
        return 1;
    }
}
