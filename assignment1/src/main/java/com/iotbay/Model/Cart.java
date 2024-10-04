package com.iotbay.Model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Item> cart;

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
    public void addItem(Item item) {
        cart.add(item);
    }

    public void removeItem(Item item) {
        cart.remove(item);
    }

    public void removeItem(int index) {
        cart.remove(index);
    }

    /*
    // Remove a destination from the cart by index
    public void removeDestination(int index) {
        if (index >= 0 && index < cart.size()) {
            cart.remove(index);
        }
    }*/

    // Update the quantity of a destination by index
    /*public void updateQuantity(int index, int newQuantity) {
        if (index >= 0 && index < cart.size()) {
            Destinations destination = cart.get(index);  // Corrected here
            destination.setQuantity(newQuantity);
        }
    }*/

    // Get the list of destinations in the cart
    public List<Item> getItems() {
        return cart;
    }

    // Get the total number of items in the cart
    /*public int getTotalItems() {
        int total = 0;
        for (Item item : cart) {
            total += item.getQuantity();
        }
        return total;
    }
        */

    // Calculate the total price of the cart
    
    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (Item item : cart) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }


}
