package com.iotbay.Model;

public class DummyUsers {
    private static Customer dummyCustomer = new Customer(
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
    
    private static Staff dummyClerk = new Staff(
        "clerk@clerk.com",
        "4321",
        "Alex",
        "Clerk",
        12345678,
        1
    );

    private static Staff dummyAdmin = new Staff(
        "admin@admin.com",
        "0987",
        "Blake",
        "Admin",
        2334512,
        2
    );

    public static Customer getDummyCustomer() {
        return dummyCustomer;
    }

    public static Staff getDummyClerk() {
        return dummyClerk;
    }

    public static Staff getDummyAdmin() {
        return dummyAdmin;
    }   
}
