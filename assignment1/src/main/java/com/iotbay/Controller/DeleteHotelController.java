package com.iotbay.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iotbay.Dao.HotelDAO;
import com.iotbay.Model.Hotel;

public class DeleteHotelController extends HttpServlet{
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get item from hidden field in the hotel list  
        int itemID = Integer.parseInt(request.getParameter("itemID"));
        HttpSession session = request.getSession();
        HotelDAO hotelDAOManager = (HotelDAO) session.getAttribute("hotelDAOManager");
        
        try{
            //finish delete operation using HotelDAO, redirect to feedback page
            hotelDAOManager.deleteHotel(itemID);
            ArrayList<Hotel> hotelList = hotelDAOManager.fetchAllHotels();
            session.setAttribute("hotelList", hotelList);
            response.sendRedirect("deleteHotel.jsp");
        }
        catch(SQLException e){
            System.out.println(e);
        }

    }
}
