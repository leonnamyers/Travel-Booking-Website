package com.iotbay.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iotbay.Model.Payment;


public class DeletePaymentDetailsController extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Payment payment = (Payment) session.getAttribute("payment");
        if (payment != null) {
            session.removeAttribute("payment");
        }

        // Display message
        request.getRequestDispatcher("DeletePayment.jsp").forward(request, response);
    }
}