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
        //DBManager manager = (DBManager) session.getAttribute("manager");
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp"); 
            return;
        }

        Order order = new Order();
        order.setCustomerEmail(user.getEmail());

        try {
            order.setDestination(request.getParameter("destination"));
            order.setDepartureDate(request.getParameter("departureDate"));
            order.setReturnDate(request.getParameter("returnDate"));
            order.setNumberOfPassengers(Integer.parseInt(request.getParameter("passengers")));
        } catch (NumberFormatException e) {
            response.sendRedirect("placeorder.jsp?booking=failure");
            return;
        }

        try {
            //manager.bookTrip(booking);

            response.sendRedirect("postorder.jsp?booking=success");

        } catch (IOException e) {
            response.sendRedirect("placeorder.jsp?booking=failure");
        }
    }
}
