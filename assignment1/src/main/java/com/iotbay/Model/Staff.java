package com.iotbay.Model;

public class Staff extends User {
    private int staffID;
    private int staffTypeID;
    private StaffType staffType;

    public Staff() {
        super.setUserType(UserType.STAFF);
    }
    
    public Staff(String email, String password, String firstName, String lastName, StaffType staffType) {
        super(email, password, firstName, lastName, UserType.STAFF);
        this.staffType = staffType;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public int getStaffTypeID() {
        return staffTypeID;
    }

    public void setStaffTypeID(int staffTypeID) {
        this.staffTypeID = staffTypeID;
    }

    public StaffType getStaffType() {
        return staffType;
    }

    public void setStaffType(StaffType staffType) {
        this.staffType = staffType;
    }

    
}
