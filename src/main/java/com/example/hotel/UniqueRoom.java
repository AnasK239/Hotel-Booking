package com.example.hotel;

import java.util.Date;

public class UniqueRoom extends Room{
    private String uniqueFeature;
    private String theme;

    UniqueRoom(int capacity, float price, boolean booked, Hotel hotel, String description, String uniqueFeature, String theme) {
        super(capacity, price,  booked, hotel, description);
        this.uniqueFeature = uniqueFeature;
        this.theme = theme;
    }

    // Setters and Getters
    String getUniqueFeature() {
        return uniqueFeature;
    }
    void setUniqueFeature(String uniqueFeature) {
        this.uniqueFeature = uniqueFeature;
    }
    String getTheme() {
        return theme;
    }
    void setTheme(String theme) {
        this.theme = theme;
    }


    @Override
    public float calculateTotalPrice(int numberOfDays) {
        return getPrice() * numberOfDays * 1.3f;
    }
    @Override
    public int getPoints(){ return 3;}
    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof UniqueRoom)) return false;
        if (!super.equals(obj)) return false;
        UniqueRoom that = (UniqueRoom) obj;
        return uniqueFeature.equals(that.uniqueFeature) && theme.equals(that.theme);
    }


}
