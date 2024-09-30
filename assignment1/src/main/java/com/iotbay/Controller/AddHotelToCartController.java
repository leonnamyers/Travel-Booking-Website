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

public class AddHotelToCartController extends HttpServlet{
        
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        HotelDAO hotelDAOManager = (HotelDAO)session.getAttribute("hotelDAOManager");
        String checkInTime = (String)session.getAttribute("checkInTime");
        String checkOutTime = (String)session.getAttribute("checkOutTime");
        if(checkInTime == null||checkOutTime == null||checkInTime.equals("")||checkOutTime.equals("")){
            request.getRequestDispatcher("hotelBookingDateErr.jsp").forward(request, response);
            return;
        }
    }
}
