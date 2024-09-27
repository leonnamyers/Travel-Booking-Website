package com.iotbay.Controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.sql.Date;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iotbay.Dao.DBConnector;
import com.iotbay.Dao.DBManager;
import com.iotbay.Dao.FlightDAO;
import com.iotbay.Model.*;

public class FilteringFlightController extends HttpServlet{

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        FlightDAO flightDAOManager = (FlightDAO) session.getAttribute("flightDAOManager");
        String filtDepCity = (String)request.getParameter("departure");
        String filtDesCity = (String)request.getParameter("destination");

        String filtDepTime = (String)request.getParameter("departureTime");

        String filtSeatType = request.getParameter("seats");

        
        try{
            ArrayList<Flight> flightList = flightDAOManager.fetchFilteredFlights(filtDepCity, filtDesCity, filtDepTime, filtSeatType);
            session.setAttribute("flightList", flightList);

            request.setAttribute("departureTime", filtDepTime);
            request.getRequestDispatcher("flights.jsp").include(request, response);
        }
        catch(SQLException e){
            System.out.println(e);
        }

    }
    
}
