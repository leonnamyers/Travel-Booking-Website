package com.iotbay.Model;
import java.util.*;
import java.sql.Date;
import java.sql.Time;

public class Hotel extends Item{

    private Timestamp checkInTime;
    private Timestamp checkOutTime;
    private String roomType;
    private String roomSize;
    private String city;
    private int days;
    private double totalPrice;

    public Hotel(Timestamp checkInTime,Timestamp checkOutTime,int itemID, String name, double price, int availability, String img, String roomType,
            String roomSize, String city, int days, int totalPrice) {
        super(itemID, name, price, availability, img);
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.roomType = roomType;
        this.roomSize = roomSize;
        this.days = days;
        this.city = city;
        this.totalPrice = totalPrice;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
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
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    


}
