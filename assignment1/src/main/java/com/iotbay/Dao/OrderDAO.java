package com.iotbay.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.iotbay.Model.Order;


public class OrderDAO {
    // CRUD statements for Order table
    private PreparedStatement createSt;
    private PreparedStatement readSt;
    private PreparedStatement updateSt;
    private PreparedStatement deleteSt;
    private PreparedStatement getOrderSt;

    // SQL queries
    private String createQuery = "INSERT INTO Orders (customerID, cartID) VALUES(?, ?)";
    private String readQuery = "SELECT orderID, customerID, cartID FROM Orders";
    private String updateQuery = "UPDATE Orders SET customerID = ?, cartID = ? WHERE orderID = ?";
    private String deleteQuery = "DELETE FROM Orders WHERE orderID = ?";
    private String getOrderQuery = "SELECT orderID, customerID, cartID FROM Orders WHERE orderID = ?";

    public OrderDAO(Connection connection) throws SQLException {
        connection.setAutoCommit(true);
        createSt = connection.prepareStatement(createQuery);
        readSt = connection.prepareStatement(readQuery);
        updateSt = connection.prepareStatement(updateQuery);
        deleteSt = connection.prepareStatement(deleteQuery);
        getOrderSt = connection.prepareStatement(getOrderQuery);
    }

    // Create Operation:
    public void createOrder(int customerID, int cartID) throws SQLException {
        createSt.setInt(1, customerID);
        createSt.setInt(2, cartID);
        createSt.executeUpdate();
        System.out.println("1 order successfully created");
    }

    // Read Operation:
    public ArrayList<Order> fetchAllOrders() throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        ResultSet rs = readSt.executeQuery();
        while (rs.next()) {
            int orderID = rs.getInt(1);
            int customerID = rs.getInt(2);
            int cartID = rs.getInt(3);
            Order order = new Order(orderID, customer, cart);
            orders.add(order);
        }
        return orders;
    }

    // Update Operation:
    public void updateOrder(int orderID, int customerID, int cartID) throws SQLException {
        updateSt.setInt(1, customerID);
        updateSt.setInt(2, cartID);
        updateSt.setInt(3, orderID);
        updateSt.executeUpdate();
        System.out.println("Order successfully updated");
    }

    // Delete Operation:
    public void deleteOrder(int orderID) throws SQLException {
        deleteSt.setInt(1, orderID);
        deleteSt.executeUpdate();
        System.out.println("Order successfully deleted");
    }

    // Get single order:
    public Order fetchOrder(int orderID) throws SQLException {
        getOrderSt.setInt(1, orderID);
        ResultSet rs = getOrderSt.executeQuery();
        if (rs.next()) {
            int customerID = rs.getInt(2);
            int cartID = rs.getInt(3);
            return new Order(orderID, customer, cart);
        }
        return null;
    }

}

