package com.iotbay.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import com.iotbay.Model.*;

public class FlightDAO {

    private Statement st;
	private PreparedStatement readSt;
    private PreparedStatement filterSt;
	private PreparedStatement updateSt;
	private PreparedStatement deleteSt;
    // private PreparedStatement updateAvailabilitySt;
    private PreparedStatement fetchStockSt;
	private String readQuery = "SELECT itemID, name, price, availability, img, startTime, endTime, departureCity, destinationCity, stops, seatType, (TIME_TO_SEC(TIMEDIFF(endTime,startTime))/3600) FROM FlightCatalogue";
    private String filterQuery = "SELECT itemID, name, price, availability, img, startTime, endTime, departureCity, destinationCity, stops, seatType, (TIME_TO_SEC(TIMEDIFF(endTime,startTime))/3600) from FlightCatalogue where departureCity LIKE ? and destinationCity LIKE ? and seatType LIKE ? and startTime BETWEEN ? AND DATE_ADD(?, INTERVAL 1 DAY)";
	private String updateQuery = "UPDATE FlightCatalogue SET name = ?, price= ?, availability= ?, img= ?, startTime= ?, endTime= ?, departureCity= ?, destinationCity= ?, stops= ?, seatType= ? WHERE itemID= ?";
	private String deleteQuery = "DELETE FROM FlightCatalogue WHERE itemID= ? ";
    // private String updateAvailabilityQuery = "UPDATE FlightCatalogue SET availability= ? WHERE itemID= ?";
    private String fetchStock = "SELECT ProductInStock FROM Product WHERE ProductID=?";

    public FlightDAO(Connection connection) throws SQLException {
		connection.setAutoCommit(true);
		st = connection.createStatement();
		readSt = connection.prepareStatement(readQuery);
		updateSt = connection.prepareStatement(updateQuery);
		deleteSt = connection.prepareStatement(deleteQuery);
        filterSt = connection.prepareStatement(filterQuery);
	}

            // Read Operation: read a list of all devices with its image, id, name, type, price, details
    public ArrayList<Flight> fetchAllFlights() throws SQLException {
        
        ArrayList<Flight> flights = new ArrayList();
        ResultSet rs = readSt.executeQuery();

        while (rs.next()) {
                int itemID = rs.getInt(1);
                String name = rs.getString(2);
                Double price = rs.getDouble(3);
                int availability = rs.getInt(4);
                String img = rs.getString(5);
                Timestamp startTime = rs.getTimestamp(6);
                Timestamp endTime = rs.getTimestamp(7);
                String departureCity = rs.getString(8);
                String destinationCity = rs.getString(9);
                String stops = rs.getString(10);
                String seatType = rs.getString(11);
                int hours = rs.getInt(12);

                Flight flight = new Flight(itemID, name, price, availability, img, startTime, endTime,
                departureCity, destinationCity, hours, stops, seatType);
                flights.add(flight);
        }
        return flights;
    }

    public ArrayList<Flight> fetchFilteredFlights(String filtDepCity, String filtDesCity,Timestamp filtDepTime, String filtSeatType) throws SQLException {


        filterSt.setString(1, filtDepCity + "%");
        filterSt.setString(2, filtDesCity + "%");
        filterSt.setString(3, filtSeatType + "%");
        filterSt.setTimestamp(4, filtDepTime);
        filterSt.setTimestamp(5, filtDepTime);
        ResultSet rs = filterSt.executeQuery();
        
        ArrayList<Flight> flights = new ArrayList();
        
        while (rs.next()) {
            int itemID = rs.getInt(1);
            String name = rs.getString(2);
            Double price = rs.getDouble(3);
            int availability = rs.getInt(4);
            String img = rs.getString(5);
            Timestamp startTime = rs.getTimestamp(6);
            Timestamp endTime = rs.getTimestamp(7);
            String departureCity = rs.getString(8);
            String destinationCity = rs.getString(9);
            String stops = rs.getString(10);
            String seatType = rs.getString(11);
            int hours = rs.getInt(12);

            Flight flight = new Flight(itemID, name, price, availability, img, startTime, endTime,
            departureCity, destinationCity, hours, stops, seatType);
            flights.add(flight);
        }
        return flights;
    }


    
}
