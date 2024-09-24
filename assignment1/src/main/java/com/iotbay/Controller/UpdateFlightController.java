package com.iotbay.Controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iotbay.Dao.DBConnector;
import com.iotbay.Dao.DBManager;
import com.iotbay.Dao.FlightDAO;
import com.iotbay.Model.*;

public class UpdateFlightController extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        FlightDAO flightDAOManager = (FlightDAO) session.getAttribute("flightDAOManager");
        
        //get the product data from request object 
        int itemID = Integer.parseInt(request.getParameter("itemID"));
        String name = request.getParameter("name");

        String startTime = (String)request.getParameter("startTime");
        String correctStartTime = startTime.replace("T"," ");
        correctStartTime+=":00";
        Timestamp startTimeStamp = Timestamp.valueOf(correctStartTime);

        String endTime = (String)request.getParameter("endTime");
        String correctEndTime = endTime.replace("T"," ");
        correctEndTime+=":00";
        Timestamp endTimeStamp = Timestamp.valueOf(correctEndTime);
        
        String departureCity = (String)request.getParameter("departureCity");
        String destinationCity = (String)request.getParameter("destinationCity");
        String stops = (String)request.getParameter("stops");
        String seatType = (String)request.getParameter("seatType");
        String img = (String)request.getParameter("img");
        
        double price = 0;
        int availability = 0;
        
        boolean isPrice = isDouble(request.getParameter("price"));
        boolean isAvailability = isInteger(request.getParameter("availability"));

       

        //check empty name
        if(name == null||name.equals(""))
        {
            request.setAttribute("nameErr", "Company name can't be empty!");
            request.getRequestDispatcher("/updateFlightFormServlet?itemID=" + itemID).include(request, response);
        }
        //check empty time
        if(startTime == null || startTime.equals(""))
        {
            request.setAttribute("departureTimeErr", "Departure time can't be empty!");
            request.getRequestDispatcher("/updateFlightFormServlet?itemID=" + itemID).include(request, response);
        }
        //check empty time
        if(endTime == null ||endTime.equals(""))
        {
            request.setAttribute("arrivalTimeErr", "Arrival time can't be empty!");
            request.getRequestDispatcher("/updateFlightFormServlet?itemID=" + itemID).include(request, response);
        }
        //check if price input is not a number
        if(!isPrice)
        {
            request.setAttribute("priceErr", "Invalid price input!");
            request.getRequestDispatcher("/updateFlightFormServlet?itemID=" + itemID).include(request, response);
        }
        //check if availability is not a int
        if(!isAvailability){
            
            request.setAttribute("availabilityErr", "Invalid availability input!");
            request.getRequestDispatcher("/updateFlightFormServlet?itemID=" + itemID).include(request, response);
        }

  
        else{
            
            try{
                //get availability and price after ensuring they are numbers
                availability = Integer.parseInt(request.getParameter("availability"));
                price = Double.parseDouble(request.getParameter("price"));
                
                if(availability < 0){
                    request.setAttribute("availabilityError", "Availability input must be 0 or more");
                    request.getRequestDispatcher("/updateFlightFormServlet?itemID=" + itemID).include(request, response);
                }
                if(price < 0){
                    request.setAttribute("priceError", "Price input must be 0 or more");
                    request.getRequestDispatcher("/updateFlightFormServlet?itemID=" + itemID).include(request, response);
                }
                //Finish updating operation and redirect staff back to the list of devices
                
                flightDAOManager.updateFlight(itemID, name, price, availability, img, startTimeStamp, endTimeStamp, departureCity, destinationCity, stops, seatType);
                request.getRequestDispatcher("/ConnServlet").forward(request, response);
                response.sendRedirect("flights.jsp");

            }
            catch(SQLException e){
                System.out.println(e);
            }
        }
    }

    private static boolean isDouble(String str) { 
        try {  
          Double.parseDouble(str);  
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
      }
      
      //Number checking method for stock
      private static boolean isInteger(String str){
        try {  
          Integer.parseInt(str);  
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
      }
    
}
