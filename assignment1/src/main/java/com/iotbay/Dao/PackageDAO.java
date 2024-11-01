package com.iotbay.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.iotbay.Model.Package;

public class PackageDAO {

    private PreparedStatement readSt;
    private PreparedStatement updateSt;
    private PreparedStatement deleteSt;
    private PreparedStatement addSt;

    // SQL Queries
    private String readQuery = "SELECT ITEMID, NAME, PRICE, AVAILABILITY, IMG, DESCRIPTION, INTRODUCTION, ACTIVITIES, TRANSPORTATION, DINING, SPECIALOFFER, CONTACTNAME, CONTACTPHONE FROM PACKAGE";
    private String updateQuery = "UPDATE PACKAGE SET NAME=?, PRICE=?, AVAILABILITY=?, IMG=?, DESCRIPTION=?, INTRODUCTION=?, ACTIVITIES=?, TRANSPORTATION=?, DINING=?, SPECIALOFFER=?, CONTACTNAME=?, CONTACTPHONE=? WHERE ITEMID=?";
    private String deleteQuery = "DELETE FROM PACKAGE WHERE ITEMID = ?";
    private String addQuery = "INSERT INTO PACKAGE (NAME, PRICE, AVAILABILITY, IMG, DESCRIPTION, INTRODUCTION, ACTIVITIES, TRANSPORTATION, DINING, SPECIALOFFER, CONTACTNAME, CONTACTPHONE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


    // Constructor with Connection
    public PackageDAO(Connection connection) throws SQLException {
        connection.setAutoCommit(true);
        readSt = connection.prepareStatement(readQuery);
        updateSt = connection.prepareStatement(updateQuery);
        deleteSt = connection.prepareStatement(deleteQuery);
        addSt = connection.prepareStatement(addQuery);
    }

    // Fetch all packages
    public ArrayList<Package> fetchAllPackages() throws SQLException {
        ArrayList<Package> packages = new ArrayList<>();
        ResultSet rs = readSt.executeQuery();

        while (rs.next()) {
            int itemID = rs.getInt(1);
            String name = rs.getString(2);
            Double price = rs.getDouble(3);
            int availability = rs.getInt(4);
            String img = rs.getString(5);
            String description = rs.getString(6);
            String introduction = rs.getString(7);
            String activities = rs.getString(8);
            String transportation = rs.getString(9);
            String dining = rs.getString(10);
            String specialOffer = rs.getString(11);
            String contactName = rs.getString(12);
            String contactPhone = rs.getString(13);

            Package pkg = new Package(itemID, name, price, availability, img, description, introduction, activities, transportation, dining, specialOffer, contactName, contactPhone);
            packages.add(pkg);
        }

        return packages;
    }

    // Fetch a package by its itemID
    public Package fetchPackageById(int itemID) throws SQLException {
        String query = "SELECT ITEMID, NAME, PRICE, AVAILABILITY, IMG, DESCRIPTION, INTRODUCTION, ACTIVITIES, TRANSPORTATION, DINING, SPECIALOFFER, CONTACTNAME, CONTACTPHONE FROM PACKAGE WHERE ITEMID = ?";
    
        PreparedStatement stmt = readSt.getConnection().prepareStatement(query);
        stmt.setInt(1, itemID);
        ResultSet rs = stmt.executeQuery();
    
        if (rs.next()) {
            String name = rs.getString("NAME");
            Double price = rs.getDouble("PRICE");
            int availability = rs.getInt("AVAILABILITY");
            String img = rs.getString("IMG");
            String description = rs.getString("DESCRIPTION");
            String introduction = rs.getString("INTRODUCTION");
            String activities = rs.getString("ACTIVITIES");
            String transportation = rs.getString("TRANSPORTATION");
            String dining = rs.getString("DINING");
            String specialOffer = rs.getString("SPECIALOFFER");
            String contactName = rs.getString("CONTACTNAME");
            String contactPhone = rs.getString("CONTACTPHONE");
    
            return new Package(itemID, name, price, availability, img, description, introduction, activities, transportation, dining, specialOffer, contactName, contactPhone);
        } else {
            return null; // No package found for the given itemID
        }
    }
    

    // Add a new package
    public void createPackage(String name, double price, int availability, String img, String description, String introduction, String activities, String transportation, String dining, String specialOffer, String contactName, String contactPhone) throws SQLException {
        addSt.setString(1, name);
        addSt.setDouble(2, price);
        addSt.setInt(3, availability);
        addSt.setString(4, img);
        addSt.setString(5, description);
        addSt.setString(6, introduction);
        addSt.setString(7, activities);
        addSt.setString(8, transportation);
        addSt.setString(9, dining);
        addSt.setString(10, specialOffer);
        addSt.setString(11, contactName);
        addSt.setString(12, contactPhone);
    
        addSt.executeUpdate();
        System.out.println("1 row successfully created");
    }

    // Update a package
    public void updatePackage(int itemID, String name, double price, int availability, String img, String description, String introduction, String activities, String transportation, String dining, String specialOffer, String contactName, String contactPhone) throws SQLException {
        updateSt.setString(1, name);
        updateSt.setDouble(2, price);
        updateSt.setInt(3, availability);
        updateSt.setString(4, img);
        updateSt.setString(5, description);
        updateSt.setString(6, introduction);
        updateSt.setString(7, activities);
        updateSt.setString(8, transportation);
        updateSt.setString(9, dining);
        updateSt.setString(10, specialOffer);
        updateSt.setString(11, contactName);
        updateSt.setString(12, contactPhone);
        updateSt.setInt(13, itemID);

        updateSt.executeUpdate();
        System.out.println("1 row successfully updated");
    }

    // Delete a package
    public void deletePackage(int itemID) throws SQLException {
        deleteSt.setInt(1, itemID);
        deleteSt.executeUpdate();
        System.out.println("1 row successfully deleted");
    }
}
