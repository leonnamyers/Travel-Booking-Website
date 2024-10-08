package com.iotbay.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iotbay.Dao.DBConnector;
import com.iotbay.Dao.OrderDAO;
import com.iotbay.Model.Cart;
import com.iotbay.Model.Order;


public class PlaceOrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        Order order = (Order) session.getAttribute("order");

        if (order == null) {
            order = new Order();
            session.setAttribute("order", order);

        }

        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("cart.jsp");
            return;
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("cart.jsp");
            return;
        }

        request.setAttribute("cart", cart);
        response.sendRedirect("FlightOrder.jsp");

        // Retrieve form data from the request (order details)
        String firstname = request.getParameter("First Name");
        String lastname = request.getParameter("Last Name");
        String email = request.getParameter("email");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String seatType = request.getParameter("seatType");
        String roomType = request.getParameter("roomType");

        // Validate form inputs
        if (firstname == null || lastname == null || email == null ||
                startTime == null || endTime == null || seatType == null || roomType == null ||
                firstname.isEmpty() || lastname.isEmpty() || email.isEmpty() ||
                startTime.isEmpty() || endTime.isEmpty() || seatType.isEmpty() || roomType.isEmpty()) {
            request.setAttribute("errorMessage", "All fields are required!");
            request.getRequestDispatcher("FlightOrder.jsp").forward(request, response);
            return;
        }

        Timestamp orderDate = new Timestamp(System.currentTimeMillis());

        Order order = new Order();
        order.setCart(cart);
        session.setAttribute("order", order);

        System.out.println("Order details: " + order.toString());

        // Create an Order and save it in the database
        DBConnector dbConnector = null;
        Connection connection = null;

        try {
            dbConnector = new DBConnector();
            connection = dbConnector.openConnection();
            OrderDAO orderDAO = new OrderDAO(connection);
            // Save the order to the database

            orderDAO.createOrder(order);

            // Store the order details in the session
            session.setAttribute("order", order);
            
            request.getRequestDispatcher("PostOrder.jsp").forward(request, response);


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
