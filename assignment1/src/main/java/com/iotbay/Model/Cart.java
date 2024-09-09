package com.iotbay.Model;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Destination;

public final class Cart {
    private List<Destination> destinations;

    public Cart() {
        destinations = new ArrayList<>();
    }

    // Check if the cart is empty
    public boolean isEmpty() {
        return destinations.isEmpty();
    }

    // Clear the cart
    public void clear() {
        destinations.clear();
    }

    // Add a destination to the cart
    public void addDestination(Destination destination) {
        destinations.add(destination);
    }

    // Remove a destination from the cart by index
    public void removeDestination(int index) {
        if (index >= 0 && index < destinations.size()) {
            destinations.remove(index);
        }
    }

    // Update the quantity of a destination by index
    public void updateQuantity(int index, int newQuantity) {
        if (index >= 0 && index < destinations.size()) {
            Destination destination = destinations.get(index);
            destination.setQuantity(newQuantity); // Assume Destination class has a setQuantity method
        }
    }

    // Get the list of destinations in the cart
    public List<Destination> getDestinations() {
        return destinations;
    }

    // Get the total number of items in the cart
    public int getTotalItems() {
        int total = 0;
        for (Destination destination : destinations) {
            total += destination.getQuantity();
        }
        return total;
    }

    // Calculate the total price of the cart
    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (Destination destination : destinations) {
            totalPrice += destination.getPrice() * destination.getQuantity(); // Assume Destination class has getPrice method
        }
        return totalPrice;
    }
}