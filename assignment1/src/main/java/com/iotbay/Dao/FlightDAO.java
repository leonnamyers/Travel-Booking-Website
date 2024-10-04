package com.iotbay.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.iotbay.Model.Flight;
import com.iotbay.Model.Item;

public class FlightDAO {
    //CRUD statements for FlightCatalogue table
    //Item st for get flight as item (super class) 
    private Statement st;
	private PreparedStatement readSt;
    private PreparedStatement filterSt;
    private PreparedStatement createSt;
	private PreparedStatement updateSt;
	private PreparedStatement deleteSt;
    private PreparedStatement getFlightItemSt;
    private PreparedStatement getFlightSt;

	private String readQuery = "SELECT itemID, name, price, availability, img, startTime, endTime, departureCity, destinationCity, stops, seatType, (TIME_TO_SEC(TIMEDIFF(endTime,startTime))/3600) FROM FlightCatalogue ORDER BY name";
    private String filterQuery = "SELECT itemID, name, price, availability, img, startTime, endTime, departureCity, destinationCity, stops, seatType, (TIME_TO_SEC(TIMEDIFF(endTime,startTime))/3600) FROM FlightCatalogue WHERE departureCity LIKE ? and destinationCity LIKE ? and seatType LIKE ? and startTime LIKE ? ORDER BY name";
	private String createQuery = "INSERT INTO FlightCatalogue (name, price, availability, img, startTime, endTime, departureCity, destinationCity, stops, seatType) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private String updateQuery = "UPDATE FlightCatalogue SET name = ?, price= ?, availability= ?, img= ?, startTime= ?, endTime= ?, departureCity= ?, destinationCity= ?, stops= ?, seatType= ? WHERE itemID= ?";
	private String deleteQuery = "DELETE FROM FlightCatalogue WHERE itemID= ?";
    private String getFlightItemQuery = "SELECT name, price, availability, img FROM FlightCatalogue WHERE itemID = ?";
    private String getFlightQuery = "SELECT name, price, availability, img, startTime, endTime, departureCity, destinationCity, stops, seatType, (TIME_TO_SEC(TIMEDIFF(endTime,startTime))/3600) FROM FlightCatalogue WHERE itemID = ?";

    public FlightDAO() throws SQLException {
		connection.setAutoCommit(true);
		st = connection.createStatement();
		readSt = connection.prepareStatement(readQuery);
		updateSt = connection.prepareStatement(updateQuery);
		deleteSt = connection.prepareStatement(deleteQuery);
        filterSt = connection.prepareStatement(filterQuery);
        createSt = connection.prepareStatement(createQuery);
        getFlightItemSt = connection.prepareStatement(getFlightItemQuery);
        getFlightSt = connection.prepareStatement(getFlightQuery);
	}

    //Read Operation:
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

    //Read Operation: Search
    public ArrayList<Flight> fetchFilteredFlights(String filtDepCity, String filtDesCity,String filtDepTime, String filtSeatType) throws SQLException {

        filterSt.setString(1, filtDepCity + "%");
        filterSt.setString(2, filtDesCity + "%");
        filterSt.setString(3, filtSeatType + "%");
        filterSt.setString(4, filtDepTime + "%");
        ResultSet rs = filterSt.executeQuery();
        
        ArrayList<Flight> flights = new ArrayList<Flight>();
        
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
    //Create Operation:
    public void createFlight(String name, double price, int availability, String img, Timestamp startTime, Timestamp endTime, String departureCity, String destinationCity, String stops, String seatType) throws SQLException {
		
		createSt.setString(1, name);
		createSt.setDouble(2, price);
		createSt.setInt(3, availability);
		createSt.setString(4, img);
        createSt.setTimestamp(5, startTime);
        createSt.setTimestamp(6, endTime);
        createSt.setString(7, departureCity);
        createSt.setString(8, destinationCity);
        createSt.setString(9, stops);
        createSt.setString(10, seatType);

        createSt.executeUpdate();
        System.out.println("1 row successfully created");
	}

    //Update Operation:
    public void updateFlight(int itemID, String name, double price, int availability, String img, Timestamp startTime, Timestamp endTime, String departureCity, String destinationCity, String stops, String seatType) throws SQLException{
        
        updateSt.setString(1, name);
        updateSt.setDouble(2, price);
        updateSt.setInt(3, availability);
        updateSt.setString(4, img);
        updateSt.setTimestamp(5, startTime);
        updateSt.setTimestamp(6, endTime);
        updateSt.setString(7, departureCity);
        updateSt.setString(8, destinationCity);
        updateSt.setString(9, stops);
        updateSt.setString(10, seatType);
        updateSt.setInt(11, itemID);

        updateSt.executeUpdate();
        System.out.println("1 row successfully updated");
    }

    //Delete Operation:
    public void deleteFlight(int itemID) throws SQLException{
        
        deleteSt.setInt(1, itemID);

        deleteSt.executeUpdate();
        System.out.println("1 row successfully deleted");
    }

    //Add to Cart: get the item to add to cart
    public Item fetchFlightItem(int itemID) throws SQLException{
        
        getFlightItemSt.setInt(1, itemID);
        ResultSet rs = getFlightItemSt.executeQuery();
        Item flight;
        while (rs.next()) {
            String name = rs.getString(1);
            Double price = rs.getDouble(2);
            int availability = rs.getInt(3);
            String img = rs.getString(4);

            flight = new Item(itemID, name, price, availability, img);
            return flight;
        }

        return null;
    }

    public Flight fetchFlight(int itemID) throws SQLException{
        
        getFlightSt.setInt(1, itemID);
        ResultSet rs = getFlightSt.executeQuery();
        Flight flight;
        while (rs.next()) {
                String name = rs.getString(1);
                Double price = rs.getDouble(2);
                int availability = rs.getInt(3);
                String img = rs.getString(4);
                Timestamp startTime = rs.getTimestamp(5);
                Timestamp endTime = rs.getTimestamp(6);
                String departureCity = rs.getString(7);
                String destinationCity = rs.getString(8);
                String stops = rs.getString(9);
                String seatType = rs.getString(10);
                int hours = rs.getInt(11);

            flight = new Flight(itemID, name, price, availability, img, startTime, endTime, departureCity, destinationCity, hours, stops,seatType);
            return flight;
        }

        return null;
    }



}
