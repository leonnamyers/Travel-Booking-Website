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

public class ReviewPaymentsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        DBConnector dbConnector = null;
        Connection connection = null;

        try {
            dbConnector = new DBConnector();
            connection = dbConnector.openConnection();
            PaymentDAO paymentDAO = new PaymentDAO(connection);

            // Retrieve payment ID as a request parameter, not from User object
            String paymentIDParam = request.getParameter("paymentID");
            if (paymentIDParam != null && !paymentIDParam.isEmpty()) {
                int paymentID = Integer.parseInt(paymentIDParam);

                // Fetch payment using paymentDAO
                Payment payment = paymentDAO.fetchPayment(paymentID);
                if (payment != null) {
                    request.setAttribute("payment", payment);
                } else {
                    request.setAttribute("error", "Payment details not found.");
                }
            }

            request.getRequestDispatcher("ReviewPayments.jsp").forward(request, response);
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
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        DBConnector dbConnector = null;
        Connection connection = null;

        try {
            dbConnector = new DBConnector();
            connection = dbConnector.openConnection();
            PaymentDAO paymentDAO = new PaymentDAO(connection);

            // Retrieve payment ID as a request parameter
            String paymentIDParam = request.getParameter("paymentID");
            if (paymentIDParam != null && !paymentIDParam.isEmpty()) {
                int paymentID = Integer.parseInt(paymentIDParam);

                switch (action) {
                    case "remove":
                        // Remove payment by paymentID
                        paymentDAO.deletePayment(paymentID);
                        request.setAttribute("message", "Payment details removed successfully.");
                        break;

                    case "update":
                        // Update payment details
                        Payment paymentToUpdate = paymentDAO.fetchPayment(paymentID);
                        if (paymentToUpdate != null) {
                            String cardHolderName = request.getParameter("cardHolderName");
                            String cardNumber = request.getParameter("cardNumber");
                            String expiryDate = request.getParameter("expiryDate");
                            String cvv = request.getParameter("cvv");

                            // Perform validation on the input fields here

                            paymentToUpdate.setCardHolderName(cardHolderName);
                            paymentToUpdate.setCardNumber(cardNumber);
                            paymentToUpdate.setExpirationDate(java.sql.Date.valueOf(expiryDate)); // Assuming expiryDate is in yyyy-MM-dd format
                            paymentToUpdate.setCvv(cvv);

                            paymentDAO.updatePayment(paymentToUpdate);
                            request.setAttribute("message", "Payment details updated successfully.");
                        } else {
                            request.setAttribute("error", "No payment details found to update.");
                        }
                        break;

                    default:
                        request.setAttribute("error", "Unknown action.");
                }
            } else {
                request.setAttribute("error", "No payment ID provided.");
            }

            request.getRequestDispatcher("ReviewPayments.jsp").forward(request, response);
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
