package com.iotbay.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.iotbay.Model.Cruise;

public class CruiseDAO {

    private PreparedStatement readSt;
    private PreparedStatement updateSt;
    private PreparedStatement deleteSt;
    private PreparedStatement addSt;

    // SQL Queries for Cruise
    private String readQuery = "SELECT ITEMID, NAME, PRICE, AVAILABILITY, IMG, PORT, DESCRIPTION, DURATION, DEPARTUREDATE, SPECIALOFFER, LOCATION FROM CRUISE";
    private String updateQuery = "UPDATE CRUISE SET NAME=?, PRICE=?, AVAILABILITY=?, IMG=?, PORT=?, DESCRIPTION=?, DURATION=?, DEPARTUREDATE=?, SPECIALOFFER=?, LOCATION=? WHERE ITEMID=?";
    private String deleteQuery = "DELETE FROM CRUISE WHERE ITEMID = ?";
    private String addQuery = "INSERT INTO CRUISE (NAME, PRICE, AVAILABILITY, IMG, PORT, DESCRIPTION, DURATION, DEPARTUREDATE, SPECIALOFFER, LOCATION) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    // Constructor with Connection
    public CruiseDAO(Connection connection) throws SQLException {
        connection.setAutoCommit(true);
        readSt = connection.prepareStatement(readQuery);
        updateSt = connection.prepareStatement(updateQuery);
        deleteSt = connection.prepareStatement(deleteQuery);
        addSt = connection.prepareStatement(addQuery);
    }

    // Fetch all cruises
    public ArrayList<Cruise> fetchAllCruises() throws SQLException {
        ArrayList<Cruise> cruises = new ArrayList<>();
        ResultSet rs = readSt.executeQuery();

        while (rs.next()) {
            int itemID = rs.getInt(1);
            String name = rs.getString(2);
            double price = rs.getDouble(3);
            int availability = rs.getInt(4);
            String img = rs.getString(5);
            String port = rs.getString(6);
            String description = rs.getString(7);
            int duration = rs.getInt(8);
            String departureDate = rs.getString(9);
            String specialOffer = rs.getString(10);
            String location = rs.getString(11);

            Cruise cruise = new Cruise(itemID, name, price, availability, img, port, description, duration, departureDate, specialOffer, location);
            cruises.add(cruise);
        }

        return cruises;
    }

    // Fetch a cruise by its itemID
    public Cruise fetchCruiseById(int itemID) throws SQLException {
        String query = "SELECT ITEMID, NAME, PRICE, AVAILABILITY, IMG, PORT, DESCRIPTION, DURATION, DEPARTUREDATE, SPECIALOFFER, LOCATION FROM CRUISE WHERE ITEMID = ?";

        PreparedStatement stmt = readSt.getConnection().prepareStatement(query);
        stmt.setInt(1, itemID);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String name = rs.getString("NAME");
            double price = rs.getDouble("PRICE");
            int availability = rs.getInt("AVAILABILITY");
            String img = rs.getString("IMG");
            String port = rs.getString("PORT");
            String description = rs.getString("DESCRIPTION");
            int duration = rs.getInt("DURATION");
            String departureDate = rs.getString("DEPARTUREDATE");
            String specialOffer = rs.getString("SPECIALOFFER");
            String location = rs.getString("LOCATION");

            return new Cruise(itemID, name, price, availability, img, port, description, duration, departureDate, specialOffer, location);
        } else {
            return null; // No cruise found for the given itemID
        }
    }

    // Add a new cruise
    public void createCruise(String name, double price, int availability, String img, String port, String description, int duration, String departureDate, String specialOffer, String location) throws SQLException {
        addSt.setString(1, name);
        addSt.setDouble(2, price);
        addSt.setInt(3, availability);
        addSt.setString(4, img);
        addSt.setString(5, port);
        addSt.setString(6, description);
        addSt.setInt(7, duration);
        addSt.setString(8, departureDate);
        addSt.setString(9, specialOffer);
        addSt.setString(10, location);

        addSt.executeUpdate();
        System.out.println("1 row successfully created");
    }

    // Update an existing cruise
    public void updateCruise(int itemID, String name, double price, int availability, String img, String port, String description, int duration, String departureDate, String specialOffer, String location) throws SQLException {
        updateSt.setString(1, name);
        updateSt.setDouble(2, price);
        updateSt.setInt(3, availability);
        updateSt.setString(4, img);
        updateSt.setString(5, port);
        updateSt.setString(6, description);
        updateSt.setInt(7, duration);
        updateSt.setString(8, departureDate);
        updateSt.setString(9, specialOffer);
        updateSt.setString(10, location);
        updateSt.setInt(11, itemID);

        updateSt.executeUpdate();
        System.out.println("1 row successfully updated");
    }

    // Delete a cruise by its itemID
    public void deleteCruise(int itemID) throws SQLException {
        deleteSt.setInt(1, itemID);
        deleteSt.executeUpdate();
        System.out.println("1 row successfully deleted");
    }

    // Search cruises by port (目的地)
    public ArrayList<Cruise> searchCruisesByPort(String port) throws SQLException {
        String searchQuery = "SELECT ITEMID, NAME, PRICE, AVAILABILITY, IMG, PORT, DESCRIPTION, DURATION, DEPARTUREDATE, SPECIALOFFER, LOCATION FROM CRUISE WHERE PORT = ?";
        PreparedStatement stmt = readSt.getConnection().prepareStatement(searchQuery);
        stmt.setString(1, port);

        ResultSet rs = stmt.executeQuery();
        ArrayList<Cruise> filteredCruises = new ArrayList<>();

        while (rs.next()) {
            int itemID = rs.getInt("ITEMID");
            String name = rs.getString("NAME");
            double price = rs.getDouble("PRICE");
            int availability = rs.getInt("AVAILABILITY");
            String img = rs.getString("IMG");
            String description = rs.getString("DESCRIPTION");
            int duration = rs.getInt("DURATION");
            String departureDate = rs.getString("DEPARTUREDATE");
            String specialOffer = rs.getString("SPECIALOFFER");
            String location = rs.getString("LOCATION");

            Cruise cruise = new Cruise(itemID, name, price, availability, img, port, description, duration, departureDate, specialOffer, location);
            filteredCruises.add(cruise);
        }

        return filteredCruises;
    }

    // Search cruises by departure date (按日期搜索)
    public ArrayList<Cruise> searchCruisesByDate(String startDate, String endDate) throws SQLException {
        String searchQuery = "SELECT ITEMID, NAME, PRICE, AVAILABILITY, IMG, PORT, DESCRIPTION, DURATION, DEPARTUREDATE, SPECIALOFFER, LOCATION FROM CRUISE WHERE DEPARTUREDATE BETWEEN ? AND ?";
        PreparedStatement stmt = readSt.getConnection().prepareStatement(searchQuery);
        stmt.setString(1, startDate);
        stmt.setString(2, endDate);

        ResultSet rs = stmt.executeQuery();
        ArrayList<Cruise> filteredCruises = new ArrayList<>();

        while (rs.next()) {
            int itemID = rs.getInt("ITEMID");
            String name = rs.getString("NAME");
            double price = rs.getDouble("PRICE");
            int availability = rs.getInt("AVAILABILITY");
            String img = rs.getString("IMG");
            String port = rs.getString("PORT");
            String description = rs.getString("DESCRIPTION");
            int duration = rs.getInt("DURATION");
            String departureDate = rs.getString("DEPARTUREDATE");
            String specialOffer = rs.getString("SPECIALOFFER");
            String location = rs.getString("LOCATION");

            Cruise cruise = new Cruise(itemID, name, price, availability, img, port, description, duration, departureDate, specialOffer, location);
            filteredCruises.add(cruise);
        }

        return filteredCruises;
    }
}
