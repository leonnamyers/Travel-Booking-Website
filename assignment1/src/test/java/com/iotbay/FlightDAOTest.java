// package com.iotbay;

<<<<<<< HEAD
// import static org.junit.jupiter.api.Assertions.*;
=======
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
>>>>>>> b5c8c9e6db67493dacce662f5e0095daa444bfc5

// import java.sql.Connection;
// import java.sql.SQLException;
// import java.sql.Timestamp;
// import java.util.ArrayList;

<<<<<<< HEAD
// import org.junit.jupiter.api.Test;

// import com.iotbay.Dao.DBConnector;
// import com.iotbay.Dao.FlightDAO;
// import com.iotbay.Model.Flight;
// import com.iotbay.Model.Item;
=======
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.iotbay.Dao.DBConnector;
import com.iotbay.Dao.FlightDAO;
import com.iotbay.Dao.PackageDAO;
import com.iotbay.Model.Flight;
import com.iotbay.Model.Item;
>>>>>>> b5c8c9e6db67493dacce662f5e0095daa444bfc5

// public class FlightDAOTest {

<<<<<<< HEAD
//     private DBConnector connector;
//     private Connection conn;
//     private FlightDAO flightDAOManager;

//     public FlightDAOTest() throws ClassNotFoundException, SQLException {
//         connector = new DBConnector();
//         conn = connector.openConnection();
//         flightDAOManager = new FlightDAO(conn);

//     }

//     //Read
//     @Test
//     public void testFetchAllFlights() throws SQLException {
//         ArrayList<Flight> allFlights = flightDAOManager.fetchAllFlights();
//         assertEquals(allFlights.size(), 10);
//     }
//     //Read: Search
//     @Test
//     public void testFetchFilteredFlights() throws SQLException {
//         ArrayList<Flight> filteredFlights = flightDAOManager.fetchFilteredFlights("", "", "2024-02-03", "");
//         assertEquals(filteredFlights.size(), 2);
//     }
//     //Create
//     @Test
//     public void createFlights() throws SQLException {
//         flightDAOManager.createFlight("Harmony", 300, 70, "Harmony.jpg", Timestamp.valueOf("2024-04-10 20:30:00"), Timestamp.valueOf("2024-04-10 21:30:00"), "Melbourne", "Adelaide", "Non Stop", "Business");
//         ArrayList<Flight> filteredFlights = flightDAOManager.fetchFilteredFlights("Melbourne", "Adelaide","2024-04-10 20:30:00", "Business");
//         Flight flight = flightDAOManager.fetchFlight(filteredFlights.get(0).getItemID());
//         assertEquals(flight.getName(), "Harmony");
//         assertEquals(flight.getPrice(), 300);
//         assertEquals(flight.getAvailability(), 70);
//         assertEquals(flight.getImg(), "Harmony.jpg");
//         assertEquals(flight.getStartTime(), Timestamp.valueOf("2024-04-10 20:30:00"));
//         assertEquals(flight.getEndTime(), Timestamp.valueOf("2024-04-10 21:30:00"));
//         assertEquals(flight.getDepartureCity(),"Melbourne");
//         assertEquals(flight.getDestinationCity(),"Adelaide");
//         assertEquals(flight.getStops(),"Non Stop");
//         assertEquals(flight.getSeatType(),"Business");
//         assertEquals(flight.getHours(),1);
//     }
//     //Update
//     @Test
//     public void updateFlights() throws SQLException {
//         ArrayList<Flight> filteredFlights = flightDAOManager.fetchFilteredFlights("Melbourne", "Adelaide","2024-04-10 20:30:00", "Business");
//         Flight flight = flightDAOManager.fetchFlight(filteredFlights.get(0).getItemID());
//         flightDAOManager.updateFlight(flight.getItemID(),"Traveling", 534, 40, "Traveling.jpg", Timestamp.valueOf("2024-04-10 22:43:00"), Timestamp.valueOf("2024-04-11 02:10:00"), "Brisbane", "Canberra", "Non Stop", "Business");
//         flight = flightDAOManager.fetchFlight(filteredFlights.get(0).getItemID());
//         assertEquals(flight.getName(), "Traveling");
//         assertEquals(flight.getPrice(), 534);
//         assertEquals(flight.getAvailability(), 40);
//         assertEquals(flight.getImg(), "Traveling.jpg");
//         assertEquals(flight.getStartTime(), Timestamp.valueOf("2024-04-10 22:43:00"));
//         assertEquals(flight.getEndTime(), Timestamp.valueOf("2024-04-11 02:10:00"));
//         assertEquals(flight.getDepartureCity(),"Brisbane");
//         assertEquals(flight.getDestinationCity(),"Canberra");
//         assertEquals(flight.getStops(),"Non Stop");
//         assertEquals(flight.getSeatType(),"Business");
//         assertEquals(flight.getHours(),3);
//         flightDAOManager.deleteFlight(flight.getItemID());
//     }
//     //Add to cart
//     @Test
//     public void getFlightItem() throws SQLException {
//         Item flight = flightDAOManager.fetchFlightItem(3);
//         assertEquals(flight.getItemID(), 3);
//         assertEquals(flight.getName(), "Traveling");
//         assertEquals(flight.getPrice(), 572);
//         assertEquals(flight.getAvailability(), 50);
//         assertEquals(flight.getImg(), "Travel.jpg");
//     }

//     //Delete
//     @Test
//     public void deleteFlights() throws SQLException {
//         flightDAOManager.createFlight("JetEngine", 300, 70, "JetEngine.jpg", Timestamp.valueOf("2025-03-13 14:00:00"), Timestamp.valueOf("2025-03-13 19:48:00"), "Sydeney", "Hobart", "1 Stop", "Economy");
//         ArrayList<Flight> filteredFlights = flightDAOManager.fetchFilteredFlights("Sydeney", "Hobart","2025-03-13 14:00:00", "Economy");
//         Flight flight = flightDAOManager.fetchFlight(filteredFlights.get(0).getItemID());
//         ArrayList<Flight> checkList = flightDAOManager.fetchAllFlights();
//         assertEquals(checkList.size(), 11);

//         flightDAOManager.deleteFlight(flight.getItemID());
//         checkList = flightDAOManager.fetchAllFlights();
//         assertEquals(checkList.size(), 10);
//     }
=======

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
    public void readAllFlights() throws SQLException {
        ArrayList<Flight> allFlights = flightDAOManager.fetchAllFlights();
        assertEquals(allFlights.size(), 6);
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
>>>>>>> b5c8c9e6db67493dacce662f5e0095daa444bfc5

    private int hoursDiff(Timestamp beginTime, Timestamp endTime)
    {
        long milliseconds1 = beginTime.getTime();
        long milliseconds2 = endTime.getTime();

        long diff = milliseconds2 - milliseconds1;
        int diffHours = (int)(diff / (60 * 60 * 1000));

        return diffHours;
    }

// }
