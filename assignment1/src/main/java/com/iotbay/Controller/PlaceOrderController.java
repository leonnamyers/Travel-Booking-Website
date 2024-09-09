package com.iotbay.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iotbay.Model.Cart;

public class PlaceOrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/cart.jsp"); // Redirect to cart if empty
            return;
        }

        request.setAttribute("cart", cart); // Send cart details to JSP
        request.getRequestDispatcher("placeOrder.jsp").forward(request, response); // Show order summary
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/cart.jsp"); // Redirect if cart is empty
            return;
        }

        // Retrieve form data from the request
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String destination = request.getParameter("destination");
        String departureDate = request.getParameter("departureDate");
        String returnDate = request.getParameter("returnDate");
        int passengers = Integer.parseInt(request.getParameter("passengers"));

        // Additional order processing logic can go here
        // Save the order in the database, for instance

        // Redirect to post-order thank you page
        response.sendRedirect(request.getContextPath() + "/PostOrder.jsp");
    }
}
