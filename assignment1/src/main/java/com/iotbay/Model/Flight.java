package com.iotbay.Model;
import java.util.*;
import java.sql.*;


public class Flight extends Item {
    private Timestamp startTime;
    private String departureCity;
    private String destinationCity;
    private int hours;
    private String stops;
    private String seatType;


    public Flight(String itemID, String name, double price, int availability, String img, Timestamp startTime, 
     String departureCity, String destinationCity, int hours, String stops, String seatType){
        super(itemID, name, price, availability,img);
        this.startTime = startTime;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.hours = hours;
        this.stops = stops;
        this.seatType = seatType;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getStops() {
        return stops;
    }

    public void setStops(String stops) {
        this.stops = stops;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String sitType) {
        this.seatType = sitType;
    }
    
}