package com.iotbay.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iotbay.Dao.DBConnector;
import com.iotbay.Dao.PaymentDAO;
import com.iotbay.Model.Cart;
import com.iotbay.Model.Order;
import com.iotbay.Model.Payment;

public class PaymentController extends HttpServlet {

    private PaymentDAO paymentDAO;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = new DBConnector().getConnection();
            this.paymentDAO = new PaymentDAO(connection);
        
        } catch (SQLException e) {
            throw new ServletException("Error initializing PaymentController", e);
        } catch (ClassNotFoundException ex) {
        }        
    }
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
        Order order = (Order) session.getAttribute("order");
        // PaymentDAO paymentDAO = (PaymentDAO) session.getAttribute("paymentDAO");

        if (order == null) {
            order = new Order();
            session.setAttribute("order", order);
        }

        // Check if cart is empty
        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("cart.jsp"); 
            return;
        }

        String cardHolderName = request.getParameter("cardHolderName");
        String cardNumber = request.getParameter("cardNumber");
        String expirationDateStr = request.getParameter("expiryDate");
        String cvv = request.getParameter("cvv");

        if (cardNumber == null || !cardNumber.matches("\\d{16}")) {
            request.setAttribute("errorMessage", "Invalid card number.");
            request.getRequestDispatcher("Payment.jsp").forward(request, response);
            return;
        }
    
        // Validate expiry date (should be MM/YY format)
        if (expirationDateStr == null || !expirationDateStr.matches("(0[1-9]|1[0-2])/\\d{2}")) {
            request.setAttribute("errorMessage", "Invalid expiration date.");
            request.getRequestDispatcher("Payment.jsp").forward(request, response);
            return;
        }
    
        // Validate CVV (should be 3 or 4 digits)
        if (cvv == null || !cvv.matches("\\d{3,4}")) {
            request.setAttribute("errorMessage", "Invalid CVV.");
            request.getRequestDispatcher("Payment.jsp").forward(request, response);
            return;
        }


        Date expirationDate = Date.valueOf("20" + expirationDateStr.substring(3) + "-" + expirationDateStr.substring(0, 2) + "-01");

        order.setCart(cart);
        session.setAttribute("order", order);

        try {
            // Create a new Payment object
            Payment payment = new Payment();
            payment.setEmail((String) session.getAttribute("email"));
            payment.setCardNumber(cardNumber);
            payment.setExpirationDate(expirationDate);
            payment.setCvv(cvv);
            payment.setCardHolderName(cardHolderName);

            // Save the payment using PaymentDAO
            this.paymentDAO.createPayment(payment);

            // Redirect to Review Payments page
            request.getRequestDispatcher("FlightOrder.jsp").forward(request, response);
        
        } catch (SQLException e) {
            request.setAttribute("errorMessage", "Error processing payment. Please try again.");
            request.getRequestDispatcher("Payment.jsp").forward(request, response);
        }
    }
}