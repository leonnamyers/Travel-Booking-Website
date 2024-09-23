package com;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import com.iotbay.Dao.FlightDAO;
import com.iotbay.Dao.DBConnector;
import com.iotbay.Dao.DBManager;
import com.iotbay.Model.Flight;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;


public class FlightDAOTest {
    private DBConnector connector;
    private Connection conn;
    private FlightDAO flightDAOManager;

    public FlightDAOTest() throws ClassNotFoundException, SQLException {
        connector = new DBConnector();
        conn = connector.openConnection();
        flightDAOManager = new FlightDAO(conn);
    }


    @Test
    public void testFetchAllFlights() throws SQLException {
        ArrayList<Flight> allFlights = flightDAOManager.fetchAllFlights();
        assertEquals(allFlights.size(), 10);
    }

    @Test
    public void testFetchFilteredFlights() throws SQLException {
        ArrayList<Flight> filteredFlights = flightDAOManager.fetchFilteredFlights("", "", Timestamp.valueOf("2024-02-03 00:00:00"), "");
        assertEquals(filteredFlights.size(), 2);
    }


}
