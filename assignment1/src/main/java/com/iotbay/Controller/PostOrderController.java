package com.iotbay.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iotbay.Dao.DBManager;
import com.iotbay.Model.Order;
import com.iotbay.Model.User;

public class PlaceOrderController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session and retrieve relevant objects
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        User user = (User) session.getAttribute("user");

        // Check if user is logged in and manager is available
        if (user == null || manager == null) {
            response.sendRedirect("login.jsp"); // Redirect to login if user is not logged in
            return;
        }

        // Create a new Order object
        Order order = new Order();

        // Set the order details
        order.setCustomerEmail(user.getEmail());

        // Assuming "paymentID" is a parameter in the request, parse it as an integer
        try {
            int paymentID = Integer.parseInt(request.getParameter("paymentID"));
            order.setPaymentID(paymentID);
        } catch (NumberFormatException e) {
            // Handle invalid paymentID parameter
            response.sendRedirect("placeorder.jsp?order=failure");
            return;
        }

        try {
            // Place the order using the DBManager
            manager.placeOrder(order);

            // Redirect to a success page
            response.sendRedirect("thankyou.jsp?order=success");

        } catch (Exception e) {
            response.sendRedirect("thankyou.jsp?order=failure");
        }
    }
}