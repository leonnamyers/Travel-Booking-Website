package com.iotbay.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iotbay.Dao.DBConnector;
import com.iotbay.Dao.DBManager;
import com.iotbay.Dao.FlightDAO;
import com.iotbay.Model.*;

public class AddFlightToCartController extends HttpServlet{

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        HttpSession session = request.getSession();
        FlightDAO flightDAOManager = (FlightDAO) session.getAttribute("flightDAOManager");

        //get cart and item from session
        Cart cart = (Cart) session.getAttribute("cart");
        int itemID = Integer.parseInt(request.getParameter("itemID"));

        try {
            //fetch flight as item(super class), add flight item to the cart
            //redirect back to the flight page
            Item item = flightDAOManager.fetchFlightItem(itemID);
            cart.addItemToCart(item);
            session.setAttribute("cart", cart);
            request.getRequestDispatcher("flights.jsp").include(request, response);

        }catch (SQLException e) {
            System.out.println(e);
        }
    }
    
}
