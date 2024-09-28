package com.iotbay.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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


    private String readQuery = "SELECT itemID, name, price, availability, img, roomType, roomSize, city, availableBeginDate, availableEndDate From HotelCatalogue";
    private String filterQuery = "SELECT itemID, name, price, availability, img, roomType, roomSize, city, availableBeginDate, availableEndDate FROM HotelCatalogue where roomType LIKE ? and roomSize LIKE ? and city LIKE ? and availableBeginDate < ? and availableEndDate > ?";
	private String createQuery = "INSERT INTO HotelCatalogue (name, price, availability, img, roomType, roomSIze, city, availableBeginDate, availableEndDate) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private String updateQuery = "UPDATE HotelCatalogue SET name=?, price=?, availability=?, img=?, roomType=?, roomSIze=?, city=?, availableBeginDate=?, availableEndDate=? WHERE itemID=?";
	private String deleteQuery = "DELETE FROM HotelCatalogue WHERE itemID=?";
    private String getHotelQuery = "SELECT itemID, name, price, availability, img, roomType, roomSize, city, availableBeginDate, availableEndDate From HotelCatalogue WHERE itemID = ?";
    private String createCustomerHotelQuery = "INSERT INTO CustomerHotel (itemID, name, price, availability, img, checkInTime, checkOutTime, roomType, roomSIze, city) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private String getCustomerHotelQuery = "SELECT itemID, name, price, availability, img, checkInTime, checkOutTime, roomType, roomSize, city, DATEDIFF(checkOutTime,checkInTime) FROM CustomerHotel WHERE itemID = ?";
    
    
}
