package com.iotbay.Model;
import java.util.*;
import java.sql.Date;
import java.sql.Time;

public class Hotel extends Item{

    private String roomType;
    private String roomSize;
    private String roomDetails;
    private String city;
    
    public Hotel(String itemID, String name, double price, int availability, String img, String roomType,
            String roomSize, String roomDetails, String city) {
        super(itemID, name, price, availability, img);
        this.roomType = roomType;
        this.roomSize = roomSize;
        this.roomDetails = roomDetails;
        this.city = city;
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
