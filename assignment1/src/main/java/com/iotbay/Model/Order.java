package com.iotbay.Model;

import java.io.Serializable;

public class Order implements Serializable {
    Customer customer;
    Cart cart;

    public Order() {

    }

    public Order(Customer customer, Cart cart) {
        this.customer = customer;
        this.cart = cart;
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

    

    /*
    CREATE TABLE `Order` (
        `order_id` int NOT NULL,
        `email` varchar(100) DEFAULT NULL,
        `order_date` date DEFAULT NULL,
        PRIMARY KEY (`order_id`)
      )
       
      CREATE TABLE `OrderLine` (
        `order_id` int NOT NULL,
        `item_id` int NOT NULL,
        `item_type` char(2) NOT NULL,
        `quantity` int NOT NULL,
        `payment_id` int DEFAULT NULL,
        `item_price` decimal(10,2) DEFAULT NULL,
        `total_price` decimal(10,2) GENERATED ALWAYS AS ((`item_price` * `quantity`)) STORED,
        PRIMARY KEY (`order_id`,`item_id`,`item_type`),
        KEY `item_id` (`item_id`,`item_type`),
        CONSTRAINT `orderline_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `Order` (`order_id`),
        CONSTRAINT `orderline_ibfk_2` FOREIGN KEY (`item_id`, `item_type`) REFERENCES `Item` (`item_id`, `item_type`)
      )
        */
      

}

    /*
    private int bookingNumber;
    private String customerEmail;
    private String destination;
    private String departureDate;
    private String returnDate;
    private int numberOfPassengers;

    // Default constructor
    public Order() {
    }

    // Getters and setters
    public int getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
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

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }
}
    */
