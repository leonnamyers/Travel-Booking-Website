package com.iotbay;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.iotbay.Dao.DBConnector;

import com.iotbay.Dao.HotelDAO;
import com.iotbay.Model.Flight;
import com.iotbay.Model.Hotel;
import com.iotbay.Model.Customer;
import com.iotbay.Model.CustomerHotel;
import com.iotbay.Model.Item;

public class HotelDAOTest {


    private HotelDAO hotelDAOManager;
    private ArrayList<Hotel> mockHotelList;
    private ArrayList<CustomerHotel> mockCustomerHotels;

    @BeforeEach
    public void setup() throws SQLException {
        hotelDAOManager = Mockito.mock(HotelDAO.class);
        mockHotelList = new ArrayList<Hotel>();
        mockCustomerHotels = new ArrayList<CustomerHotel>();
        mockHotelList.add(
            new Hotel(
            1, 
            "Tranquil Oasis", 
            213, 
            50, 
            "TranquilOasis.jpg", 
            "Double", 
            "2 people", 
            "Christmas Island", 
            Date.valueOf("2024-01-01"), 
            Date.valueOf("2026-12-31"))
        );
        mockHotelList.add(new Hotel(
            2, 
            "Tranquil Oasis", 
            240, 
            50, 
            "TranquilOasis.jpg", 
            "Queen", 
            "2 people", 
            "Christmas Island", 
            Date.valueOf("2024-01-01"), 
            Date.valueOf("2026-12-31")));

        mockHotelList.add(new Hotel(
            3, 
            "Amour Villa", 
            1059, 
            50, 
            "AmourVilla.jpg", 
            "Executive Suite", 
            "2 people", 
            "Brisbane", 
            Date.valueOf("2024-01-01"), 
            Date.valueOf("2026-12-31")));

        mockHotelList.add(new Hotel(
            4, 
            "Prince Hotel", 
            375, 
            50, 
            "PrinceHotel.jpg", 
            "Single", 
            "1 person", 
            "Melbourne", 
            Date.valueOf("2024-01-01"), 
            Date.valueOf("2026-12-31")));

        mockHotelList.add(new Hotel(
            5, 
            "Beach Life", 
            150, 
            50, 
            "BeachLife.jpg", 
            "Triple", 
            "3 people", 
            "Christmas Island", 
            Date.valueOf("2024-01-01"), 
            Date.valueOf("2026-12-31")));

        mockCustomerHotels.add(
            new CustomerHotel(
                Date.valueOf("2024-10-11"), 
                Date.valueOf("2024-10-12"), 
                1, 
                "Prince Hotel", 
                375, 
                50, 
                "PrinceHotel.jpg", 
                "Single", 
                "1 person", 
                "Melbourne")
        );
        mockCustomerHotels.add(
            new CustomerHotel(
                Date.valueOf("2024-11-23"), 
                Date.valueOf("2024-11-27"), 
                2, 
                "Prince Hotel", 
                375*5, 
                50, 
                "PrinceHotel.jpg", 
                "Single", 
                "1 person", 
                "Melbourne")
        );
        mockCustomerHotels.add(
            new CustomerHotel(
                Date.valueOf("2024-12-07"), 
                Date.valueOf("2024-12-08"), 
                3, 
                "Tranquil Oasis", 
                240, 
                50, 
                "TranquilOasis.jpg", 
                "Queen", 
                "2 people", 
                "Christmas Island")
        );
        


        when(hotelDAOManager.fetchAllHotels()).thenReturn(mockHotelList);
    }

    //Add to cart
    private Item mockFetchItem(int id) throws SQLException{
        when(hotelDAOManager.fetchCustomerHotelItem(id)).thenReturn(mockHotelList.get(id-1));
        Item testHotel = hotelDAOManager.fetchCustomerHotelItem(id);
        return testHotel;
    }

    //Fecth hotel
    @Test
    public CustomerHotel mockFecth(int id) throws SQLException {
        when(hotelDAOManager.fetchCustomerHotel(id)).thenReturn(mockCustomerHotels.get(id-1));
        CustomerHotel testCustomerHotel = hotelDAOManager.fetchCustomerHotel(id);
        return testCustomerHotel;
    }

    //Read
    @Test
    public void readAllHotel() throws SQLException {
        ArrayList<Hotel> allHotels = hotelDAOManager.fetchAllHotels();
        assertEquals(allHotels.size(), 5);
        
    }

    //Add to cart
    @Test
    public void fetchHotelItem() throws SQLException {
        Item testHotel = mockFetchItem(3);
        assertNotNull(testHotel);
        assertEquals(3, testHotel.getItemID());
        assertEquals("Amour Villa", testHotel.getName());
        assertEquals(1059, testHotel.getPrice());
    }

    //Read one hotel
    @Test
    public void fetchHotel() throws SQLException {
        CustomerHotel testHotel = mockFecth(2);
        assertEquals(2, testHotel.getItemID());
        assertEquals("Prince Hotel", testHotel.getName());
        assertEquals(375*5, testHotel.getPrice());
        assertEquals(Date.valueOf("2024-11-23"), testHotel.getCheckInTime());
    }

    //Create
    @Test
    public void createHotels() throws SQLException {

        doNothing().when(hotelDAOManager).createHotel(
            "Golden Highway", 
            423, 
            75, 
            "imageYetToCome.jpg", 
            "Queen", 
            "2 people", 
            "Gold Coast", 
            Date.valueOf("2025-01-02"), 
            Date.valueOf("2027-01-03"));
        hotelDAOManager.createHotel(
            "Golden Highway", 
            423, 
            75, 
            "imageYetToCome.jpg", 
            "Queen", 
            "2 people", 
            "Gold Coast", 
            Date.valueOf("2025-01-02"), 
            Date.valueOf("2027-01-03"));

        verify(hotelDAOManager).createHotel(
            "Golden Highway", 
            423, 
            75, 
            "imageYetToCome.jpg", 
            "Queen", 
            "2 people", 
            "Gold Coast", 
            Date.valueOf("2025-01-02"), 
            Date.valueOf("2027-01-03")
        );
    }

    //Update
    @Test
    public void updateHotels() throws SQLException {

        doNothing().when(hotelDAOManager).updateHotel(
            5,
            "Golden Highway", 
            423, 
            75, 
            "imageYetToCome.jpg", 
            "Queen", 
            "2 people", 
            "Gold Coast", 
            Date.valueOf("2025-01-02"), 
            Date.valueOf("2027-01-03"));

        hotelDAOManager.updateHotel(
            5,
            "Golden Highway", 
            423, 
            75, 
            "imageYetToCome.jpg", 
            "Queen", 
            "2 people", 
            "Gold Coast", 
            Date.valueOf("2025-01-02"), 
            Date.valueOf("2027-01-03")
        );
        verify(hotelDAOManager).updateHotel(
            5,
            "Golden Highway", 
            423, 
            75, 
            "imageYetToCome.jpg", 
            "Queen", 
            "2 people", 
            "Gold Coast", 
            Date.valueOf("2025-01-02"), 
            Date.valueOf("2027-01-03")
        );
    }

    //Delete
    @Test
    public void deleteHotels() throws SQLException {
        doNothing().when(hotelDAOManager).deleteHotel(3);
        hotelDAOManager.deleteHotel(3);
        verify(hotelDAOManager).deleteHotel(3);
    }

    private int hoursDiff(Timestamp beginTime, Timestamp endTime)
    {
        long milliseconds1 = beginTime.getTime();
        long milliseconds2 = endTime.getTime();

        long diff = milliseconds2 - milliseconds1;
        int diffHours = (int)(diff / (60 * 60 * 1000));

        return diffHours;
    }

}

