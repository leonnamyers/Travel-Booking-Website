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
import com.iotbay.Dao.HotelDAO;
import com.iotbay.Model.*;

public class UpdateHotelFormController extends HttpServlet{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int itemID = Integer.parseInt(request.getParameter("itemID"));
        HttpSession session = request.getSession();
        HotelDAO hotelDAOManager = (HotelDAO) session.getAttribute("hotelDAOManager");

        try{
            //set request object with attribute product of that specific ID
            //So the product data can be used in productForm, and also indicate it should perform an updating action.
            Hotel existingHotel = hotelDAOManager.fetchHotel(itemID);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("updateHotel.jsp");
            request.setAttribute("updatingHotel", existingHotel);
            dispatcher.forward(request, response);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
}
