package com.iotbay.Model;
import java.util.*;
import java.sql.Date;
import java.sql.Time;

public class Flight extends Item {
    private Time startTime;
    private Date startDate;
    private String departureCity;
    private String destinationCity;
    private int hours;
    private String stops;
    private String sitType;


    public Flight(String itemID, String name, double price, int availability, String img, Time startTime, 
    Date startDate, String departureCity, String destinationCity, int hours, String stops, String sitType){
        super(itemID, name, price, availability,img);
        this.startTime = startTime;
        this.startDate = startDate;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.hours = hours;
        this.stops = stops;
        this.sitType = sitType;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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

    public String getSitType() {
        return sitType;
    }

    public void setSitType(String sitType) {
        this.sitType = sitType;
    }
    
}
