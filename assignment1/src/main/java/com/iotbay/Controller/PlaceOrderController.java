package com.iotbay.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iotbay.Model.Order;
import com.iotbay.Model.User;

public class PlaceOrderController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // If user is not logged in, redirect to login page
        if (user == null) {
            response.sendRedirect("login.jsp"); 
            return;
        }

        // Create new order object
        Order order = new Order();
        order.setCustomerEmail(user.getEmail());

        try {
            // Extract and set order parameters from request
            order.setDestination(request.getParameter("destination"));
            order.setDepartureDate(request.getParameter("departureDate"));
            order.setReturnDate(request.getParameter("returnDate"));
            order.setNumberOfPassengers(Integer.parseInt(request.getParameter("passengers")));
        } catch (NumberFormatException e) {
            // Redirect to failure page in case of invalid data
            response.sendRedirect("placeorder.jsp?booking=failure");
            return;
        }

        try {
            // Simulate successful booking process (Add actual logic here)
            // manager.bookTrip(order);

            // Redirect to success page after booking
            response.sendRedirect("postorder.jsp?booking=success");

        } catch (IOException e) {
            // In case of failure, redirect to failure page
            response.sendRedirect("placeorder.jsp?booking=failure");
        }
    }
}
