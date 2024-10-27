package com.iotbay.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iotbay.Dao.CruiseDAO;
import com.iotbay.Dao.DBConnector;
import com.iotbay.Model.Cart;
import com.iotbay.Model.Cruise;

public class CruiseDetailsController extends HttpServlet {

    private CruiseDAO cruiseDAO;

    @Override
    public void init() throws ServletException {
        try {
            DBConnector dbConnector = new DBConnector();
            Connection connection = dbConnector.openConnection();
            if (connection != null) {
                cruiseDAO = new CruiseDAO(connection);
            } else {
                throw new ServletException("Failed to connect to the database");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException("Database connection error", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cruiseIdStr = request.getParameter("cruiseId");
        int cruiseId = Integer.parseInt(cruiseIdStr);

        try {
            // Fetch selected cruise details by ID
            Cruise selectedCruise = cruiseDAO.fetchCruiseById(cruiseId);
            request.setAttribute("selectedCruise", selectedCruise);

            // Forward to cruiseDetails.jsp
            request.getRequestDispatcher("cruiseDetails.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Error fetching cruise details", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        String cruiseIdStr = request.getParameter("cruiseId");
        int cruiseId = Integer.parseInt(cruiseIdStr);

        try {
            // Fetch the cruise by ID
            Cruise selectedCruise = cruiseDAO.fetchCruiseById(cruiseId);

            // Add cruise to cart
            cart.addItemToCart(selectedCruise);

            // Update session cart and forward to Cart.jsp
            session.setAttribute("cart", cart);
            request.getRequestDispatcher("Cart.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Error adding cruise to cart", e);
        }
    }
}
