package com.iotbay.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.iotbay.Model.Order;

public class OrderDAO {

    private String createQuery = "INSERT INTO Orders (OrderID, email, order_date, StartTime, EndTime, seatType, RoomType, totalPrice) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private String readQuery = "SELECT OrderID, email, order_date, StartTime, EndTime, seatType, RoomType, totalPrice FROM Orders";
    private String deleteQuery = "DELETE FROM Orders WHERE OrderID = ?";
    private String getOrderQuery = "SELECT OrderID, email, order_date, StartTime, EndTime, seatType, RoomType, totalPrice FROM Orders WHERE OrderID = ?";
    private String updateQuery = "UPDATE Orders SET email = ?, order_date = ?, StartTime = ?, EndTime = ?, seatType = ?, RoomType = ?, totalPrice = ? WHERE OrderID = ?";
    
    private Connection connection;

    // Constructor
    public OrderDAO(Connection connection) throws SQLException {
        this.connection = connection;
        connection.setAutoCommit(true);
    }

    //Create Operation
    public void createOrder(Order order) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(createQuery);
        statement.setInt(1, order.getOrderID());
        statement.setString(2, order.getCustomerID());
        statement.setTimestamp(3, order.getOrderDate());
        statement.setTimestamp(4, order.getStartTime());
        statement.setTimestamp(5, order.getEndTime());
        statement.setString(6, order.getSeatType());
        statement.setString(7, order.getRoomType());
        statement.setInt(8, order.getTotalPrice());

        statement.executeUpdate();
        statement.close();
    }


    // Read Operation
    public ArrayList<Order> fetchAllOrders() throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(readQuery);
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            String customerID = rs.getString("email");
            Timestamp orderDate = rs.getTimestamp("order_date");
            Timestamp startTime = rs.getTimestamp("StartTime");
            Timestamp endTime = rs.getTimestamp("EndTime");
            String seatType = rs.getString("seatType");
            String roomType = rs.getString("RoomType");
            int orderID = rs.getInt("OrderID");
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
        statement.setString(2, order.getCustomerID());
        statement.setTimestamp(3, order.getOrderDate());
        statement.setTimestamp(4, order.getStartTime());
        statement.setTimestamp(5, order.getEndTime());
        statement.setString(6, order.getSeatType());
        statement.setString(7, order.getRoomType());
        statement.setInt(8, order.getTotalPrice());
        
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

    // public boolean saveOrder(Order order) {
    //     PreparedStatement statement = connection.prepareStatement(saveOrderQuery) {
    //     statement.setString(1, order.getCustomerID());
    //     statement.setTimestamp(2, order.getOrderDate());
    //     statement.setTimestamp(3, order.getStartTime());
    //     statement.setTimestamp(4, order.getEndTime());
    //     statement.setString(5, order.getSeatType());
    //     statement.setString(6, order.getRoomType());
    //     statement.setInt(7, order.getTotalPrice());

    //     int rowsAffected = statement.executeUpdate();
    //     return rowsAffected > 0;

    //     }
    // }
}
