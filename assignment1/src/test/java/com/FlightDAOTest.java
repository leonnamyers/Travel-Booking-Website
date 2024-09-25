// package com;
// import org.junit.jupiter.api.AfterAll;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.TestInstance;
// import com.iotbay.Dao.FlightDAO;
// import com.iotbay.Dao.DBConnector;
// import com.iotbay.Dao.DBManager;
// import com.iotbay.Model.*;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import java.sql.Connection;
// import java.sql.Date;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Time;
// import java.sql.Timestamp;
// import java.util.ArrayList;


// public class FlightDAOTest {
//     private DBConnector connector;
//     private Connection conn;
//     private FlightDAO flightDAOManager;

//     public FlightDAOTest() throws ClassNotFoundException, SQLException {
//         connector = new DBConnector();
//         conn = connector.openConnection();
//         flightDAOManager = new FlightDAO(conn);
//     }


//     @Test
//     public void testFetchAllFlights() throws SQLException {
//         ArrayList<Flight> allFlights = flightDAOManager.fetchAllFlights();
//         assertEquals(allFlights.size(), 10);
//     }

//     @Test
//     public void testFetchFilteredFlights() throws SQLException {
//         ArrayList<Flight> filteredFlights = flightDAOManager.fetchFilteredFlights("", "", Timestamp.valueOf("2024-02-03 00:00:00"), "");
//         assertEquals(filteredFlights.size(), 2);
//     }

//     // @Test
//     // public void createFlights() throws SQLException {
//     //     flightDAOManager.createFlight("Harmony", 300, 70, "Harmony.jpg", Timestamp.valueOf("2024-04-10 20:30:00"), Timestamp.valueOf("2024-04-10 21:30:00"), "Melbourne", "Adelaide", "Non Stop", "Business");
//     // }

//     // @Test
//     // public void updateFlights() throws SQLException {
//     //     flightDAOManager.updateFlight(15,"Traveling", 300, 70, "Traveling.jpg", Timestamp.valueOf("2024-04-10 20:30:00"), Timestamp.valueOf("2024-04-10 21:30:00"), "Melbourne", "Adelaide", "Non Stop", "Business");
//     // }

//     // @Test
//     // public void deleteFlights() throws SQLException {
//     //     flightDAOManager.deleteFlight(15);
//     // }

//     @Test
//     public void getFlightItem() throws SQLException {
//         Item flight = flightDAOManager.fetchFlightItem(3);
//         assertEquals(flight.getItemID(), 3);
//         assertEquals(flight.getName(), "Traveling");
//         assertEquals(flight.getPrice(), 572);
//         assertEquals(flight.getAvailability(), 50);
//         assertEquals(flight.getImg(), "Travel.jpg");
//     }

// }
