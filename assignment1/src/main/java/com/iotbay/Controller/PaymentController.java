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

        // Add your payment logic here (save payment, process transaction, etc.)

        // Forward to the PostOrder.jsp page after successful payment
        request.getRequestDispatcher("PlaceOrder.jsp").forward(request, response);
    }
}
