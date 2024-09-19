package com.iotbay.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iotbay.Model.Package;

public class PackageDAO {

    private PreparedStatement readSt;
    private PreparedStatement updateSt;
    private PreparedStatement deleteSt;
    private PreparedStatement addSt;

    private String readQuery = "SELECT * FROM Package";
    private String updateQuery = "UPDATE Package SET name=?, price=?, availability=?, img=?, description=?, introduction=?, activities=?, transportation=?, dining=?, specialOffer=?, contactName=?, contactPhone=? WHERE itemID=?";
    private String deleteQuery = "DELETE FROM Package WHERE itemID = ?";
    private String addQuery = "INSERT INTO Package (name, price, availability, img, description, introduction, activities, transportation, dining, specialOffer, contactName, contactPhone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private Connection connection;

    public PackageDAO(Connection connection) throws SQLException {
        this.connection = connection;
        connection.setAutoCommit(true);
        readSt = connection.prepareStatement(readQuery);
        updateSt = connection.prepareStatement(updateQuery);
        deleteSt = connection.prepareStatement(deleteQuery);
        addSt = connection.prepareStatement(addQuery);
    }

    // Method to retrieve all packages
    public List<Package> getAllPackages() throws SQLException {
        List<Package> packages = new ArrayList<>();
        ResultSet resultSet = readSt.executeQuery();

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
        return packages;
    }

    // Method to add a new package
    public void addPackage(Package pkg) throws SQLException {
        addSt.setString(1, pkg.getName());
        addSt.setDouble(2, pkg.getPrice());
        addSt.setInt(3, pkg.getAvailability());
        addSt.setString(4, pkg.getImg());
        addSt.setString(5, pkg.getDescription());
        addSt.setString(6, pkg.getIntroduction());
        addSt.setString(7, pkg.getActivities());
        addSt.setString(8, pkg.getTransportation());
        addSt.setString(9, pkg.getDining());
        addSt.setString(10, pkg.getSpecialOffer());
        addSt.setString(11, pkg.getContactName());
        addSt.setString(12, pkg.getContactPhone());
        addSt.executeUpdate();
    }

    // Method to update a package
    public void updatePackage(Package pkg) throws SQLException {
        updateSt.setString(1, pkg.getName());
        updateSt.setDouble(2, pkg.getPrice());
        updateSt.setInt(3, pkg.getAvailability());
        updateSt.setString(4, pkg.getImg());
        updateSt.setString(5, pkg.getDescription());
        updateSt.setString(6, pkg.getIntroduction());
        updateSt.setString(7, pkg.getActivities());
        updateSt.setString(8, pkg.getTransportation());
        updateSt.setString(9, pkg.getDining());
        updateSt.setString(10, pkg.getSpecialOffer());
        updateSt.setString(11, pkg.getContactName());
        updateSt.setString(12, pkg.getContactPhone());
        updateSt.setInt(13, pkg.getItemID());
        updateSt.executeUpdate();
    }

    // Method to delete a package
    public void deletePackage(int itemID) throws SQLException {
        deleteSt.setInt(1, itemID);
        deleteSt.executeUpdate();
    }
}
