package com.iotbay.Model;
import java.sql.*;


public class Hotel extends Item{
    
    //skeleton hotel
    private String roomType;
    private String roomSize;
    private String city;
    private Date availableBeginDate;
    private Date availableEndDate;

    public Hotel(int itemID, String name, double price, int availability, String img, String roomType,
            String roomSize, String city, Date availabilityBeginDate, Date availabilityEndDate) {
        super(itemID, name, price, availability, img);
        this.roomType = roomType;
        this.roomSize = roomSize;
        this.city = city;
        this.availableBeginDate = availabilityBeginDate;
        this.availableEndDate = availabilityEndDate;
    }

    public String getRoomType() {
        return roomType;
    }
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
    public String getRoomSize() {
        return roomSize;
    }
    public void setRoomSize(String roomSize) {
        this.roomSize = roomSize;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
        public Date getAvailableBeginDate() {
        return availableBeginDate;
    }

    public void setAvailableBeginDate(Date availabilityBeginDate) {
        this.availableBeginDate = availabilityBeginDate;
    }

    public Date getAvailableEndDate() {
        return availableEndDate;
    }

    public void setAvailableEndDate(Date availabilityEndDate) {
        this.availableEndDate = availabilityEndDate;
    }

}
