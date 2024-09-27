package com.iotbay.Model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Item> cart;
    private Iterable<Item> items;

    public Cart() {
        cart = new ArrayList<Item>();
    }

    // Check if the cart is empty
    public boolean isEmpty() {
        return cart.isEmpty();
    }

    // Clear the cart
    public void clear() {
        cart.clear();
    }

    // Add a destination to the cart
    public void addItemToCart(Item item) {
        cart.add(item);
    }

    public void deleteItem(int index) {
        cart.remove(index);
    }
    
    // Get the list of destinations in the cart
    public List<Item> getItems() {
        return cart;
    }

    // Calculate the total price of the cart
    
    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (Item item : cart) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }

    public List<Flight> getFlightsInCart() {
        List<Flight> flights = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Flight) {
                flights.add((Flight) item);
            }
        }
        return flights;
    }

    // Get all Hotels in the cart
    public List<Hotel> getHotelsInCart() {
        List<Hotel> hotels = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Hotel) {
                hotels.add((Hotel) item);
            }
        }
        return hotels;
    }

    // Get all Packages in the cart
    public List<Package> getPackagesInCart() {
        List<Package> packages = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Package) {
                packages.add((Package) item);
            }
        }
        return packages;
    }
}


