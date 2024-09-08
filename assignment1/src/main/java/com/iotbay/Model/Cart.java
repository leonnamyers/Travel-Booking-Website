package com.iotbay.Model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Item> cart;

    public Cart() {
        cart = new ArrayList<Item>();
    }

    public List<Item> getCart() {
        return cart;
    }

    public void setCart(List<Item> cart) {
        this.cart = cart;
    }
    
    // can change to a bool -> if Jialan or Bochi want there to be a success message or something to happen when it adds to the cart successfully
    public void addItemToCart(Item item) {
        cart.add(item);
    }

    public void removeItemFromCart(Item item) {
        cart.remove(item);
    }

}
