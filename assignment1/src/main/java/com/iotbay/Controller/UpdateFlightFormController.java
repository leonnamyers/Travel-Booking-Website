package com.iotbay.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iotbay.Dao.DBConnector;
import com.iotbay.Dao.DBManager;
import com.iotbay.Dao.FlightDAO;
import com.iotbay.Model.*;

public class UpdateFlightFormController extends HttpServlet{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //getting the itemID from request
        int itemID = Integer.parseInt(request.getParameter("itemID"));
        
        HttpSession session = request.getSession();
        FlightDAO flightDAOManager = (FlightDAO) session.getAttribute("flightDAOManager");
        
        try{
            //forward request with request attribute that is an existing 
            //flight to display its data in the updateFlight form
            Flight existingFlight = flightDAOManager.fetchFlight(itemID);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("updateFlight.jsp");
            request.setAttribute("updatingFlight", existingFlight);
            dispatcher.forward(request, response);
        }
        catch(SQLException e){
            System.out.println(e);
        }

    }
    
}
