package com.iotbay.Dao;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectorTest {
    public static void main(String[] args) {
        try {
            DBConnector dbConnector = new DBConnector();
            Connection connection = dbConnector.openConnection();
            if (connection != null && !connection.isClosed()) {
                System.out.println("Database connection successful!");
                connection.close();
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
