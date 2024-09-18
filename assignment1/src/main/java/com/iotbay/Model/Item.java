package com.iotbay.Model;
import java.util.*;
import java.sql.Date;
import java.sql.Time;
import java.io.Serializable;

public class Item implements Serializable{
    private int itemID;
    private String name;
    private double price;
    private int availability;
    private String img; 

    public Item(){
        this.itemID = 0;
        this.name = null;
        this.price = 0;
        this.availability = 0;
        this.img = null;
    }
    
    public Item(int itemID, String name, double price, int availability, String img){
        this.itemID = itemID;
        this.name = name;
        this.price = price;
        this.availability = availability;
        this.img = img;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getAvailability() {
        return availability;
    }
    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }






}
