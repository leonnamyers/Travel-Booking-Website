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
import com.iotbay.Model.Cart;

public class PlaceOrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");


        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("cart.jsp"); // Redirect to cart if empty
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
            response.sendRedirect("cart.jsp"); // Redirect if cart is empty
            return;
        }

        // Retrieve form data from the request
        String firstname = request.getParameter("First Name");
        String lastname = request.getParameter("Last Name");
        String email = request.getParameter("email");
        String destination = request.getParameter("destination");
        String departureDate = request.getParameter("departureDate");
        String returnDate = request.getParameter("returnDate");
        int passengers = Integer.parseInt(request.getParameter("passengers"));


        OrderDAO orderDAO = (OrderDAO) session.getAttribute("orderDAO");

        if (orderDAO == null) {
            response.sendRedirect("error.jsp"); // Handle case if DAO is not available in the session
            return;
        }

        try {
            // Create the order in the database
            String customerID = email; 
            double totalPrice = cart.getTotalPrice();
            Timestamp orderDate = new Timestamp(System.currentTimeMillis());

            // Call the DAO method to save the order
            orderDAO.createOrder(customerID, totalPrice, orderDate);

            // Clear the cart after placing the order
            cart.clear();
            session.setAttribute("cart", cart);

            response.sendRedirect("PostOrder.jsp"); // Redirect to post-order confirmation page
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redirect to an error page in case of failure
        }
    }
}