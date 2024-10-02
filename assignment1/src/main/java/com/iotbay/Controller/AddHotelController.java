package com.iotbay.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iotbay.Dao.DBConnector;
import com.iotbay.Dao.DBManager;
import com.iotbay.Dao.HotelDAO;
import com.iotbay.Model.*;

public class AddHotelController extends HttpServlet{
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        HotelDAO hotelDAOManager = (HotelDAO)session.getAttribute("hotelDAOManager");

        //get the hotel data from the add form
        String name = request.getParameter("name");
        String roomType = request.getParameter("roomType");
        String roomSize = request.getParameter("roomSize");
        String city = request.getParameter("city");
        String availableBeginDate = request.getParameter("availableBeginDate");
        String availableEndDate = request.getParameter("availableEndDate");
        String img = request.getParameter("img");

        //prepare to validate the form inputs price and availability
        String enteredPrice = request.getParameter("price");
        String enteredAvailability = request.getParameter("availability");
        double price = 0;
        int availability = 0;
        boolean isPrice = isDouble(enteredPrice);
        boolean isAvailability = isInteger(enteredAvailability);
    
        //input validation, if invalid, send back inputs with error messages
        //check empty name
        if(name == null||name.equals(""))
        {
            request.setAttribute("nameErr", "Hotel name can't be empty!");
            request.setAttribute("name", name);
            request.setAttribute("price", enteredPrice);
            request.setAttribute("availability", enteredAvailability);
            request.setAttribute("img", img);
            request.setAttribute("city", city);
            request.setAttribute("availableBeginDate", availableBeginDate);
            request.setAttribute("availableEndDate", availableEndDate);

            request.getRequestDispatcher("addHotel.jsp").forward(request, response);
            return;
        }

        //check empty img
        if(img == null||img.equals(""))
        {
            request.setAttribute("imgErr", "Image can't be empty!");
            request.setAttribute("name", name);
            request.setAttribute("price", enteredPrice);
            request.setAttribute("availability", enteredAvailability);
            request.setAttribute("img", img);
            request.setAttribute("city", city);
            request.setAttribute("availableBeginDate", availableBeginDate);
            request.setAttribute("availableEndDate", availableEndDate);

            request.getRequestDispatcher("addHotel.jsp").forward(request, response);
            return;
        }

        //check if price input is not a double
        if(!isPrice)
        {
            request.setAttribute("priceErr", "Invalid price input!");
            request.setAttribute("name", name);
            request.setAttribute("price", enteredPrice);
            request.setAttribute("availability", enteredAvailability);
            request.setAttribute("img", img);
            request.setAttribute("city", city);
            request.setAttribute("availableBeginDate", availableBeginDate);
            request.setAttribute("availableEndDate", availableEndDate);

            request.getRequestDispatcher("addHotel.jsp").forward(request, response);
            return;
        }

        //check if isAvailability input is not an int
        if(!isAvailability){
            request.setAttribute("availabilityErr", "Invalid availability input!");
            request.setAttribute("name", name);
            request.setAttribute("price", enteredPrice);
            request.setAttribute("availability", enteredAvailability);
            request.setAttribute("img", img);
            request.setAttribute("city", city);
            request.setAttribute("availableBeginDate", availableBeginDate);
            request.setAttribute("availableEndDate", availableEndDate);

            request.getRequestDispatcher("addHotel.jsp").forward(request, response);
            return;
        }
        //check empty city
        if(city == null||city.equals("")){
            request.setAttribute("cityErr", "City can't be empty!");
            request.setAttribute("name", name);
            request.setAttribute("price", enteredPrice);
            request.setAttribute("availability", enteredAvailability);
            request.setAttribute("img", img);
            request.setAttribute("city", city);
            request.setAttribute("availableBeginDate", availableBeginDate);
            request.setAttribute("availableEndDate", availableEndDate);

            request.getRequestDispatcher("addHotel.jsp").forward(request, response);
            return;
        }
        //check empty date
        if(availableBeginDate == null||availableBeginDate.equals("")){
            request.setAttribute("availableBeginDateErr", "Invalid begin date value!");
            request.setAttribute("name", name);
            request.setAttribute("price", enteredPrice);
            request.setAttribute("availability", enteredAvailability);
            request.setAttribute("img", img);
            request.setAttribute("city", city);
            request.setAttribute("availableBeginDate", availableBeginDate);
            request.setAttribute("availableEndDate", availableEndDate);

            request.getRequestDispatcher("addHotel.jsp").forward(request, response);
            return;    
        }

        //check empty date
        if(availableEndDate == null||availableEndDate.equals("")){
            request.setAttribute("availableEndDateErr", "Invalid end date value!");   
            request.setAttribute("name", name);
            request.setAttribute("price", enteredPrice);
            request.setAttribute("availability", enteredAvailability);
            request.setAttribute("img", img);
            request.setAttribute("city", city);
            request.setAttribute("availableBeginDate", availableBeginDate);
            request.setAttribute("availableEndDate", availableEndDate);

            request.getRequestDispatcher("addHotel.jsp").forward(request, response);
            return; 
        }

        else{
            try{
                //Convert input strings to valid variables
                Date beginDate = Date.valueOf(availableBeginDate);
                Date endDate = Date.valueOf(availableEndDate);
                availability = Integer.parseInt(enteredAvailability);
                price = Double.parseDouble(enteredPrice);

                //check if the input numbers are valid
                if(availability <= 0){
                    request.setAttribute("availabilityErr", "Invalid availability input");
                    request.setAttribute("name", name);
                    request.setAttribute("price", enteredPrice);
                    request.setAttribute("availability", enteredAvailability);
                    request.setAttribute("img", img);
                    request.setAttribute("city", city);
                    request.setAttribute("availableBeginDate", availableBeginDate);
                    request.setAttribute("availableEndDate", availableEndDate);
        
                    request.getRequestDispatcher("addHotel.jsp").forward(request, response);
                    return; 
                }
                if(price <= 0){
                    request.setAttribute("priceErr", "Invalid price input");
                    request.setAttribute("name", name);
                    request.setAttribute("price", enteredPrice);
                    request.setAttribute("availability", enteredAvailability);
                    request.setAttribute("img", img);
                    request.setAttribute("city", city);
                    request.setAttribute("availableBeginDate", availableBeginDate);
                    request.setAttribute("availableEndDate", availableEndDate);
        
                    request.getRequestDispatcher("addHotel.jsp").forward(request, response);
                    return; 
                }

                //Finish creating operation and redirect staff to feedback jsp
                hotelDAOManager.createHotel(name, price, availability, img, roomType, roomSize, city, beginDate, endDate);
                ArrayList<Hotel> hotelList = hotelDAOManager.fetchAllHotels();
                session.setAttribute("hotelList", hotelList);
                request.getRequestDispatcher("addHotelOperation.jsp").include(request, response);
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
