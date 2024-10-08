package com.iotbay.Model;

public class Cruise extends Item {
    private String port;  // 邮轮出发港口
    private String description; // 邮轮描述

    public Cruise() {
        super();
        this.port = null;
        this.description = null;
    }

    // 这是新的构造函数，符合你在controller中的调用方式
    public Cruise(int itemID, String name, double price, String port, String description, int availability) {
        super(itemID, name, price, availability, null);  // img 默认为 null
        this.port = port;
        this.description = description;
    }

    // 之前已有的构造函数
    public Cruise(int itemID, String name, double price, int availability, String img, String port, String description) {
        super(itemID, name, price, availability, img);
        this.port = port;
        this.description = description;
    }

    // Getters and Setters...

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
