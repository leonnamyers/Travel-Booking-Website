package com.iotbay.Controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        order.setCart(cart);

        // Save Order
        OrderDAO orderDAO = new OrderDAO();
        try {
            boolean orderSaved = orderDAO.saveOrder(order);
            if (orderSaved) {
                request.setAttribute("order", order);
                response.sendRedirect("Payment.jsp"); // Redirect to payment page
            } else {
                request.setAttribute("errorMessage", "There was an error saving your order. Please try again.");
                serveJSP(request, response, "cart.jsp");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error saving order: {0}", e.getMessage());
            request.setAttribute("errorMessage", "Error processing your order. Please try again later.");
            serveJSP(request, response, "cart.jsp");
        }
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
