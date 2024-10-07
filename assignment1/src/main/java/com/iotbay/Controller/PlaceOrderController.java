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
import com.iotbay.Model.Cart;
import com.iotbay.Model.Order;

public class PlaceOrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");


        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("cart.jsp"); 
            return;
        }

        request.setAttribute("cart", cart);
        request.getRequestDispatcher("FlightOrder.jsp").forward(request, response); 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("cart.jsp"); 
            return;
        }

        // Retrieve form data from the request
        String firstname = request.getParameter("First Name");
        String lastname = request.getParameter("Last Name");
        String email = request.getParameter("email");
        String destination = request.getParameter("destination");
        String departureDate = request.getParameter("departureDate");
        String returnDate = request.getParameter("returnDate");
        String seatType = request.getParameter("seatType");

        // Validate that none of the form fields are empty
        if (firstname == null || lastname == null || email == null || destination == null ||
                departureDate == null || returnDate == null || seatType == null ||
                firstname.isEmpty() || lastname.isEmpty() || email.isEmpty() || destination.isEmpty() ||
                departureDate.isEmpty() || returnDate.isEmpty() || seatType.isEmpty()) {
            request.setAttribute("errorMessage", "All fields are required!");
            request.getRequestDispatcher("FlightOrder.jsp").forward(request, response);
            return;
        }

        DBConnector dbConnector = null;
        Connection connection = null;
        
        try {
            dbConnector = new DBConnector();
            connection = dbConnector.openConnection();
            OrderDAO orderDAO = new OrderDAO(connection);

            // Create the order object using form data and cart details
            Timestamp orderDate = new Timestamp(System.currentTimeMillis());
            double totalPrice = cart.getTotalPrice(); // Assuming getTotalPrice() is available in Cart

            Order order = new Order(email, totalPrice, orderDate, destination, departureDate, returnDate, seatType);
            order.setCart(cart); // Associate the cart with the order

            // Save the order to the database
            orderDAO.createOrder(order, email, totalPrice, orderDate, destination, departureDate, returnDate, seatType);

            // Clear the cart after placing the order
            cart.clear();
            session.setAttribute("cart", cart);

            // Redirect to payment page after successful order placement
            response.sendRedirect("Payment.jsp");

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
}
