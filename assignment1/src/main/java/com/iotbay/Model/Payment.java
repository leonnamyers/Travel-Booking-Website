package com.iotbay.Model;

import java.io.Serializable;

public class Payment implements Serializable {
    private int paymentID;
    private String cardType;
    private String cardNumber;
    private String expiryDate;
    private Address billingAddress;
    private String cvn;
    private double amount;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCvn(String cvn) {
        this.cvn = cvn;
    }

    public String getCvn() {
        return cvn;
    }

    public Payment() {
    }

    public int getPaymentID() {
        return paymentID;
    }
    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }
    public String getCardType() {
        return cardType;
    }
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public String getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
    public Address getAddress() {
        return billingAddress;
    }
    public void setAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }
    
}
