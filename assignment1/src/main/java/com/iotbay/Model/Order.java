package com.iotbay.Model;

import java.io.Serializable;
import java.sql.Timestamp;


public class Order implements Serializable {
    private Customer customer;
    private Cart cart;
    private int orderID;
    private double totalPrice;
    private Timestamp orderDate;

    // Default constructor
    public Order() {
    }

    public Order(int orderID, int customerID, double totalPrice, Timestamp orderDate) {
        this.orderID = orderID;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        // You'll need to fetch the customer and cart objects based on their IDs.
        this.customer = fetchCustomerByID(customerID);
    }

    // Getter and setter methods
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
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

    // These methods need to be implemented to fetch the customer and cart based on their IDs
    private Customer fetchCustomerByID(int customerID) {
        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = customerDAO.getCustomerByID(customerID);


        return new Customer(); 
    }
}
