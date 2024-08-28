package com.iotbay.Model;

public class Customer extends User{
    private Address address;
    private int homePhoneNumber = -1;
    private int mobilePhoneNumber = -1;
    private Integer savedPaymentID;
    private Integer savedShipmentID;

    public Customer() {
        super.setUserType(UserType.CUSTOMER);
    }

    public Customer(String email, String password, String firstName, String lastName, UserType userType, Address address, int homePhoneNumber, int mobilePhoneNumber, boolean isActivated, Integer savedPaymentID, Integer savedShipmentID) {
        super(email, password, firstName, lastName, UserType.CUSTOMER);
        this.address = address;
        this.homePhoneNumber = homePhoneNumber;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.savedPaymentID = savedPaymentID;
        this.savedShipmentID = savedShipmentID;
    }

    // Registration

    public Customer(String email, String password, String firstName, String lastName, Address address) {
        super(email, password, firstName, lastName, UserType.CUSTOMER);
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public void setHomePhoneNumber(int homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public int getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(int mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    /*

    public Integer getSavedPaymentID() {
        return savedPaymentID;
    }

    public void setSavedPaymentID(Integer savedPaymentID) {
        this.savedPaymentID = savedPaymentID;
    }

    public Integer getSavedShipmentID() {
        return savedShipmentID;
    }

    public void setSavedShipmentID(Integer savedShiptmentID) {
        this.savedShipmentID = savedShiptmentID;
    }
 */


}
