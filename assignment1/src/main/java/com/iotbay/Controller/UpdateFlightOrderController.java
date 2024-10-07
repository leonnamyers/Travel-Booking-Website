package com.iotbay.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iotbay.Dao.DBConnector;
import com.iotbay.Dao.OrderDAO;
import com.iotbay.Model.Order;

public class UpdateFlightOrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        int orderID = Integer.parseInt(request.getParameter("orderID"));
        // Initialize DB connection and OrderDAO
        DBConnector dbConnector = null;
        Connection connection = null;
        try {
            dbConnector = new DBConnector();
            connection = dbConnector.openConnection();
            OrderDAO orderDAO = new OrderDAO(connection);

            // Fetch the order by ID
            Order order = orderDAO.fetchOrder(orderID);

            if (order != null) {
                request.setAttribute("order", order);
                request.getRequestDispatcher("updateFlightOrder.jsp").forward(request, response);
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        } finally {
            // Ensure the connection is closed
            if (dbConnector != null) {
                try {
                    dbConnector.closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    // Method to process the updated order details
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        DBConnector dbConnector = null;
        Connection connection = null;
        try {
            dbConnector = new DBConnector();
            connection = dbConnector.openConnection();
            OrderDAO orderDAO = new OrderDAO(connection);

            int orderID = Integer.parseInt(request.getParameter("orderID"));
            String firstname = request.getParameter("FirstName");
            String lastname = request.getParameter("LastName");
            String email = request.getParameter("email");
            String destination = request.getParameter("destination");
            String departureDate = request.getParameter("departureDate");
            String returnDate = request.getParameter("returnDate");
            String seatType = request.getParameter("seatType");
            double updatedTotalPrice = Double.parseDouble(request.getParameter("totalPrice"));
            Timestamp updatedOrderDate = new Timestamp(System.currentTimeMillis());

            // Fetch the existing order to update
            Order existingOrder = orderDAO.fetchOrder(orderID);

            if (existingOrder != null) {
                // Create a new Order object with the updated values
                Order updatedOrder = new Order();
                updatedOrder.setOrderID(orderID);

                // Update the order in the database
                orderDAO.updateOrder(existingOrder, updatedOrder);

                // After updating, redirect to a confirmation or order summary page
                response.sendRedirect("FlightOrder.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        } finally {
            if (dbConnector != null) {
                try {
                    dbConnector.closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
