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
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        User user = (User) session.getAttribute("user");

        if (user == null || manager == null) {
            response.sendRedirect("login.jsp"); 
            return;
        }

        Order order = new Order();

        order.setCustomerEmail(user.getEmail());

        try {
            int paymentID = Integer.parseInt(request.getParameter("paymentID"));
            order.setPaymentID(paymentID);
        } catch (NumberFormatException e) {
            response.sendRedirect("placeorder.jsp?order=failure");
            return;
        }

        try {
            //manager.placeOrder(order);

            response.sendRedirect("postorder.jsp?order=success");

        } catch (IOException e) {
            response.sendRedirect("placeorder.jsp?order=failure");
        }
    }
}