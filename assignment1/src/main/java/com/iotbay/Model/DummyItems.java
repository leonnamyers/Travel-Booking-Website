package com.iotbay.Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DummyItems {

    public static Flight createDummyFlight() {
        return new Flight(
            0, 
            "Test", 
            100.00, 
            1, 
            "", 
            Timestamp.valueOf(LocalDateTime.now()),
            "Sydney", 
            "Brisbane", 
            2, 
            "No stops", 
            "Economy"
        );
    }
}
