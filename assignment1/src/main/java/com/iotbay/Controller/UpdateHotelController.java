package com.iotbay.Controller;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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
import com.iotbay.Dao.HotelDAO;
import com.iotbay.Model.*;

public class UpdateHotelController extends HttpServlet{
       
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        HotelDAO hotelDAOManager = (HotelDAO)session.getAttribute("hotelDAOManager");
        int itemID = Integer.parseInt(request.getParameter("itemID"));
        String name = request.getParameter("name");
        String roomType = request.getParameter("roomType");
        String roomSize = request.getParameter("roomSize");
        String city = request.getParameter("city");
        String availableBeginDate = request.getParameter("availableBeginDate");
        String availableEndDate = request.getParameter("availableEndDate");
        String img = request.getParameter("img");

        String enteredPrice = request.getParameter("price");
        String enteredAvailability = request.getParameter("availability");
        double price = 0;
        int availability = 0;
        boolean isPrice = isDouble(request.getParameter("price"));
        boolean isAvailability = isInteger(request.getParameter("availability"));
    
        //check empty name
        if(name == null||name.equals(""))
        {
            request.setAttribute("nameErr", "Hotel name can't be empty!");
            request.getRequestDispatcher("/UpdateHotelFormController?itemID=" + itemID).forward(request, response);
            response.sendRedirect("updateHotel.jsp");
        }
        if(img == null||img.equals(""))
        {
            request.setAttribute("imgErr", "Image can't be empty!");
            request.getRequestDispatcher("/UpdateHotelFormController?itemID=" + itemID).forward(request, response);
            response.sendRedirect("updateHotel.jsp");
        }

        //check if price input is not a number
        if(!isPrice)
        {
            request.setAttribute("priceErr", "Invalid price input!");
            request.getRequestDispatcher("/UpdateHotelFormController?itemID=" + itemID).forward(request, response);
            response.sendRedirect("updateHotel.jsp");
        }

        if(!isAvailability){
            request.setAttribute("availabilityErr", "Invalid availability input!");
            request.getRequestDispatcher("/UpdateHotelFormController?itemID=" + itemID).forward(request, response);
            response.sendRedirect("updateHotel.jsp");
        }

        if(city == null||city.equals("")){
            request.setAttribute("cityErr", "City can't be empty!");
            request.getRequestDispatcher("/UpdateHotelFormController?itemID=" + itemID).forward(request, response);
            response.sendRedirect("updateHotel.jsp");
        }
        if(availableBeginDate == null||availableBeginDate.equals("")){
            request.setAttribute("availableBeginDateErr", "Invalid begin date value!");
            request.getRequestDispatcher("/UpdateHotelFormController?itemID=" + itemID).forward(request, response);
            response.sendRedirect("updateHotel.jsp");  
        }
        if(availableEndDate == null||availableEndDate.equals("")){
            request.setAttribute("availableEndDateErr", "Invalid end date value!");   
            request.getRequestDispatcher("/UpdateHotelFormController?itemID=" + itemID).forward(request, response);
            response.sendRedirect("updateHotel.jsp");
        }
        else{
            try{
                Date beginDate = Date.valueOf(availableBeginDate);
                Date endDate = Date.valueOf(availableEndDate);
                availability = Integer.parseInt(enteredAvailability);
                price = Double.parseDouble(enteredPrice);

                if(availability <= 0){
                    request.setAttribute("availabilityErr", "Invalid availability input");
                    request.getRequestDispatcher("/UpdateHotelFormController?itemID=" + itemID).forward(request, response);
                    response.sendRedirect("updateHotel.jsp");
                }
                if(price <= 0){
                    request.setAttribute("priceErr", "Invalid price input");
                    request.getRequestDispatcher("/UpdateHotelFormController?itemID=" + itemID).forward(request, response);
                    response.sendRedirect("updateHotel.jsp");
                }
                hotelDAOManager.updateHotel(itemID, name, price, availability, img, roomType, roomSize, city, beginDate, endDate);
                ArrayList<Hotel> hotelList = hotelDAOManager.fetchAllHotels();
                session.setAttribute("hotelList", hotelList);
                request.getRequestDispatcher("updateHotelOperation.jsp").include(request, response);
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
