package com.iotbay.Model;
import java.sql.*;


public class Hotel extends Item{

    private String roomType;
    private String roomSize;
    private String city;
    private Date availabilityBeginDate;
    private Date availabilityEndDate;

    public Hotel(int itemID, String name, double price, int availability, String img, String roomType,
            String roomSize, String city, Date availabilityBeginDate, Date availabilityEndDate) {
        super(itemID, name, price, availability, img);
        this.roomType = roomType;
        this.roomSize = roomSize;
        this.city = city;
        this.availabilityBeginDate = availabilityBeginDate;
        this.availabilityEndDate = availabilityEndDate;
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
        public Date getAvailabilityBeginDate() {
        return availabilityBeginDate;
    }

    public void setAvailabilityBeginDate(Date availabilityBeginDate) {
        this.availabilityBeginDate = availabilityBeginDate;
    }

    public Date getAvailabilityEndDate() {
        return availabilityEndDate;
    }

    public void setAvailabilityEndDate(Date availabilityEndDate) {
        this.availabilityEndDate = availabilityEndDate;
    }

}
