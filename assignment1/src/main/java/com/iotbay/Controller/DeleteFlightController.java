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

public class DeleteFlightController extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //get device id for deletion
        int itemID = Integer.parseInt(request.getParameter("itemID"));
        HttpSession session = request.getSession();
        FlightDAO flightDAOManager = (FlightDAO) session.getAttribute("flightDAOManager");
        
        try{
            //delete and redirect staff back to the list of devices
            flightDAOManager.deleteFlight(itemID);
            ArrayList<Flight> flightList = flightDAOManager.fetchAllFlights();
            session.setAttribute("flightList", flightList);
            response.sendRedirect("deleteFlight.jsp");
        }
        catch(SQLException e){
            System.out.println(e);
        }

    }
    
}
