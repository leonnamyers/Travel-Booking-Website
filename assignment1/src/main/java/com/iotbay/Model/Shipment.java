package com.iotbay.Model;

import java.io.Serializable;

public class Shipment implements Serializable {
    private int shipmentID;
    private Address shipmentAddress;
    private String shipmentContactNumber;
    
    public Shipment() {
    }

    public int getShipmentID() {
        return shipmentID;
    }
    public void setShipmentID(int shipmentID) {
        this.shipmentID = shipmentID;
    }
    public Address getShipmentAddress() {
        return shipmentAddress;
    }
    public void setShipmentAddress(Address shipmentAddress) {
        this.shipmentAddress = shipmentAddress;
    }
    public String getShipmentContactNumber() {
        return shipmentContactNumber;
    }
    public void setShipmentContactNumber(String shipmentContactNumber) {
        this.shipmentContactNumber = shipmentContactNumber;
    }
}
