package com.iotbay.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.iotbay.Model.Order;

public class OrderDAO {

    private String createQuery = "INSERT INTO Orders (customerID, orderDate, startTime, endTime, seatType, roomType, totalPrice) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private String readQuery = "SELECT orderID, customerID, orderDate, startTime, endTime, seatType, roomType, totalPrice FROM Orders";
    private String deleteQuery = "DELETE FROM Orders WHERE orderID = ?";
    private String getOrderQuery = "SELECT orderID, customerID, orderDate, startTime, endTime, seatType, roomType, totalPrice FROM Orders WHERE orderID = ?";
    private String updateQuery = "UPDATE Orders SET customerID = ?, orderDate = ?, startTime = ?, endTime = ?, seatType = ?, roomType = ?, totalPrice = ? WHERE orderID = ?";

    private Connection connection;

    // Constructor
    public OrderDAO(Connection connection) throws SQLException {
        this.connection = connection;
        connection.setAutoCommit(true);
    }

    //Create Operation
    public void createOrder(Order order) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(createQuery);
        statement.setString(1, order.getCustomerID());
        statement.setTimestamp(2, order.getOrderDate());
        statement.setTimestamp(3, order.getStartTime());
        statement.setTimestamp(4, order.getEndTime());
        statement.setString(5, order.getSeatType());
        statement.setString(6, order.getRoomType());
        statement.setInt(7, order.getTotalPrice());

        statement.executeUpdate();
        statement.close();
    }



    // Read Operation
    public ArrayList<Order> fetchAllOrders() throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(readQuery);
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            int orderID = rs.getInt("orderID");
            String customerID = rs.getString("customerID");
            Timestamp orderDate = rs.getTimestamp("orderDate");
            Timestamp startTime = rs.getTimestamp("startTime");
            Timestamp endTime = rs.getTimestamp("endTime");
            String seatType = rs.getString("seatType");
            String roomType = rs.getString("roomType");
            int totalPrice = rs.getInt("totalPrice");
    
            Order order = new Order();
            orders.add(order);
        }
        rs.close();
        statement.close();
        return orders;
    }

    // Update Operation
    public void updateOrder(Order order) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(updateQuery);
        statement.setString(1, order.getCustomerID());
        statement.setTimestamp(2, order.getOrderDate());
        statement.setTimestamp(3, order.getStartTime());
        statement.setTimestamp(4, order.getEndTime());
        statement.setString(5, order.getSeatType());
        statement.setString(6, order.getRoomType());
        statement.setInt(7, order.getTotalPrice());
        statement.setInt(8, order.getOrderID());
        
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
        statement.close();
    }

    // Get single order by ID
    public Order fetchOrder(int orderID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(getOrderQuery);
        statement.setInt(1, orderID);
        
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            String customerID = rs.getString("customerID");
            Timestamp orderDate = rs.getTimestamp("orderDate");
            Timestamp startTime = rs.getTimestamp("startTime");
            Timestamp endTime = rs.getTimestamp("endTime");
            String seatType = rs.getString("seatType");
            String roomType = rs.getString("roomType");
            int totalPrice = rs.getInt("totalPrice");

            Order order = new Order();
            rs.close();
            statement.close();
            return order;
        }

        rs.close();
        statement.close();
        return null;
    }

    public boolean saveOrder(Order order) {
        String sql = "INSERT INTO Orders (customerID, orderDate, startTime, endTime, seatType, roomType, totalPrice) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, order.getCustomerID());
            preparedStatement.setTimestamp(2, order.getOrderDate());
            preparedStatement.setTimestamp(3, order.getStartTime());
            preparedStatement.setTimestamp(4, order.getEndTime());
            preparedStatement.setString(5, order.getSeatType());
            preparedStatement.setString(6, order.getRoomType());
            preparedStatement.setInt(7, order.getTotalPrice());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
