package com.iotbay.Model;

import java.sql.Timestamp;

public class Order {
    
    private int orderID;
    private Cart cart;
    private String customerID;
    private double totalPrice;
    private Timestamp orderDate;
    private String destination;
    private String departureDate;
    private String returnDate;
    private String seatType;

    // Constructor
    public Order(String customerID, double totalPrice, Timestamp orderDate, String destination, String departureDate, String returnDate, String seatType) {
        this.customerID = customerID;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.destination = destination;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.seatType = seatType;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }
}
