package com.iotbay.Model;

public class Cruise extends Item {
    private String port;          // 出发港口
    private String description;   // 描述
    private int duration;         // 持续时间
    private String departureDate; // 出发日期
    private String specialOffer;  // 特别优惠
    private String location;      // 位置

    // 完整的构造函数，包含所有字段
    public Cruise(int itemID, String name, double price, int availability, String img, String port, String description, int duration, String departureDate, String specialOffer, String location) {
        super(itemID, name, price, availability, img);  // 调用父类Item的构造函数
        this.port = port;
        this.description = description;
        this.duration = duration;
        this.departureDate = departureDate;
        this.specialOffer = specialOffer;
        this.location = location;
    }

    // Getters 和 Setters

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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getSpecialOffer() {
        return specialOffer;
    }

    public void setSpecialOffer(String specialOffer) {
        this.specialOffer = specialOffer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
