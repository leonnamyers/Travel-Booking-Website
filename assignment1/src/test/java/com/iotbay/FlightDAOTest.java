package com.iotbay;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.iotbay.Dao.DBConnector;
import com.iotbay.Dao.FlightDAO;
import com.iotbay.Dao.PackageDAO;
import com.iotbay.Model.Flight;
import com.iotbay.Model.Item;

public class FlightDAOTest {


    private FlightDAO flightDAOManager;
    private ArrayList<Flight> mockFlighList;

    @BeforeEach
    public void setup() throws SQLException {
        flightDAOManager = Mockito.mock(FlightDAO.class);
        mockFlighList = new ArrayList<Flight>();

        mockFlighList.add(
        new Flight(
        1, 
        "Traveling", 
        567, 
        50, 
        "Travel.jpg", 
        Timestamp.valueOf("2024-10-24 06:30:00"),
        Timestamp.valueOf("2024-10-24 09:30:00"), 
        "Sydney", 
        "Perth", 
        hoursDiff(Timestamp.valueOf("2024-10-24 06:30:00"), Timestamp.valueOf("2024-10-24 09:30:00")), 
        "Non Stop", 
        "Economy"));

        mockFlighList.add(
        new Flight(
        2, 
        "Traveling", 
        1938, 
        50, 
        "Travel.jpg", 
        Timestamp.valueOf("2024-10-30 09:30:00"),
        Timestamp.valueOf("2024-10-30 12:30:00"), 
        "Perth", 
        "Sydney", 
        hoursDiff(Timestamp.valueOf("2024-10-30 09:30:00"), Timestamp.valueOf("2024-10-30 12:30:00")), 
        "Non Stop", 
        "Business"));

        mockFlighList.add(
            new Flight(
            3, 
            "Traveling", 
            572, 
            50, 
            "Travel.jpg", 
            Timestamp.valueOf("2024-11-02 15:24:00"),
            Timestamp.valueOf("2024-11-02 20:04:00"), 
            "Adelaide", 
            "Gold Coast", 
            hoursDiff(Timestamp.valueOf("2024-11-02 15:24:00"), Timestamp.valueOf("2024-11-02 20:04:00")), 
            "Non Stop", 
            "Business"));

        mockFlighList.add(
            new Flight(
            4, 
            "Harmony", 
            503, 
            50, 
            "Harmony.jpg", 
            Timestamp.valueOf("2024-11-13 07:40:00"),
            Timestamp.valueOf("2024-11-13 12:24:00"), 
            "Gold Coast", 
            "Adelaide", 
            hoursDiff( Timestamp.valueOf("2024-11-13 07:40:00"), Timestamp.valueOf("2024-11-13 12:24:00")), 
            "Non Stop", 
            "Premium Economy"));

        mockFlighList.add(
            new Flight(
            5, 
            "Harmony", 
            551, 
            50, 
            "Harmony.jpg", 
            Timestamp.valueOf("2024-02-03 20:50:00"),
            Timestamp.valueOf("2024-02-03 23:05:00"), 
            "Gold Coast", 
            "Brisbane", 
            hoursDiff(Timestamp.valueOf("2024-02-03 20:50:00"), Timestamp.valueOf("2024-02-03 23:05:00")), 
            "Non Stop", 
            "Business"));

        mockFlighList.add(
            new Flight(
            6, 
            "Harmony", 
            520, 
            50, 
            "Harmony.jpg", 
            Timestamp.valueOf("2024-02-03 06:05:00"),
            Timestamp.valueOf("2024-02-03 08:30:00"), 
            "Brisbane", 
            "Gold Coast", 
            hoursDiff(Timestamp.valueOf("2024-02-03 06:05:00"), Timestamp.valueOf("2024-02-03 08:30:00")), 
            "Non Stop", 
            "Business"));


        when(flightDAOManager.fetchAllFlights()).thenReturn(mockFlighList);
    }

    private Flight mockFetch(int id) throws SQLException{
        when(flightDAOManager.fetchFlight(id)).thenReturn(mockFlighList.get(id-1));
        Flight testFlight = flightDAOManager.fetchFlight(id);
        return testFlight;
    }


    //Read
    @Test
    public void testFetchAllFlights() throws SQLException {
        ArrayList<Flight> allFlights = flightDAOManager.fetchAllFlights();
        assertEquals(allFlights.size(), 10);
        Flight testFlight = mockFetch(2);
        assertNotNull(testFlight);
        assertEquals(2, testFlight.getItemID());
        assertEquals("Traveling", testFlight.getName());
        assertEquals(Timestamp.valueOf("2024-10-30 09:30:00"), testFlight.getStartTime());
    }

    //Create
    @Test
    public void createFlights() throws SQLException {

        doNothing().when(flightDAOManager).createFlight(
            "Harmony", 
            300, 
            70, 
            "Harmony.jpg", 
            Timestamp.valueOf("2024-04-10 20:30:00"), 
            Timestamp.valueOf("2024-04-10 21:30:00"), 
            "Melbourne", 
            "Adelaide", 
            "Non Stop", 
            "Business");

        flightDAOManager.createFlight(
            "Harmony", 
            300, 
            70, 
            "Harmony.jpg", 
            Timestamp.valueOf("2024-04-10 20:30:00"), 
            Timestamp.valueOf("2024-04-10 21:30:00"), 
            "Melbourne", 
            "Adelaide", 
            "Non Stop", 
            "Business"
        );
        verify(flightDAOManager).createFlight(
            "Harmony", 
            300, 
            70, 
            "Harmony.jpg", 
            Timestamp.valueOf("2024-04-10 20:30:00"), 
            Timestamp.valueOf("2024-04-10 21:30:00"), 
            "Melbourne", 
            "Adelaide", 
            "Non Stop", 
            "Business"
        );
    }
    //Update
    @Test
    public void updateFlights() throws SQLException {

        doNothing().when(flightDAOManager).updateFlight(
            2,
            "Harmony", 
            300, 
            70, 
            "Harmony.jpg", 
            Timestamp.valueOf("2024-04-10 20:30:00"), 
            Timestamp.valueOf("2024-04-10 21:30:00"), 
            "Melbourne", 
            "Adelaide", 
            "Non Stop", 
            "Business");

        flightDAOManager.updateFlight(
            2,
            "Harmony", 
            300, 
            70, 
            "Harmony.jpg", 
            Timestamp.valueOf("2024-04-10 20:30:00"), 
            Timestamp.valueOf("2024-04-10 21:30:00"), 
            "Melbourne", 
            "Adelaide", 
            "Non Stop", 
            "Business"
        );
        verify(flightDAOManager).updateFlight(
            2,
            "Harmony", 
            300, 
            70, 
            "Harmony.jpg", 
            Timestamp.valueOf("2024-04-10 20:30:00"), 
            Timestamp.valueOf("2024-04-10 21:30:00"), 
            "Melbourne", 
            "Adelaide", 
            "Non Stop", 
            "Business"
        );
    }
    //Add to cart
    @Test
    public void getFlightItem() throws SQLException {
        Flight testFlight = mockFetch(1);
        assertEquals(1, testFlight.getItemID());
        assertEquals("Traveling", testFlight.getName());
        assertEquals("Travel.jpg", testFlight.getImg());
        assertEquals("Perth", testFlight.getDestinationCity());
        assertEquals("Economy", testFlight.getSeatType());
    }

    //Delete
    @Test
    public void deleteFlights() throws SQLException {
        doNothing().when(flightDAOManager).deleteFlight(4);
        flightDAOManager.deleteFlight(4);
        verify(flightDAOManager).deleteFlight(4);
    }

    private int hoursDiff(Timestamp beginTime, Timestamp endTime)
    {
        long milliseconds1 = beginTime.getTime();
        long milliseconds2 = endTime.getTime();

        long diff = milliseconds2 - milliseconds1;
        int diffHours = (int)(diff / (60 * 60 * 1000));

        return diffHours;
    }

}
