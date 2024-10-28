package com.iotbay.Model;

import java.sql.Timestamp;

public class Order {
    
    private int orderID;
    private String customerID;
    private Cart cart;
    private int TotalPrice;
    private Timestamp orderDate;
    private String startTime;
    private String endTime;
    private String seatType;
    private String roomType;

    // Constructor
    public Order(int orderID, String customerID, Cart cart, Timestamp orderDate, int TotalPrice, String startTime, String endTime, String seatType, String roomType) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.cart = cart;
        this.TotalPrice = TotalPrice;
        this.orderDate = orderDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.seatType = seatType;
        this.roomType = roomType;
    }

    // Getters and setters

    public Order() {
        
    }
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.TotalPrice = (int) totalPrice;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public String getStartTime() {
        return startTime;  
    }    

    public void setStartTime(String startTime) {
        this.startTime = startTime;  
    }

    public String getEndTime() {
        return endTime;    
    }    

    public void setEndTime(String endTime) {
        this.endTime = endTime;  
    }
   
    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
}
