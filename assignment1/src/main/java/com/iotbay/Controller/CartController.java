package com.iotbay.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iotbay.Dao.DBConnector;
import com.iotbay.Dao.OrderDAO;
import com.iotbay.Model.Cart;
import com.iotbay.Model.Order;

public class CartController extends HttpServlet {

    public static final String PLACE_ORDER_BUTTON_VALUE = "Place Order";
    private static final Logger LOGGER = Logger.getLogger(CartController.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        serveJSP(request, response, "cart.jsp"); // Display cart page
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        String action = request.getParameter("action");
        if (action == null) action = "";

        try {
            if (PLACE_ORDER_BUTTON_VALUE.equals(action)) {
                placeOrder(request, response, cart);
            } else {
                deleteItem(request, response, cart);
            }
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error processing cart action: {0}", ex.getMessage());
            response.sendRedirect("error.jsp");
        }

    }

    private void placeOrder(HttpServletRequest request, HttpServletResponse response, Cart cart) throws ServletException, IOException {
        if (cart == null || cart.isEmpty()) {
            request.setAttribute("errorMessage", "Your cart is empty. Please add items before placing an order.");
            serveJSP(request, response, "cart.jsp");
            return;
        }

        // Create Order
        Order order = new Order();
        order.setCustomerID((String) request.getSession().getAttribute("customerID")); // Assuming customer ID is stored in session
        order.setTotalPrice(cart.getTotalPrice());
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));

        DBConnector dbConnector = null;
        Connection connection = null;

        try {
            dbConnector = new DBConnector();
            connection = dbConnector.openConnection();

            OrderDAO orderDAO = new OrderDAO(connection);

        } catch (ClassNotFoundException | SQLException e) {
            request.setAttribute("errorMessage", "Error processing your order. Please try again later.");
        serveJSP(request, response, "cart.jsp");
        } finally {
            if (dbConnector != null) {
                try {
                    dbConnector.closeConnection(); // Close the connection in the finally block
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }

    response.sendRedirect("Payment.jsp");

}

    private void deleteItem(HttpServletRequest request, HttpServletResponse response, Cart cart) throws ServletException, IOException {
        Enumeration<String> parameters = request.getParameterNames();
        int index = -1;
        while (parameters.hasMoreElements()) {
            String element = parameters.nextElement();
            if (element.contains("remove")) {
                index = Integer.parseInt(element.replace("remove", ""));
            }
        }
        if (index >= 0 && index < cart.getItems().size()) {
            cart.deleteItem(index);
        }
        request.getSession().setAttribute("cart", cart);
        serveJSP(request, response, "cart.jsp");
    }

    private void serveJSP(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        request.getRequestDispatcher(page).forward(request, response);
    }
}
