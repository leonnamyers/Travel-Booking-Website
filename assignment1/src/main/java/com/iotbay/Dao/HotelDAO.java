package com.iotbay.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import com.iotbay.Model.*;
import java.time.Duration;

public class HotelDAO {
    private Statement st;
	private PreparedStatement readSt;
    private PreparedStatement filterSt;
    private PreparedStatement createSt;
	private PreparedStatement updateSt;
	private PreparedStatement deleteSt;
    private PreparedStatement getHotelSt;
    private PreparedStatement createCustomerHotelSt;
    private PreparedStatement getCustomerHotelSt;
    private PreparedStatement getCustomerHotelItemSt;


    private String readQuery = "SELECT itemID, name, price, availability, img, roomType, roomSize, city, availableBeginDate, availableEndDate From HotelCatalogue ORDER BY name";
    private String filterQuery = "SELECT itemID, name, price, availability, img, roomType, roomSize, city, availableBeginDate, availableEndDate FROM HotelCatalogue where roomType LIKE ? and roomSize LIKE ? and city LIKE ? and availableBeginDate <= ? and availableEndDate >= ? ORDER BY name";
	private String createQuery = "INSERT INTO HotelCatalogue (name, price, availability, img, roomType, roomSize, city, availableBeginDate, availableEndDate) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private String updateQuery = "UPDATE HotelCatalogue SET name=?, price=?, availability=?, img=?, roomType=?, roomSize=?, city=?, availableBeginDate=?, availableEndDate=? WHERE itemID=?";
	private String deleteQuery = "DELETE FROM HotelCatalogue WHERE itemID=?";
    private String getHotelQuery = "SELECT name, price, availability, img, roomType, roomSize, city, availableBeginDate, availableEndDate From HotelCatalogue WHERE itemID = ?";
    private String createCustomerHotelQuery = "INSERT INTO CustomerHotel (itemID, name, price, availability, img, checkInTime, checkOutTime, roomType, roomSize, city) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private String getCustomerHotelQuery = "SELECT name, price, availability, img, checkInTime, checkOutTime, roomType, roomSize, city FROM CustomerHotel WHERE itemID = ?";
    private String getCustomerHotelItemQuery = "SELECT name, price, availability, img FROM CustomerHotel WHERE itemID = ?";
    
    public HotelDAO(Connection connection) throws SQLException {
		connection.setAutoCommit(true);
		st = connection.createStatement();
		readSt = connection.prepareStatement(readQuery);
		updateSt = connection.prepareStatement(updateQuery);
		deleteSt = connection.prepareStatement(deleteQuery);
        filterSt = connection.prepareStatement(filterQuery);
        createSt = connection.prepareStatement(createQuery);
        getHotelSt = connection.prepareStatement(getHotelQuery);
        createCustomerHotelSt = connection.prepareStatement(createCustomerHotelQuery);
        getCustomerHotelSt = connection.prepareStatement(getCustomerHotelQuery);
        getCustomerHotelItemSt = connection.prepareStatement(getCustomerHotelItemQuery);
	}

        // Read Operation: 
    public ArrayList<Hotel> fetchAllHotels() throws SQLException {
        ArrayList<Hotel> hotels = new ArrayList<Hotel>();
        ResultSet rs = readSt.executeQuery();
        while (rs.next()) {
                int itemID = rs.getInt(1);
                String name = rs.getString(2);
                Double price = rs.getDouble(3);
                int availability = rs.getInt(4);
                String img = rs.getString(5);
                String roomType = rs.getString(6);
                String roomSize = rs.getString(7);
                String city = rs.getString(8);
                Date availableBeginDate = rs.getDate(9);
                Date availableEndDate = rs.getDate(10);

                Hotel hotel = new Hotel(itemID, name, price, availability, img, roomType, roomSize, city, availableBeginDate, availableEndDate);
                hotels.add(hotel);
        }
        return hotels;
    }

    public ArrayList<Hotel> fetchFilteredHotel(String filtRoomType, String filtRoomSize, String filtCity, String filtAvailableBeginDate, String filtAvailableEndDate) throws SQLException {
        filterSt.setString(1, filtRoomType + "%");
        filterSt.setString(2, filtRoomSize + "%");
        filterSt.setString(3, filtCity + "%");
        filterSt.setString(4, filtAvailableBeginDate);
        filterSt.setString(4, filtAvailableEndDate);
        ResultSet rs = filterSt.executeQuery();
        
        ArrayList<Hotel> hotels = new ArrayList<Hotel>();
        
        while (rs.next()) {
            int itemID = rs.getInt(1);
            String name = rs.getString(2);
            Double price = rs.getDouble(3);
            int availability = rs.getInt(4);
            String img = rs.getString(5);
            String roomType = rs.getString(6);
            String roomSize = rs.getString(7);
            String city = rs.getString(8);
            Date availableBeginDate = rs.getDate(9);
            Date availableEndDate = rs.getDate(10);

            Hotel hotel = new Hotel(itemID, name, price, availability, img, roomType, roomSize, city, availableBeginDate, availableEndDate);
            hotels.add(hotel);
        }
        return hotels;
    }

    public void createHotel(String name, double price, int availability, String img, String roomType, String roomSize, String city, Date availableBeginDate, Date availableEndDate) throws SQLException {
		createSt.setString(1, name);
		createSt.setDouble(2, price);
		createSt.setInt(3, availability);
		createSt.setString(4, img);
        createSt.setString(5, roomType);
        createSt.setString(6, roomSize);
        createSt.setString(7, city);
        createSt.setDate(8, availableBeginDate);
        createSt.setDate(9, availableEndDate);

        createSt.executeUpdate();
        System.out.println("1 row successfully created");
	}

    public void updateHotel(String name, double price, int availability, String img, String roomType, String roomSize, String city, Date availableBeginDate, Date availableEndDate) throws SQLException{
        updateSt.setString(1, name);
        updateSt.setDouble(2, price);
        updateSt.setInt(3, availability);
        updateSt.setString(4, img);
        updateSt.setString(5, roomType);
        updateSt.setString(6, roomSize);
        updateSt.setString(7, city);
        updateSt.setDate(8, availableBeginDate);
        updateSt.setDate(9, availableEndDate);


        updateSt.executeUpdate();
        System.out.println("1 row successfully updated");
    }

    public void deleteHotel(int itemID) throws SQLException{
        
        deleteSt.setInt(1, itemID);

        deleteSt.executeUpdate();
        System.out.println("1 row successfully deleted");
    }

    public Hotel fetchHotel(int itemID) throws SQLException{
        
        getHotelSt.setInt(1, itemID);
        ResultSet rs = getHotelSt.executeQuery();
        Hotel hotel;
        while (rs.next()) {
            String name = rs.getString(1);
            Double price = rs.getDouble(2);
            int availability = rs.getInt(3);
            String img = rs.getString(4);
            String roomType = rs.getString(5);
            String roomSize = rs.getString(6);
            String city = rs.getString(7);
            Date availableBeginDate = rs.getDate(8);
            Date availableEndDate = rs.getDate(9);

            hotel = new Hotel(itemID, name, price, availability, img, roomType, roomSize, city, availableBeginDate, availableEndDate);
            return hotel;
        }
        return null;
    }

    public void createCustomerHotel(int itemID, String name, double price, int availability, String img, Date checkInTime, Date checkOutTime, String roomType, String roomSize, String city) throws SQLException {
        createCustomerHotelSt.setInt(1, itemID);
        createCustomerHotelSt.setString(1, name);
		createCustomerHotelSt.setDouble(2, price);
		createCustomerHotelSt.setInt(3, availability);
		createCustomerHotelSt.setString(4, img);
        createCustomerHotelSt.setDate(5, checkInTime);
        createCustomerHotelSt.setDate(6, checkOutTime);
        createCustomerHotelSt.setString(7, roomType);
        createCustomerHotelSt.setString(8, roomSize);
        createCustomerHotelSt.setString(9, city);

        createCustomerHotelSt.executeUpdate();
        System.out.println("1 row successfully created");
	}

    public CustomerHotel fetchCustomerHotel(int itemID) throws SQLException{
        getCustomerHotelSt.setInt(1, itemID);
        ResultSet rs = getHotelSt.executeQuery();
        CustomerHotel hotel;
        while (rs.next()) {
            String name = rs.getString(1);
            Double price = rs.getDouble(2);
            int availability = rs.getInt(3);
            String img = rs.getString(4);
            Date checkInTime = rs.getDate(5);
            Date checkOutTime = rs.getDate(6);
            String roomType = rs.getString(7);
            String roomSize = rs.getString(8);
            String city = rs.getString(9);
            

            hotel = new CustomerHotel(checkInTime, checkOutTime, itemID, name, itemID, availability, img, roomType, roomSize, city);
            return hotel;
        }
        return null;
    }

    public Item fetchCustomerHotelItem(int itemID) throws SQLException{
        getCustomerHotelItemSt.setInt(1, itemID);
        ResultSet rs = getHotelSt.executeQuery();
        Item hotelItem;

        while (rs.next()) {
            String name = rs.getString(1);
            Double price = rs.getDouble(2);
            int availability = rs.getInt(3);
            String img = rs.getString(4);

            hotelItem = new Item(itemID, name, price, availability, img);
            return hotelItem;
        }
        return null;
    }
}
