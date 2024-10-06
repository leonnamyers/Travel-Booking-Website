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

import com.iotbay.Model.Cart;
import com.iotbay.Model.Customer;
import com.iotbay.Model.Order;

public class CartController extends HttpServlet {

    public static final String CLEAR_BUTTON_VALUE = "Clear cart";
    public static final String PLACE_ORDER_BUTTON_VALUE = "Place Order";
    private static final Logger LOGGER = Logger.getLogger(CartController.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        serveJSP(request, response, "cart.jsp"); // Display cart page
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
        }

        String action = request.getParameter("action");
        if (action == null) action = "";


        try {
            switch (action) {
                case CLEAR_BUTTON_VALUE:
                    clearCart(request, response, cart);
                    break;
                case PLACE_ORDER_BUTTON_VALUE:
                    placeOrder(request, response, cart);
                    break;
    
                default:
                    deleteItem(request, response, cart);
                    break;
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error processing cart action: {0}", ex.getMessage());
            response.sendRedirect("error.jsp");
        }
    }

    private void clearCart(HttpServletRequest request, HttpServletResponse response, Cart cart) throws ServletException, IOException {
        cart.clear();
        request.getSession().setAttribute("cart", cart);
        serveJSP(request, response, "cart.jsp");
    }

    private void placeOrder(HttpServletRequest request, HttpServletResponse response, Cart cart) throws IOException {
        if (cart == null || cart.isEmpty()) {
            // If cart is empty, redirect back to cart.jsp with an error message
            request.setAttribute("errorMessage", "Your cart is empty. Please add items before placing an order.");
            return;
        }

        // Create Order
        Order order = new Order();
        order.setCart(cart);
        order.setCustomer((Customer) request.getSession().getAttribute("user"));

        // DAO stuff using Order

        request.getSession().setAttribute("cart", new Cart());
        
        response.sendRedirect("Payment.jsp"); // Redirect to payment page
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
