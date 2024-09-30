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
import com.iotbay.Dao.HotelDAO;
import com.iotbay.Model.*;

public class FilteringHotelController extends HttpServlet{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            HttpSession session = request.getSession();
            HotelDAO hotelDAOManager = (HotelDAO) session.getAttribute("hotelDAOManager");
            String city = request.getParameter("city");
            String checkInTime = request.getParameter("checkInTime");
            String checkOutTime = request.getParameter("checkOutTime");
            String roomType = request.getParameter("roomType");
            String roomSize = request.getParameter("roomSize");

            if(checkInTime == null || checkInTime.equals("")){
                request.setAttribute("checkInTimeErr", "Check-in time can't be empty!");
                request.setAttribute("city", city);
                request.setAttribute("roomType", roomType);
                request.setAttribute("roomSize", roomSize);
                request.getRequestDispatcher("hotels.jsp").forward(request, response);
                return;
            }

            if(checkOutTime == null || checkOutTime.equals("")){
                request.setAttribute("checkOutTimeErr", "Check-out time can't be empty!");
                request.setAttribute("city", city);
                request.setAttribute("roomType", roomType);
                request.setAttribute("roomSize", roomSize);
                request.getRequestDispatcher("hotels.jsp").forward(request, response);
                return;
            }
            else{

            try{
                session.setAttribute("checkInTime", checkInTime);
                session.setAttribute("checkOutTime", checkOutTime);
                request.setAttribute("city", city);
                request.setAttribute("roomType", roomType);
                request.setAttribute("roomSize", roomSize);
                ArrayList<Hotel> hotelList = hotelDAOManager.fetchFilteredHotel(roomType, roomSize, city, checkInTime, checkOutTime);
                session.setAttribute("hotelList", hotelList);
                request.getRequestDispatcher("hotels.jsp").include(request, response);
            }
            catch(SQLException e){
                System.out.println(e);
            }
        }
    }
}
