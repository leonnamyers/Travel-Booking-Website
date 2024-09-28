package com;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.iotbay.Dao.DBConnector;
import com.iotbay.Dao.DBManager;
import com.iotbay.Dao.PackageDAO;
import com.iotbay.Model.Package;

public class PackageDAOTest {

<<<<<<< HEAD
    // @Test
    // public void testDatabaseConnectionAndCRUD() {
    //     try {
     
    //         DBConnector dbConnector = new DBConnector();
    //         Connection connection = dbConnector.openConnection();
=======
    DBConnector dbConnector;
    Connection connection;
    DBManager manager;

    @Test
    public void testDatabaseConnectionAndCRUD() {
        try {

            DBConnector dbConnector = new DBConnector();
            Connection connection = dbConnector.openConnection();
>>>>>>> 984bfc53f326671b7534cb88f3e261725a40e90e

    //         assertNotNull(connection, "Failed to connect to the database");

    //         PackageDAO packageDAO = new PackageDAO(connection);

<<<<<<< HEAD
    
    //         System.out.println("Inserting a new package...");
    //         packageDAO.createPackage(
    //             "Sydney Opera House Tour", 
    //             150.00, 
    //             10, 
    //             "http://example.com/images/opera.jpg", 
    //             "A guided tour of the iconic Sydney Opera House.", 
    //             "Discover the architectural marvel of the Sydney Opera House, a UNESCO World Heritage site and a global symbol of modern Australia.", 
    //             "Guided Tour, Harbour Cruise, Evening Performance", 
    //             "Private luxury transfers from Sydney Airport", 
    //             "Daily breakfast at The Star Grand, with an optional gourmet dinner at the Opera Bar", 
    //             "10% discount on all in-house dining", 
    //             "John Doe", 
    //             "1234567890"
    //         );

           
    //         System.out.println("Fetching all packages...");
    //         ArrayList<Package> packages = packageDAO.fetchAllPackages();
    //         assertFalse(packages.isEmpty(), "Package list is empty after insertion");

        
    //         System.out.println("Updating a package...");
    //         packageDAO.updatePackage(
    //             packages.get(0).getItemID(), 
    //             "Sydney Opera House Tour - Updated", 
    //             200.00, 
    //             8, 
    //             "http://example.com/images/opera_updated.jpg", 
    //             "An updated guided tour of the iconic Sydney Opera House.", 
    //             "Updated description.", 
    //             "Updated activities", 
    //             "Updated transportation", 
    //             "Updated dining", 
    //             "Updated special offer", 
    //             "Jane Doe", 
    //             "0987654321"
    //         );

        
    //         System.out.println("Deleting a package...");
    //         packageDAO.deletePackage(packages.get(0).getItemID());
=======
            System.out.println("Inserting a new package...");
            packageDAO.createPackage(
                    "Sydney Opera House Tour",
                    150.00,
                    10,
                    "http://example.com/images/opera.jpg",
                    "A guided tour of the iconic Sydney Opera House.",
                    "Discover the architectural marvel of the Sydney Opera House, a UNESCO World Heritage site and a global symbol of modern Australia.",
                    "Guided Tour, Harbour Cruise, Evening Performance",
                    "Private luxury transfers from Sydney Airport",
                    "Daily breakfast at The Star Grand, with an optional gourmet dinner at the Opera Bar",
                    "10% discount on all in-house dining",
                    "John Doe",
                    "1234567890");

            System.out.println("Fetching all packages...");
            ArrayList<Package> packages = packageDAO.fetchAllPackages();
            assertFalse(packages.isEmpty(), "Package list is empty after insertion");

            System.out.println("Updating a package...");
            packageDAO.updatePackage(
                    packages.get(0).getItemID(),
                    "Sydney Opera House Tour - Updated",
                    200.00,
                    8,
                    "http://example.com/images/opera_updated.jpg",
                    "An updated guided tour of the iconic Sydney Opera House.",
                    "Updated description.",
                    "Updated activities",
                    "Updated transportation",
                    "Updated dining",
                    "Updated special offer",
                    "Jane Doe",
                    "0987654321");

            System.out.println("Deleting a package...");
            packageDAO.deletePackage(packages.get(0).getItemID());
>>>>>>> 984bfc53f326671b7534cb88f3e261725a40e90e

    //         connection.close();
    //     } catch (ClassNotFoundException | SQLException e) {
    //         fail("Exception occurred during database operations: " + e.getMessage());
    //     }
    // }
}
