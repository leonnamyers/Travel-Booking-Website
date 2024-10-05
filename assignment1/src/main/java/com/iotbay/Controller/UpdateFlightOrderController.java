
package com.iotbay.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iotbay.Dao.OrderDAO;
import com.iotbay.Model.Order;

public class UpdateFlightOrderController extends HttpServlet {

    // Method to handle request for displaying order details on the update page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        // Get the order ID from the request
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        
        OrderDAO orderDAO = (OrderDAO) session.getAttribute("orderDAO");
        
        if (orderDAO == null) {
            response.sendRedirect("error.jsp"); // Handle case if DAO is not available
            return;
        }
        
        try {
            // Fetch the order by ID
            Order order = orderDAO.fetchOrder(orderID);
            
            if (order != null) {
                request.setAttribute("order", order);
                request.getRequestDispatcher("updateFlightOrder.jsp").forward(request, response);
            } else {
                response.sendRedirect("error.jsp"); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); 
        }
    }

    // Method to process the updated order details
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        OrderDAO orderDAO = (OrderDAO) session.getAttribute("orderDAO");
        
        if (orderDAO == null) {
            response.sendRedirect("error.jsp");
            return;
        }

        try {
            int orderID = Integer.parseInt(request.getParameter("orderID"));
            String firstname = request.getParameter("FirstName");
            String lastname = request.getParameter("LastName");
            String email = request.getParameter("email");
            String destination = request.getParameter("destination");
            String departureDate = request.getParameter("departureDate");
            String returnDate = request.getParameter("returnDate");
            int passengers = Integer.parseInt(request.getParameter("passengers"));
            String seatType = request.getParameter("seatType");
            double updatedTotalPrice = Double.parseDouble(request.getParameter("totalPrice"));
            Timestamp updatedOrderDate = new Timestamp(System.currentTimeMillis());
            
            orderDAO.updateOrder(orderID, firstname, lastname, email, destination, departureDate, returnDate, passengers, seatType, updatedTotalPrice, updatedOrderDate);

            // After updating, redirect to a confirmation or order summary page
            response.sendRedirect("PostOrder.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
