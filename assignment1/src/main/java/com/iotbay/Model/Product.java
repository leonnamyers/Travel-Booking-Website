package com.iotbay.Model;

import java.io.Serializable;
import java.util.Random;

public class Product implements Serializable {

    private int productID;
    private String name;
    private String description;
    private double unitPrice;
    private int quantityInStock;

    public Product() {
        this.productID = generateRandomID();
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    private int generateRandomID() {
        Random random = new Random();
        return 1000000000 + random.nextInt(900000000); // 10桁のランダムな整数を生成
    }
}
