package com.iotbay.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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

    // Constants for button and logger
    public static final String PLACE_ORDER_BUTTON_VALUE = "Place Order";
    private static final Logger LOGGER = Logger.getLogger(CartController.class.getName());

    // Display cart page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        serveJSP(request, response, "cart.jsp"); 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        // Initialize a new cart if none exists in the session
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        // Determine action type: Place order or delete item
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
    // Method to place an order if the cart is not empty
    private void placeOrder(HttpServletRequest request, HttpServletResponse response, Cart cart) throws ServletException, IOException {
        if (cart == null || cart.isEmpty()) {
            request.setAttribute("errorMessage", "Your cart is empty. Please add items before placing an order.");
            serveJSP(request, response, "cart.jsp");
            return;
        }

        // Create Order
        Order order = new Order();


        DBConnector dbConnector = null;
        Connection connection = null;

        try {
            // database connection
            dbConnector = new DBConnector();
            connection = dbConnector.openConnection();

            // Save order in database using OrderDAO
            OrderDAO orderDAO = new OrderDAO(connection);
            orderDAO.createOrder(order);

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
        // Redirect user to payment page
        response.sendRedirect("Payment.jsp");

}

        // Delete an item from the cart
    private void deleteItem(HttpServletRequest request, HttpServletResponse response, Cart cart) throws ServletException, IOException {
        // Iterate through parameters to find the "remove" parameter
        Enumeration<String> parameters = request.getParameterNames();
        int index = -1;
        while (parameters.hasMoreElements()) {
            String element = parameters.nextElement();
            if (element.contains("remove")) {
                index = Integer.parseInt(element.replace("remove", ""));
            }
        }
        // Check if index is valid, then remove item from cart
        if (index >= 0 && index < cart.getItems().size()) {
            cart.deleteItem(index);
        }
        // Update cart in session and reload the cart page
        request.getSession().setAttribute("cart", cart);
        serveJSP(request, response, "cart.jsp");
    }

    private void serveJSP(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        request.getRequestDispatcher(page).forward(request, response);
    }
}
