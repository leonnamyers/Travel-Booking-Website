package com.iotbay.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.iotbay.Model.Customer;
import com.iotbay.Model.Order;

public class OrderDAO {

    // Prepared Statements for CRUD operations
    private PreparedStatement createSt;
    private PreparedStatement readSt;
    private PreparedStatement updateSt;
    private PreparedStatement deleteSt;
    private PreparedStatement getOrderSt;

    // SQL Queries
    private String createQuery = "INSERT INTO Orders (customerID, cartID, totalPrice, orderDate) VALUES(?, ?, ?, ?)";
    private String readQuery = "SELECT orderID, customerID, cartID, totalPrice, orderDate FROM Orders";
    private String updateQuery = "UPDATE Orders SET customerID = ?, cartID = ?, totalPrice = ?, orderDate = ? WHERE orderID = ?";
    private String deleteQuery = "DELETE FROM Orders WHERE orderID = ?";
    private String getOrderQuery = "SELECT orderID, customerID, cartID, totalPrice, orderDate FROM Orders WHERE orderID = ?";

    // Constructor
    public OrderDAO(Connection connection) throws SQLException {
        connection.setAutoCommit(true);
        createSt = connection.prepareStatement(createQuery);
        readSt = connection.prepareStatement(readQuery);
        updateSt = connection.prepareStatement(updateQuery);
        deleteSt = connection.prepareStatement(deleteQuery);
        getOrderSt = connection.prepareStatement(getOrderQuery);
    }

    // Create Operation: Place a new order
    public void createOrder(String customerID, double totalPrice, java.sql.Timestamp orderDate) throws SQLException {
        createSt.setString(1, customerID);
        createSt.setDouble(3, totalPrice);
        createSt.setTimestamp(4, orderDate);
        createSt.executeUpdate();
        System.out.println("Order successfully created");
    }

    // Read Operation: Fetch all orders
    public ArrayList<Order> fetchAllOrders() throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        ResultSet rs = readSt.executeQuery();
        while (rs.next()) {
            int orderID = rs.getInt(1);
            String customerID = rs.getString(2);
            int cartID = rs.getInt(3);
            double totalPrice = rs.getDouble(4);
            java.sql.Timestamp orderDate = rs.getTimestamp(5);

            Customer customer = new Customer();
        }
        return orders;
    }

    // Update Operation: Update an order's details
    public void updateOrder(int orderID, String customerID, double totalPrice, java.sql.Timestamp orderDate) throws SQLException {
        updateSt.setString(1, customerID);
        updateSt.setDouble(3, totalPrice);
        updateSt.setTimestamp(4, orderDate);
        updateSt.setInt(5, orderID);
        updateSt.executeUpdate();
        System.out.println("Order successfully updated");
    }

    // Delete Operation: Delete an order
    public void deleteOrder(int orderID) throws SQLException {
        deleteSt.setInt(1, orderID);
        deleteSt.executeUpdate();
        System.out.println("Order successfully deleted");
    }

    // Get single order by ID
    public Order fetchOrder(int orderID) throws SQLException {
        getOrderSt.setInt(1, orderID);
        ResultSet rs = getOrderSt.executeQuery();
        if (rs.next()) {
            String customerID = rs.getString(2);  // Fetching customer email as ID
            int cartID = rs.getInt(3);
            double totalPrice = rs.getDouble(4);
            java.sql.Timestamp orderDate = rs.getTimestamp(5);

        }
        return null;
    }

}