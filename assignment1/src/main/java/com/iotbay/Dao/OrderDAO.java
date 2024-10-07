package com.iotbay.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.iotbay.Model.Order;

public class OrderDAO {

    // SQL Queries
    private String createQuery = "INSERT INTO Order (CustomerID, totalPrice, orderDate, destination, departureDate, returnDate, seatType) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private String readQuery = "SELECT orderID, customerID, totalPrice, orderDate, destination, departureDate, returnDate, seatType FROM Orders";
    private String deleteQuery = "DELETE FROM Orders WHERE orderID = ?";
    private String getOrderQuery = "SELECT orderID, customerID, totalPrice, orderDate, destination, departureDate, returnDate, seatType FROM Orders WHERE orderID = ?";
    private String updateQuery = "UPDATE Orders SET firstName = ?, lastName = ?, email = ?, destination = ?, departureDate = ?, returnDate = ?, passengers = ?, seatType = ?, totalPrice = ?, orderDate = ? WHERE orderID = ?";

    private Connection connection;

    // Constructor

    public OrderDAO(Connection connection) throws SQLException {
        this.connection = connection;
        connection.setAutoCommit(true);
    }

    // Create Operation
    public void createOrder(Order order, String customerID, double totalPrice, Timestamp orderDate, String destination, String departureDate, String returnDate, String seatType) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(createQuery);
        statement.setString(1, order.getCustomerID());
        statement.setDouble(2, order.getTotalPrice());
        statement.setTimestamp(3, order.getOrderDate());
        statement.setString(4, order.getDestination());
        statement.setTimestamp(5, order.getDepartureDate());
        statement.setTimestamp(6, order.getReturnDate());
        statement.setString(7, order.getSeatType());
        
    statement.executeUpdate();
    }

    // Read Operation
    public ArrayList<Order> fetchAllOrders() throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        String fetchAllOrdersQuery = "SELECT * FROM Orders"; // Adjust the query if necessary
        PreparedStatement statement = connection.prepareStatement(fetchAllOrdersQuery);
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            int orderID = rs.getInt(1);
            String customerID = rs.getString(2);
            double totalPrice = rs.getDouble(3);
            Timestamp orderDate = rs.getTimestamp(4);
            String destination = rs.getString(5);
            Timestamp departureDate = rs.getTimestamp(6);
            Timestamp returnDate = rs.getTimestamp(7);
            String seatType = rs.getString(8);
    
            Order order = new Order(customerID, totalPrice, orderDate, destination, departureDate, returnDate, seatType);
            orders.add(order);
        }
        return orders;
    }
    // Update Operation
    public void updateOrder(Order oldOrderData, Order newOrderData) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(updateQuery);
        statement.setString(1, newOrderData.getCustomerID());
        statement.setDouble(2, newOrderData.getTotalPrice());
        statement.setTimestamp(3, newOrderData.getOrderDate());
        statement.setString(4, newOrderData.getDestination());
        statement.setTimestamp(5, newOrderData.getDepartureDate());
        statement.setTimestamp(6, newOrderData.getReturnDate());
        statement.setString(7, newOrderData.getSeatType());
        statement.setInt(8, oldOrderData.getOrderID()); // Assuming Order has a method getOrderID()
    
        int rowsAffected = statement.executeUpdate();
    
        if (rowsAffected == 0) {
            System.out.println("Update failed - Order not found");
        } else {
            System.out.println("Order successfully updated");
        }
    
        statement.close();
    }

    // Delete Operation
    public void deleteOrder(int orderID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(deleteQuery);
        statement.setInt(1, orderID);
        statement.executeUpdate();

    }

    // Get single order by ID
    public Order fetchOrder(int orderID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(getOrderQuery);
        statement.setInt(1, orderID);
        
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            String customerID = rs.getString(2);
            double totalPrice = rs.getDouble(3);
            Timestamp orderDate = rs.getTimestamp(4);
            String destination = rs.getString(5);
            Timestamp departureDate = rs.getTimestamp(6);
            Timestamp returnDate = rs.getTimestamp(7);
            String seatType = rs.getString(8);
    
            // Create and return the Order object
            Order order = new Order(customerID, totalPrice, orderDate, destination, departureDate, returnDate, seatType);
            statement.close(); // Close the statement after execution
            return order;
        }
    
        statement.close(); // Close the statement if no order is found
        return null; // Return null if the order doesn't exist
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


