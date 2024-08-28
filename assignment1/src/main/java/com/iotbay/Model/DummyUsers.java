package com.iotbay.Model;

public class DummyUsers {
    private Customer dummyCustomer = new Customer(
        "somewhere@something.com", 
        "1234", 
        "Test", 
        "Subject", 
        new Address(
            "Somewhere",
            123,
            "Over The",
            "Rainbow"
        ));
    
    private Staff dummyClerk = new Staff(
        "staffClerk@clerk.com",
        "4321",
        "Alex",
        "Clerk",
        12345678,
        1
    );

    private Staff dummyAdmin = new Staff(
        "staffAdmin@admin.com",
        "0987",
        "Blake",
        "Admin",
        2334512,
        2
    );

    public Customer getDummyCustomer() {
        return dummyCustomer;
    }

    public Staff getDummyClerk() {
        return dummyClerk;
    }

    public Staff getDummyAdmin() {
        return dummyAdmin;
    }   
}
