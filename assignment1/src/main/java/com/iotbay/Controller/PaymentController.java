package com.iotbay.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iotbay.Model.Cart;

public class PaymentController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        // Check if cart is empty
        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("cart.jsp"); // Redirect to cart if empty
            return;
        }

        // Redirect to payment page
        response.sendRedirect("Payment.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        // Check if cart is empty
        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("cart.jsp"); 
            return;
        }

        String cardHolderName = request.getParameter("cardHolderName");
        String cardNumber = request.getParameter("cardNumber");
        String expiryDate = request.getParameter("expiryDate");
        String cvv = request.getParameter("cvv");

        if (cardNumber == null || !cardNumber.matches("\\d{16}")) {
            request.setAttribute("errorMessage", "Invalid card number.");
            request.getRequestDispatcher("Payment.jsp").forward(request, response);
            return;
        }
    
        // Validate expiry date (should be MM/YY format)
        if (expiryDate == null || !expiryDate.matches("(0[1-9]|1[0-2])/\\d{2}")) {
            request.setAttribute("errorMessage", "Invalid expiry date.");
            request.getRequestDispatcher("Payment.jsp").forward(request, response);
            return;
        }
    
        // Validate CVV (should be 3 or 4 digits)
        if (cvv == null || !cvv.matches("\\d{3,4}")) {
            request.setAttribute("errorMessage", "Invalid CVV.");
            request.getRequestDispatcher("Payment.jsp").forward(request, response);
            return;
        }

        // Add payment logic here (save payment, etc.)

        request.getRequestDispatcher("FlightOrder.jsp").forward(request, response);
    }
}
