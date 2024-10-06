package com.iotbay.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.iotbay.Model.Order;

public class OrderDAO {

    // Prepared Statements for CRUD operations
    private PreparedStatement createSt;
    private PreparedStatement readSt;
    private PreparedStatement updateSt;
    private PreparedStatement deleteSt;
    private PreparedStatement getOrderSt;

    // SQL Queries
    private String createQuery = "INSERT INTO Orders (customerID, totalPrice, orderDate, destination, departureDate, returnDate, seatType) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private String readQuery = "SELECT orderID, customerID, totalPrice, orderDate, destination, departureDate, returnDate, seatType FROM Orders";
    private String deleteQuery = "DELETE FROM Orders WHERE orderID = ?";
    private String getOrderQuery = "SELECT orderID, customerID, totalPrice, orderDate, destination, departureDate, returnDate, seatType FROM Orders WHERE orderID = ?";
    private String updateQuery = "UPDATE Orders SET firstName = ?, lastName = ?, email = ?, destination = ?, departureDate = ?, returnDate = ?, passengers = ?, seatType = ?, totalPrice = ?, orderDate = ? WHERE orderID = ?";

    private Connection connection;

    // Constructor
    public OrderDAO(Connection connection) throws SQLException {
        this.connection = connection;
        connection.setAutoCommit(true);
        createSt = connection.prepareStatement(createQuery);
        readSt = connection.prepareStatement(readQuery);
        updateSt = connection.prepareStatement(updateQuery);
        deleteSt = connection.prepareStatement(deleteQuery);
        getOrderSt = connection.prepareStatement(getOrderQuery);
    }

    public OrderDAO() {
        
    }

    // Begin transaction
    public void beginTransaction() throws SQLException {
        if (connection != null) {
            connection.setAutoCommit(false);
        }
    }

    // Commit transaction
    public void commitTransaction() throws SQLException {
        if (connection != null) {
            connection.commit();
            connection.setAutoCommit(true);
        }
    }

    // Rollback transaction
    public void rollbackTransaction() throws SQLException {
        if (connection != null) {
            connection.rollback();
            connection.setAutoCommit(true);
        }
    }

    // Create Operation
    public void createOrder(String customerID, double totalPrice, Timestamp orderDate, String destination, String departureDate, String returnDate, String seatType) throws SQLException {
        createSt.setString(1, customerID);
        createSt.setDouble(2, totalPrice);
        createSt.setTimestamp(3, orderDate);
        createSt.setString(4, destination);
        createSt.setString(5, departureDate);
        createSt.setString(6, returnDate);
        createSt.setString(7, seatType);
        createSt.executeUpdate();
        System.out.println("Order successfully created");
    }

    // Read Operation
    public ArrayList<Order> fetchAllOrders() throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        ResultSet rs = readSt.executeQuery();
        while (rs.next()) {
            int orderID = rs.getInt(1);
            String customerID = rs.getString(2);
            double totalPrice = rs.getDouble(3);
            Timestamp orderDate = rs.getTimestamp(4);
            String destination = rs.getString(5);
            String departureDate = rs.getString(6);
            String returnDate = rs.getString(7);
            String seatType = rs.getString(8);

            Order order = new Order(customerID, totalPrice, orderDate, destination, departureDate, returnDate, seatType);
            orders.add(order);
        }
        return orders;
    }

    // Update Operation
    public void updateOrder(int orderID, String firstName, String lastName, String email, String destination, String departureDate, String returnDate, int passengers, String seatType, double totalPrice, Timestamp orderDate) throws SQLException {
        updateSt.setString(1, firstName);
        updateSt.setString(2, lastName);
        updateSt.setString(3, email);
        updateSt.setString(4, destination);
        updateSt.setString(5, departureDate);
        updateSt.setString(6, returnDate);
        updateSt.setInt(7, passengers);
        updateSt.setString(8, seatType);
        updateSt.setDouble(9, totalPrice);
        updateSt.setTimestamp(10, orderDate);
        updateSt.setInt(11, orderID);
        updateSt.executeUpdate();
        System.out.println("Order successfully updated");
    }

    // Delete Operation
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
            String customerID = rs.getString(2);
            double totalPrice = rs.getDouble(3);
            Timestamp orderDate = rs.getTimestamp(4);
            String destination = rs.getString(5);
            String departureDate = rs.getString(6);
            String returnDate = rs.getString(7);
            String seatType = rs.getString(8);

            return new Order(customerID, totalPrice, orderDate, destination, departureDate, returnDate, seatType);
        }
        return null; 
    }

    public boolean saveOrder(Order order) {
        String sql = "INSERT INTO orders (customer_id, order_date) VALUES (?, ?)";


        try (
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Assuming Customer class has a method getId() to get the customer ID
            preparedStatement.setString(1, order.getCustomerID());
            preparedStatement.setTimestamp(2, order.getOrderDate());

            // Execute the insert statement
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Return true if the order was saved successfully

        } catch (SQLException e) {
            e.printStackTrace(); // Simple error logging
            return false; // Return false if there was an error saving the order
        }
    }
}

