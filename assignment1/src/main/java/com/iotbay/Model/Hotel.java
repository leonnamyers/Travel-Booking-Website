package com.iotbay.Model;
import java.util.*;
import java.sql.*;

public class Hotel extends Item{

    private Timestamp checkInTime;
    private Timestamp checkOutTime;
    private String roomType;
    private int roomSize;
    private String roomDetails;
    private String city;
    
    public Hotel(Timestamp checkInTime,Timestamp checkOutTime,int itemID, String name, double price, int availability, String img, String roomType,
            int roomSize, String roomDetails, String city) {
        super(itemID, name, price, availability, img);
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.roomType = roomType;
        this.roomSize = roomSize;
        this.roomDetails = roomDetails;
        this.city = city;
    }

    public Timestamp getCheckInTime() {
        return checkInTime;
    }
    public void setCheckInTime(Timestamp checkInTime) {
        this.checkInTime = checkInTime;
    }
    public Timestamp getCheckOutTime() {
        return checkOutTime;
    }
    public void setCheckOutTime(Timestamp checkOutTime) {
        this.checkOutTime = checkOutTime;
    }
    public String getRoomType() {
        return roomType;
    }
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
    public int getRoomSize() {
        return roomSize;
    }
    public void setRoomSize(int roomSize) {
        this.roomSize = roomSize;
    }
    public String getRoomDetails() {
        return roomDetails;
    }
    public void setRoomDetails(String roomDetails) {
        this.roomDetails = roomDetails;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    


}
