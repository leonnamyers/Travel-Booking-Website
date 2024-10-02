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

public class AddFlightController extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        FlightDAO flightDAOManager = (FlightDAO) session.getAttribute("flightDAOManager");
        
        //get the flight data from the add form
        String name = request.getParameter("name");
        String startTime = (String)request.getParameter("startTime");
        String endTime = (String)request.getParameter("endTime");
        String departureCity = (String)request.getParameter("departureCity");
        String destinationCity = (String)request.getParameter("destinationCity");
        String img = (String)request.getParameter("img");
        String stops = (String)request.getParameter("stops");
        String seatType = (String)request.getParameter("seatType");

        //prepare to validate the form inputs price and availability
        double price = 0;
        int availability = 0;
        String enteredPrice = request.getParameter("price");
        String enteredAvailability= request.getParameter("availability");
        boolean isPrice = isDouble(enteredPrice);
        boolean isAvailability = isInteger(enteredAvailability);

       
        //input validation, if invalid, send back inputs with error messages
        //check empty name
        if(name == null||name.equals(""))
        {
            request.setAttribute("nameErr", "Company name can't be empty!");
            request.setAttribute("name", name);
            request.setAttribute("startTime", startTime);
            request.setAttribute("endTime", endTime);
            request.setAttribute("departureCity", departureCity);
            request.setAttribute("destinationCity", destinationCity);
            request.setAttribute("img", img);
            request.setAttribute("price", enteredPrice);
            request.setAttribute("availability", enteredAvailability);

            request.getRequestDispatcher("addFlight.jsp").forward(request, response);
            return;
        }
        //check empty time
        if(startTime == null || startTime.equals(""))
        {
            
            request.setAttribute("departureTimeErr", "Departure time can't be empty!");
            request.setAttribute("name", name);
            request.setAttribute("startTime", startTime);
            request.setAttribute("endTime", endTime);
            request.setAttribute("departureCity", departureCity);
            request.setAttribute("destinationCity", destinationCity);
            request.setAttribute("img", img);
            request.setAttribute("price", enteredPrice);
            request.setAttribute("availability", enteredAvailability);

            request.getRequestDispatcher("addFlight.jsp").forward(request, response);
            return;
            
        }
        //check empty time
        if(endTime == null ||endTime.equals(""))
        {
            request.setAttribute("arrivalTimeErr", "Arrival time can't be empty!");
            request.setAttribute("name", name);
            request.setAttribute("startTime", startTime);
            request.setAttribute("endTime", endTime);
            request.setAttribute("departureCity", departureCity);
            request.setAttribute("destinationCity", destinationCity);
            request.setAttribute("img", img);
            request.setAttribute("price", enteredPrice);
            request.setAttribute("availability", enteredAvailability);

            request.getRequestDispatcher("addFlight.jsp").forward(request, response);
            return;
        }
        //check empty departureCity
        if(departureCity == null||departureCity.equals(""))
        {
            request.setAttribute("departureCityErr", "Departure City can't be empty!");
            request.setAttribute("name", name);
            request.setAttribute("startTime", startTime);
            request.setAttribute("endTime", endTime);
            request.setAttribute("departureCity", departureCity);
            request.setAttribute("destinationCity", destinationCity);
            request.setAttribute("img", img);
            request.setAttribute("price", enteredPrice);
            request.setAttribute("availability", enteredAvailability);

            request.getRequestDispatcher("addFlight.jsp").forward(request, response);
            return;
        }

        //check empty destinationCity
        if(destinationCity == null||destinationCity.equals(""))
        {
            request.setAttribute("destinationCityErr", "destination City can't be empty!");
            request.setAttribute("name", name);
            request.setAttribute("startTime", startTime);
            request.setAttribute("endTime", endTime);
            request.setAttribute("departureCity", departureCity);
            request.setAttribute("destinationCity", destinationCity);
            request.setAttribute("img", img);
            request.setAttribute("price", enteredPrice);
            request.setAttribute("availability", enteredAvailability);

            request.getRequestDispatcher("addFlight.jsp").forward(request, response);
            return;
        }

        //check empty img
        if(img == null||img.equals(""))
        {
            request.setAttribute("imgErr", "image can't be empty!");
            request.setAttribute("name", name);
            request.setAttribute("startTime", startTime);
            request.setAttribute("endTime", endTime);
            request.setAttribute("departureCity", departureCity);
            request.setAttribute("destinationCity", destinationCity);
            request.setAttribute("img", img);
            request.setAttribute("price", enteredPrice);
            request.setAttribute("availability", enteredAvailability);

            request.getRequestDispatcher("addFlight.jsp").forward(request, response);
            return;
        }

        //check if price input is not a double
        if(!isPrice)
        {
            request.setAttribute("priceErr", "Invalid price input!");
            request.setAttribute("name", name);
            request.setAttribute("startTime", startTime);
            request.setAttribute("endTime", endTime);
            request.setAttribute("departureCity", departureCity);
            request.setAttribute("destinationCity", destinationCity);
            request.setAttribute("img", img);
            request.setAttribute("price", enteredPrice);
            request.setAttribute("availability", enteredAvailability);

            request.getRequestDispatcher("addFlight.jsp").forward(request, response);
            return;
        }
        //check if availability is not an int
        if(!isAvailability){
            
            request.setAttribute("availabilityErr", "Invalid availability input!");
            request.setAttribute("name", name);
            request.setAttribute("startTime", startTime);
            request.setAttribute("endTime", endTime);
            request.setAttribute("departureCity", departureCity);
            request.setAttribute("destinationCity", destinationCity);
            request.setAttribute("img", img);
            request.setAttribute("price", enteredPrice);
            request.setAttribute("availability", enteredAvailability);

            request.getRequestDispatcher("addFlight.jsp").forward(request, response);
            return;
        }

        
        else{
            
            try{
                //Create variables from the input strings to create new flight
                //The html timestamp has different format compare to sql.Timestamp
                //Formatting:
                String correctStartTime = startTime.replace("T"," ");
                correctStartTime+=":00";
                Timestamp startTimeStamp = Timestamp.valueOf(correctStartTime);

                String correctEndTime = endTime.replace("T"," ");
                correctEndTime+=":00";
                Timestamp endTimeStamp = Timestamp.valueOf(correctEndTime);

                //get availability and price after ensuring they are numbers
                availability = Integer.parseInt(request.getParameter("availability"));
                price = Double.parseDouble(request.getParameter("price"));
                
                //check if the input numbers are valid
                if(availability <= 0){
                    request.setAttribute("availabilityErr", "Invalid availability input");
                    request.setAttribute("name", name);
                    request.setAttribute("startTime", startTime);
                    request.setAttribute("endTime", endTime);
                    request.setAttribute("departureCity", departureCity);
                    request.setAttribute("destinationCity", destinationCity);
                    request.setAttribute("img", img);
                    request.setAttribute("price", enteredPrice);
                    request.setAttribute("availability", enteredAvailability);

                    request.getRequestDispatcher("addFlight.jsp").forward(request, response);
                    return;
                }
                if(price <= 0){
                    request.setAttribute("priceErr", "Invalid price input");
                    request.setAttribute("name", name);
                    request.setAttribute("startTime", startTime);
                    request.setAttribute("endTime", endTime);
                    request.setAttribute("departureCity", departureCity);
                    request.setAttribute("destinationCity", destinationCity);
                    request.setAttribute("img", img);
                    request.setAttribute("price", enteredPrice);
                    request.setAttribute("availability", enteredAvailability);

                    request.getRequestDispatcher("addFlight.jsp").forward(request, response);
                    return;
                }

                //Finish creating operation and redirect staff to feedback jsp
                flightDAOManager.createFlight(name, price, availability, img, startTimeStamp, endTimeStamp, departureCity, destinationCity, stops, seatType);
                ArrayList<Flight> flightList = flightDAOManager.fetchAllFlights();
                session.setAttribute("flightList", flightList);
                request.getRequestDispatcher("addFlightOperation.jsp").include(request, response);

            }
            catch(SQLException e){
                System.out.println(e);
            }
        }
    }

    //Validation methods
    private static boolean isDouble(String str) { 
        try {  
          Double.parseDouble(str);  
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
    }
      
    private static boolean isInteger(String str){
        try {  
            Integer.parseInt(str);  
            return true;
        } catch(NumberFormatException e){  
            return false;  
        }  
    }
    
}
