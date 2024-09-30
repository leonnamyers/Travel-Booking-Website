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
        String departureCity = (String)request.getParameter("departure");
        String destinationCity = (String)request.getParameter("destination");

        String departureTime = (String)request.getParameter("departureTime");

        String seatType = request.getParameter("seats");

        
        try{
            ArrayList<Flight> flightList = flightDAOManager.fetchFilteredFlights(departureCity, destinationCity, departureTime, seatType);
            session.setAttribute("flightList", flightList);
            request.setAttribute("departureCity", departureCity);
            request.setAttribute("destinationCity", destinationCity);
            request.setAttribute("departureTime", departureTime);
            request.setAttribute("seatType", seatType);
            request.getRequestDispatcher("flights.jsp").include(request, response);
        }
        catch(SQLException e){
            System.out.println(e);
        }

    }
    
}
