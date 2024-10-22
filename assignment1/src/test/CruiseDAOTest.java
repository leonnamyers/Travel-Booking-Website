package com;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.iotbay.Dao.CruiseDAO;
import com.iotbay.Model.Cruise;

public class CruiseDAOTest {

    private CruiseDAO mockCruiseDAO;
    private ArrayList<Cruise> mockCruises;

    @BeforeEach
    public void setup() throws SQLException {
        mockCruiseDAO = Mockito.mock(CruiseDAO.class);
        mockCruises = new ArrayList<>();
        Cruise baliCruise = new Cruise(1, "Bali Island Cruise", 200.00, 15, "bali.jpg", "Bali Port", "A relaxing cruise to Bali", 5, "2024-12-15", "20% off", "Bali");
        mockCruises.add(baliCruise);

        when(mockCruiseDAO.fetchAllCruises()).thenReturn(mockCruises);
        when(mockCruiseDAO.fetchCruiseById(1)).thenReturn(mockCruises.get(0));
    }

    @Test
    public void testFetchAllCruises() throws SQLException {
        ArrayList<Cruise> result = mockCruiseDAO.fetchAllCruises();
        assertFalse(result.isEmpty());
        assertEquals("Bali Island Cruise", result.get(0).getName());
        verify(mockCruiseDAO).fetchAllCruises();
    }

    @Test
    public void testFetchCruiseById() throws SQLException {
        Cruise result = mockCruiseDAO.fetchCruiseById(1);
        assertNotNull(result);
        assertEquals("Bali Island Cruise", result.getName());
        verify(mockCruiseDAO).fetchCruiseById(1);
    }

    @Test
    public void testCreateCruise() throws SQLException {
        doNothing().when(mockCruiseDAO).createCruise(
            "Great Barrier Reef Cruise", 
            350.00, 
            25, 
            "reef.jpg", 
            "Cairns Port", 
            "Explore the Great Barrier Reef", 
            7, 
            "2024-11-01", 
            "10% off", 
            "Great Barrier Reef"
        );

        mockCruiseDAO.createCruise(
            "Great Barrier Reef Cruise", 
            350.00, 
            25, 
            "reef.jpg", 
            "Cairns Port", 
            "Explore the Great Barrier Reef", 
            7, 
            "2024-11-01", 
            "10% off", 
            "Great Barrier Reef"
        );

        verify(mockCruiseDAO).createCruise(
            "Great Barrier Reef Cruise", 
            350.00, 
            25, 
            "reef.jpg", 
            "Cairns Port", 
            "Explore the Great Barrier Reef", 
            7, 
            "2024-11-01", 
            "10% off", 
            "Great Barrier Reef"
        );
    }

    @Test
    public void testUpdateCruise() throws SQLException {
        doNothing().when(mockCruiseDAO).updateCruise(
            1, 
            "Updated Bali Island Cruise", 
            220.00, 
            12, 
            "updated_bali.jpg", 
            "Updated Bali Port", 
            "Updated relaxing cruise", 
            6, 
            "2024-12-20", 
            "15% off", 
            "Updated Bali"
        );

        mockCruiseDAO.updateCruise(
            1, 
            "Updated Bali Island Cruise", 
            220.00, 
            12, 
            "updated_bali.jpg", 
            "Updated Bali Port", 
            "Updated relaxing cruise", 
            6, 
            "2024-12-20", 
            "15% off", 
            "Updated Bali"
        );

        verify(mockCruiseDAO).updateCruise(
            1, 
            "Updated Bali Island Cruise", 
            220.00, 
            12, 
            "updated_bali.jpg", 
            "Updated Bali Port", 
            "Updated relaxing cruise", 
            6, 
            "2024-12-20", 
            "15% off", 
            "Updated Bali"
        );
    }

    @Test
    public void testDeleteCruise() throws SQLException {
        doNothing().when(mockCruiseDAO).deleteCruise(1);
        mockCruiseDAO.deleteCruise(1);
        verify(mockCruiseDAO).deleteCruise(1);
    }
}
