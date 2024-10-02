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
import com.iotbay.Dao.HotelDAO;
import com.iotbay.Model.*;

public class HotelCatalogueController extends HttpServlet{
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        HotelDAO hotelDAOManager = (HotelDAO) session.getAttribute("hotelDAOManager");
        try{
            //fetch current hotel list in the database, set it to display
            ArrayList<Hotel> hotelList = hotelDAOManager.fetchAllHotels();
            session.setAttribute("hotelList", hotelList);
            response.sendRedirect("hotels.jsp");
        }       
        catch(SQLException e){
            System.out.println(e);
        }
    }
}
