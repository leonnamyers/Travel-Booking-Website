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

public class CartController extends HttpServlet {

    public static final String SAVE_BUTTON_VALUE = "Save changes";
    public static final String CLEAR_BUTTON_VALUE = "Clear cart";
    public static final String CONFIRM_ORDER_BUTTON_VALUE = "Confirm order";
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
            session.setAttribute("cart", cart); // Initialize the cart if null
        }

        String action = request.getParameter("action");
        if (action == null) action = "";

        try {
            switch (action) {
                case SAVE_BUTTON_VALUE:
                    saveChanges(request, response, cart);
                    break;
                case CLEAR_BUTTON_VALUE:
                    clearCart(request, response, cart);
                    break;
                case CONFIRM_ORDER_BUTTON_VALUE:
                    confirmOrder(request, response, cart);
                    break;
                default:
                    updateOrRemoveDestination(request, response, cart);
                    break;
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error processing cart action: {0}", ex.getMessage());
            response.sendRedirect("error.jsp");
        }
    }

    private void updateOrRemoveDestination(HttpServletRequest request, HttpServletResponse response, Cart cart) throws ServletException, IOException {
        Enumeration<String> parameters = request.getParameterNames();
        int index = -1;
        while (parameters.hasMoreElements()) {
            String element = parameters.nextElement();
            if (element.contains("remove")) {
                index = Integer.parseInt(element.replace("remove", ""));
                cart.removeDestination(index); // Remove destination
            } else if (element.contains("update")) {
                index = Integer.parseInt(element.replace("update", ""));
                int newQuantity = Integer.parseInt(request.getParameter("quantity" + index));
                cart.updateQuantity(index, newQuantity); // Update destination details
            }
        }
        request.getSession().setAttribute("cart", cart);
        serveJSP(request, response, "cart.jsp");
    }

    private void saveChanges(HttpServletRequest request, HttpServletResponse response, Cart cart) throws ServletException, IOException {
        updateQuantities(request, cart);
        request.getSession().setAttribute("cart", cart);
        serveJSP(request, response, "cart.jsp");
    }

    private void clearCart(HttpServletRequest request, HttpServletResponse response, Cart cart) throws ServletException, IOException {
        cart.clear();
        request.getSession().setAttribute("cart", cart);
        serveJSP(request, response, "cart.jsp");
    }

    private void confirmOrder(HttpServletRequest request, HttpServletResponse response, Cart cart) throws IOException {
        updateQuantities(request, cart);
        request.getSession().setAttribute("cart", cart);
        response.sendRedirect("place-order"); // Redirect to place order page
    }

    private void updateQuantities(HttpServletRequest request, Cart cart) {
        for (int i = 0; i < cart.getDestinations().size(); i++) {
            int quantity = Integer.parseInt(request.getParameter("quantity" + i));
            cart.updateQuantity(i, quantity);
        }
    }

    private void serveJSP(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        request.getRequestDispatcher(page).forward(request, response);
    }
}
