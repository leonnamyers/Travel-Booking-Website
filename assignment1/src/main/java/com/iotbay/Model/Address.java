package com.iotbay.Model;

public class Address {
    private String streetAddress;
    private int postcode;
    private String city;
    private String state;

    public Address() {
        
    }

    public Address(String streetAddress, int postcode, String city, String state) {
        this.streetAddress = streetAddress;
        this.postcode = postcode;
        this.city = city;
        this.state = state;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }
}
