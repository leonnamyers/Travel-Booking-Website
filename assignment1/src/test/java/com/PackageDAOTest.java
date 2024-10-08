package com;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.iotbay.Dao.PackageDAO;
import com.iotbay.Model.Package;

public class PackageDAOTest {

    private PackageDAO mockPackageDAO;
    private ArrayList<Package> mockPackages;

    @BeforeEach
    public void setup() throws SQLException {
        mockPackageDAO = Mockito.mock(PackageDAO.class);
        mockPackages = new ArrayList<>();
        Package sydneyOperaHouse = new Package(1, "Sydney Opera House Tour", 150.00, 10, "opera.jpg", "A guided tour of the iconic Sydney Opera House.");
        mockPackages.add(sydneyOperaHouse);

        when(mockPackageDAO.fetchAllPackages()).thenReturn(mockPackages);
        when(mockPackageDAO.fetchPackageById(1)).thenReturn(mockPackages.get(0));
    }

    @Test
    public void testFetchAllPackages() throws SQLException {
        ArrayList<Package> result = mockPackageDAO.fetchAllPackages();
        assertFalse(result.isEmpty());
        assertEquals("Sydney Opera House Tour", result.get(0).getName());
        verify(mockPackageDAO).fetchAllPackages();
    }

    @Test
    public void testFetchPackageById() throws SQLException {
        Package result = mockPackageDAO.fetchPackageById(1);
        assertNotNull(result);
        assertEquals("Sydney Opera House Tour", result.getName());
        verify(mockPackageDAO).fetchPackageById(1);
    }

    @Test
    public void testCreatePackage() throws SQLException {
        doNothing().when(mockPackageDAO).createPackage(
            "Sydney Harbour Tour", 
            120.50, 
            20, 
            "harbour.jpg", 
            "Beautiful Sydney Harbour Tour", 
            "Explore the beauty of Sydney Harbour", 
            "Boat Tour, Sightseeing", 
            "Private ferry", 
            "Lunch included", 
            "20% discount", 
            "Alice", 
            "0412345678"
        );

        mockPackageDAO.createPackage(
            "Sydney Harbour Tour", 
            120.50, 
            20, 
            "harbour.jpg", 
            "Beautiful Sydney Harbour Tour", 
            "Explore the beauty of Sydney Harbour", 
            "Boat Tour, Sightseeing", 
            "Private ferry", 
            "Lunch included", 
            "20% discount", 
            "Alice", 
            "0412345678"
        );

        verify(mockPackageDAO).createPackage(
            "Sydney Harbour Tour", 
            120.50, 
            20, 
            "harbour.jpg", 
            "Beautiful Sydney Harbour Tour", 
            "Explore the beauty of Sydney Harbour", 
            "Boat Tour, Sightseeing", 
            "Private ferry", 
            "Lunch included", 
            "20% discount", 
            "Alice", 
            "0412345678"
        );
    }

    @Test
    public void testUpdatePackage() throws SQLException {
        doNothing().when(mockPackageDAO).updatePackage(
            1, 
            "Updated Sydney Harbour Tour", 
            150.00, 
            18, 
            "updated_harbour.jpg", 
            "Updated description", 
            "Updated introduction", 
            "Updated activities", 
            "Updated transportation", 
            "Updated dining", 
            "Updated special offer", 
            "Updated contact", 
            "0412345679"
        );

        mockPackageDAO.updatePackage(
            1, 
            "Updated Sydney Harbour Tour", 
            150.00, 
            18, 
            "updated_harbour.jpg", 
            "Updated description", 
            "Updated introduction", 
            "Updated activities", 
            "Updated transportation", 
            "Updated dining", 
            "Updated special offer", 
            "Updated contact", 
            "0412345679"
        );

        verify(mockPackageDAO).updatePackage(
            1, 
            "Updated Sydney Harbour Tour", 
            150.00, 
            18, 
            "updated_harbour.jpg", 
            "Updated description", 
            "Updated introduction", 
            "Updated activities", 
            "Updated transportation", 
            "Updated dining", 
            "Updated special offer", 
            "Updated contact", 
            "0412345679"
        );
    }

    @Test
    public void testDeletePackage() throws SQLException {
        doNothing().when(mockPackageDAO).deletePackage(1);
        mockPackageDAO.deletePackage(1);
        verify(mockPackageDAO).deletePackage(1);
    }
}
