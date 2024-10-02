package com.iotbay.Model;
import java.sql.*;



public class CustomerHotel extends Item{
    //Customers' booked hotel
    private Date checkInTime;
    private Date checkOutTime;
    private String roomType;
    private String roomSize;
    private String city;

    public CustomerHotel(Date checkInTime, Date checkOutTime, int itemID, String name, double price, int availability, String img, String roomType,
            String roomSize, String city) {
        super(itemID, name, price, availability, img);
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.roomType = roomType;
        this.roomSize = roomSize;
        this.city = city;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
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

}

