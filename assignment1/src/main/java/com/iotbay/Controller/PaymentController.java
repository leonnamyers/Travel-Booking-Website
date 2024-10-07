package com.iotbay.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iotbay.Model.Cart;
import com.iotbay.Model.Order;
import com.iotbay.Model.User;

public class PaymentController extends HttpServlet {

    public static final String SUBMIT_PAYMENT_ACTION = "Submit";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("cart.jsp");
            return;
        }

        response.sendRedirect("Payment.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("cart.jsp");
            return;
        }

        String action = request.getParameter("action");
        if (action == null) action = "";

        try {
            if (SUBMIT_PAYMENT_ACTION.equals(action)) {
                handlePaymentSubmission(request, response, session, cart);
            } else {
                // Handle other actions if necessary
                response.sendRedirect("error.jsp"); // Redirect for unexpected actions
            }
        } catch (IOException ex) {
            // Log the exception and redirect to an error page
            response.sendRedirect("error.jsp");
        }
    }

    private void handlePaymentSubmission(HttpServletRequest request, HttpServletResponse response, HttpSession session, Cart cart) throws IOException {
        
        // Retrieve payment details
        String cardHolderName = request.getParameter("cardHolderName");
        String cardNumber = request.getParameter("cardNumber");
        String expiryDate = request.getParameter("expiryDate");
        String cvv = request.getParameter("cvv");

        // Save payment details in the session or handle them accordingly (for later)
        session.setAttribute("cardHolderName", cardHolderName);
        session.setAttribute("cardNumber", cardNumber);
        session.setAttribute("expiryDate", expiryDate);
        session.setAttribute("cvv", cvv);
        
        User user = (User) session.getAttribute("user");
        Order order = (Order) session.getAttribute("order");

        // Save order details in the session or handle them accordingly (for later)

        session.setAttribute("user", user);
        session.setAttribute("order", order);

        // Save flight details in the session
        session.setAttribute("destination", request.getParameter("destination"));
        session.setAttribute("departureDate", request.getParameter("departureDate"));
        session.setAttribute("returnDate", request.getParameter("returnDate"));
        session.setAttribute("seatType", request.getParameter("seatType"));

        // Forward the request to the PlaceOrderController for further order processing
        response.sendRedirect("FlightOrder.jsp");

    }
}
