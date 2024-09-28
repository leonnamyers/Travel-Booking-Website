package com;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.iotbay.Dao.DBConnector;
import com.iotbay.Dao.PackageDAO;
import com.iotbay.Model.Package;

public class PackageDAOTest {
    // private DBConnector connector;
    // private Connection conn;
    // private PackageDAO packageDAO;


    // public PackageDAOTest() throws ClassNotFoundException, SQLException {
    //     connector = new DBConnector();
    //     conn = connector.openConnection();
    //     packageDAO = new PackageDAO(conn);
    // }

    // // Set up before each test
    // @BeforeEach
    // public void setup() throws SQLException {
    //     conn.setAutoCommit(false);  // Optional: Turn off auto-commit for testing.
    // }

    // // Test fetching all packages
    // @Test
    // public void testFetchAllPackages() throws SQLException {
    //     ArrayList<Package> allPackages = packageDAO.fetchAllPackages();
    //     assertFalse(allPackages.isEmpty(), "Package list should not be empty.");
    // }

    // // Test fetching a package by ID
    // @Test
    // public void testFetchPackageById() throws SQLException {
    //     Package pkg = packageDAO.fetchPackageById(35);  // Assuming an ID of 1 exists for testing
    //     assertNotNull(pkg, "Package should not be null.");
    //     assertEquals(pkg.getName(), "Sydney Opera House Tour");  // Adjust based on your data
    // }

    // // Test creating a new package with 12 parameters
    // @Test
    // public void testCreatePackage() throws SQLException {
    //     packageDAO.createPackage(
    //             "Sydney Harbour Tour", 120.50, 20, 
    //             "harbour.jpg", "Beautiful Sydney Harbour Tour", 
    //             "Explore the beauty of Sydney Harbour", 
    //             "Boat Tour, Sightseeing", 
    //             "Private ferry", "Lunch included", 
    //             "20% discount", "Alice", "0412345678");

    //     ArrayList<Package> allPackages = packageDAO.fetchAllPackages();
    //     assertTrue(allPackages.stream().anyMatch(pkg -> pkg.getName().equals("Sydney Harbour Tour")), 
    //                "Package should be successfully created.");
    // }

    // // Test updating a package with 13 parameters
    // @Test
    // public void testUpdatePackage() throws SQLException {
    //     ArrayList<Package> allPackages = packageDAO.fetchAllPackages();
    //     if (!allPackages.isEmpty()) {
    //         Package pkg = allPackages.get(0);
    //         packageDAO.updatePackage(pkg.getItemID(),
    //                 "Updated Sydney Harbour Tour", 150.00, 18, 
    //                 "updated_harbour.jpg", "Updated description", 
    //                 "Updated introduction", "Updated activities", 
    //                 "Updated transportation", "Updated dining", 
    //                 "Updated special offer", "Updated contact", "0412345679");

    //         Package updatedPackage = packageDAO.fetchPackageById(pkg.getItemID());
    //         assertEquals(updatedPackage.getName(), "Updated Sydney Harbour Tour", "Package name should be updated.");
    //         assertEquals(updatedPackage.getPrice(), 150.00, "Package price should be updated.");
    //     } else {
    //         fail("No packages found to update.");
    //     }
    // }

    // // Test deleting a package
    // @Test
    // public void testDeletePackage() throws SQLException {
    //     // Create a test package to delete
    //     packageDAO.createPackage(
    //             "Package to Delete", 100.00, 5, 
    //             "delete_img.jpg", "Delete this package", 
    //             "Test introduction", "Test activities", 
    //             "Test transportation", "Test dining", 
    //             "Test special offer", "Test contact", "0412345678");

    //     ArrayList<Package> allPackages = packageDAO.fetchAllPackages();
    //     Package lastPackage = allPackages.get(allPackages.size() - 1);  // Assuming last package is the one we created
    //     packageDAO.deletePackage(lastPackage.getItemID());

    //     Package deletedPackage = packageDAO.fetchPackageById(lastPackage.getItemID());
    //     assertNull(deletedPackage, "Package should be successfully deleted.");
    // }
}
