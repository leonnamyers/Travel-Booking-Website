package com.iotbay.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iotbay.Model.Package;

public class PackageDAO {
    
    private Connection connection;

    // Constructor to initialize the database connection
    public PackageDAO(Connection connection) {
        this.connection = connection;
    }

    // Insert a new package into the database
    public void addPackage(Package pkg) throws SQLException {
        String query = "INSERT INTO Package (name, price, availability, img, description, introduction, activities, transportation, dining, specialOffer, contactName, contactPhone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, pkg.getName());
        statement.setDouble(2, pkg.getPrice());
        statement.setInt(3, pkg.getAvailability());
        statement.setString(4, pkg.getImg());
        statement.setString(5, pkg.getDescription());
        statement.setString(6, pkg.getIntroduction());
        statement.setString(7, pkg.getActivities());
        statement.setString(8, pkg.getTransportation());
        statement.setString(9, pkg.getDining());
        statement.setString(10, pkg.getSpecialOffer());
        statement.setString(11, pkg.getContactName());
        statement.setString(12, pkg.getContactPhone());
        statement.executeUpdate();
        statement.close();
    }

    // Retrieve all packages from the database
    public List<Package> getAllPackages() throws SQLException {
        List<Package> packages = new ArrayList<>();
        String query = "SELECT * FROM Package";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Package pkg = new Package();
            pkg.setItemID(resultSet.getInt("itemID"));
            pkg.setName(resultSet.getString("name"));
            pkg.setPrice(resultSet.getDouble("price"));
            pkg.setAvailability(resultSet.getInt("availability"));
            pkg.setImg(resultSet.getString("img"));
            pkg.setDescription(resultSet.getString("description"));
            pkg.setIntroduction(resultSet.getString("introduction"));
            pkg.setActivities(resultSet.getString("activities"));
            pkg.setTransportation(resultSet.getString("transportation"));
            pkg.setDining(resultSet.getString("dining"));
            pkg.setSpecialOffer(resultSet.getString("specialOffer"));
            pkg.setContactName(resultSet.getString("contactName"));
            pkg.setContactPhone(resultSet.getString("contactPhone"));
            packages.add(pkg);
        }
        resultSet.close();
        statement.close();
        return packages;
    }

    // Update an existing package
    public void updatePackage(Package pkg) throws SQLException {
        String query = "UPDATE Package SET name=?, price=?, availability=?, img=?, description=?, introduction=?, activities=?, transportation=?, dining=?, specialOffer=?, contactName=?, contactPhone=? WHERE itemID=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, pkg.getName());
        statement.setDouble(2, pkg.getPrice());
        statement.setInt(3, pkg.getAvailability());
        statement.setString(4, pkg.getImg());
        statement.setString(5, pkg.getDescription());
        statement.setString(6, pkg.getIntroduction());
        statement.setString(7, pkg.getActivities());
        statement.setString(8, pkg.getTransportation());
        statement.setString(9, pkg.getDining());
        statement.setString(10, pkg.getSpecialOffer());
        statement.setString(11, pkg.getContactName());
        statement.setString(12, pkg.getContactPhone());
        statement.setInt(13, pkg.getItemID());
        statement.executeUpdate();
        statement.close();
    }

    // Delete a package by ID
    public void deletePackage(int itemID) throws SQLException {
        String query = "DELETE FROM Package WHERE itemID=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, itemID);
        statement.executeUpdate();
        statement.close();
    }
}
