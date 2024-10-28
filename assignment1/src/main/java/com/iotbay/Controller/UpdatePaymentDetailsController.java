package com.iotbay.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iotbay.Dao.DBConnector;
import com.iotbay.Dao.PaymentDAO;
import com.iotbay.Model.Payment;

public class UpdatePaymentDetailsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        int paymentID = Integer.parseInt(request.getParameter("paymentID"));
        DBConnector dbConnector = null;
        Connection connection = null;
        try {
            dbConnector = new DBConnector();
            connection = dbConnector.openConnection();
            PaymentDAO paymentDAO = new PaymentDAO(connection);

            // Fetch the payment details by ID
            Payment payment = paymentDAO.fetchPayment(paymentID);

            if (payment != null) {
                request.setAttribute("payment", payment);
                request.getRequestDispatcher("UpdatePaymentDetails.jsp").forward(request, response);
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBConnector dbConnector = null;
        Connection connection = null;
        
        try {
            // Validate paymentID
            String paymentIDStr = request.getParameter("paymentID");
            if (paymentIDStr == null || paymentIDStr.trim().isEmpty()) {
                throw new IllegalArgumentException("Payment ID is required");
            }
            int paymentID = Integer.parseInt(paymentIDStr.trim());
            
            // Validate other required fields
            String cardNumber = request.getParameter("cardNumber");
            String cardHolderName = request.getParameter("cardHolderName");
            String expiryDate = request.getParameter("expiryDate");
            String cvv = request.getParameter("cvv");
            
            if (cardNumber == null || cardHolderName == null || cvv == null) {
                throw new IllegalArgumentException("All payment details are required");
            }
            
            // Database operations
            dbConnector = new DBConnector();
            connection = dbConnector.openConnection();
            PaymentDAO paymentDAO = new PaymentDAO(connection);
    
            // Fetch the existing payment details to update
            Payment existingPayment = paymentDAO.fetchPayment(paymentID);
            
            if (existingPayment == null) {
                throw new IllegalArgumentException("Payment not found");
            }
    
            // Create and update payment object
            Payment updatedPayment = new Payment();
            updatedPayment.setPaymentID(paymentID);
            updatedPayment.setCardNumber(cardNumber);
            updatedPayment.setCardHolderName(cardHolderName);
            updatedPayment.setCvv(cvv);
    
            paymentDAO.updatePayment(updatedPayment);
    
            // Set success message in session
            session.setAttribute("message", "Payment details updated successfully");
            response.sendRedirect("UpdatePaymentDetails.jsp");
            
        } catch (NumberFormatException e) {
            session.setAttribute("error", "Invalid payment ID format");
            response.sendRedirect("error.jsp");
        } catch (IllegalArgumentException e) {
            session.setAttribute("error", e.getMessage());
            response.sendRedirect("error.jsp");
        } catch (ClassNotFoundException | SQLException e) {
            session.setAttribute("error", "Database error occurred");
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