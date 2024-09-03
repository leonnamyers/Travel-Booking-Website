package com.iotbay.Model;

import java.io.Serializable;

public class Customer implements Serializable {

    private String email;
    private UserType userType;

    public Customer() {
        this.userType = UserType.CUSTOMER;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public UserType getUserType() {
        return userType;
    }
}
