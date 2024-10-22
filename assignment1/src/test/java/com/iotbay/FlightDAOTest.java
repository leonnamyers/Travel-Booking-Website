// package com.iotbay;

// import static org.junit.jupiter.api.Assertions.*;

// import java.sql.Connection;
// import java.sql.SQLException;
// import java.sql.Timestamp;
// import java.util.ArrayList;

// import org.junit.jupiter.api.Test;

// import com.iotbay.Dao.DBConnector;
// import com.iotbay.Dao.FlightDAO;
// import com.iotbay.Model.Flight;
// import com.iotbay.Model.Item;

// public class FlightDAOTest {

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


// }
