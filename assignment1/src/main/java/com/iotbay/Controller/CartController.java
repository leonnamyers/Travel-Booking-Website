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

        if (action != null) {
            if (action.equals("Place order")) {
                // Ensure the cart is not empty before proceeding to payment
                if (cart != null && !cart.isEmpty()) {
                    // Redirect or forward to payment.jsp
                    request.getRequestDispatcher("payment.jsp").forward(request, response);
                } else {
                    // If cart is empty, show error
                    response.sendRedirect("error.jsp");
                }
            } else if (action.equals("Clear cart")) {
                // Handle clear cart action
                cart.clear();
                response.sendRedirect("cart.jsp");
            }
        }


        try {
            switch (action) {
                case CLEAR_BUTTON_VALUE:
                    clearCart(request, response, cart);
                    break;
                case PLACE_ORDER_BUTTON_VALUE:
                    confirmOrder(request, response, cart);
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

    private void confirmOrder(HttpServletRequest request, HttpServletResponse response, Cart cart) throws IOException {
        //updateQuantities(request, cart);

        // Create Order
        Order order = new Order();
        order.setCart((Cart) request.getAttribute("cart"));
        order.setCustomer((Customer) request.getAttribute("user"));

        // DAO stuff using Order

        request.getSession().setAttribute("cart", cart);
        response.sendRedirect("payment.jsp"); // Redirect to payment page
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
        cart.deleteItem(index);
        request.getSession().setAttribute("cart", cart);
        serveJSP(request, response, "cart.jsp");
    }

    private void serveJSP(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        request.getRequestDispatcher(page).forward(request, response);
    }

}
