package com.iotbay.Model;

public class CustomerUser extends User{
    private Address address;
    private int homePhoneNumber = -1;
    private int mobilePhoneNumber = -1;
    private boolean isActivated;
    private Integer savedPaymentID;
    private Integer savedShipmentID;

    public CustomerUser() {
        super.setUserType(UserType.CUSTOMER_USER);
    }

    public CustomerUser(String email, String password, String firstName, String lastName, UserType userType, Address address, int homePhoneNumber, int mobilePhoneNumber, boolean isActivated, Integer savedPaymentID, Integer savedShipmentID) {
        super(email, password, firstName, lastName, UserType.CUSTOMER_USER);
        this.address = address;
        this.homePhoneNumber = homePhoneNumber;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.isActivated = isActivated;
        this.savedPaymentID = savedPaymentID;
        this.savedShipmentID = savedShipmentID;
    }

    // Registration

    public CustomerUser(String email, String password, String firstName, String lastName, Address address) {
        super(email, password, firstName, lastName, UserType.CUSTOMER_USER);
        this.address = address;
        this.isActivated = true;
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

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean isActivated) {
        this.isActivated = isActivated;
    }

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
 

    // generate savedShipment ID


    // generate savedPaymentID


}
