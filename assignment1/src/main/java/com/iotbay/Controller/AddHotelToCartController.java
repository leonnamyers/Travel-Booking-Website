package com.iotbay.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.UUID;
import java.time.*;
import java.time.temporal.ChronoUnit;

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
        //get cart and item from session
        Cart cart = (Cart) session.getAttribute("cart");
        int itemID = Integer.parseInt(request.getParameter("itemID"));
        //get session object booking time
        String checkInTime = (String)session.getAttribute("checkInTime");
        String checkOutTime = (String)session.getAttribute("checkOutTime");
        //if customer haven't set booking time, redirect them back to feedback page
        //tell them to set booking time first
        if(checkInTime == null||checkOutTime == null||checkInTime.equals("")||checkOutTime.equals("")){
            request.getRequestDispatcher("hotelBookingDateErr.jsp").forward(request, response);
            return;
        }
        try {
            //convert input strings to valid variables
            Date checkInDate = Date.valueOf(checkInTime);
            Date checkOutDate = Date.valueOf(checkOutTime);
            //calculate day duration between the dates
            LocalDate inLocalDate = checkInDate.toLocalDate();
            LocalDate outLocalDate = checkOutDate.toLocalDate();
            double daysBetween = ChronoUnit.DAYS.between(inLocalDate, outLocalDate)+1;
    
            //get skeleton hotel
            Hotel hotel = hotelDAOManager.fetchHotel(itemID);

            //create unique ID to fetch after creation
            //calculate price by the unit price of skeleton hotel
            int ID = generateUniqueId();
            double price = hotel.getPrice()*daysBetween;
            
            //create CustomerHotel (the booking order)
            //add it to the cart, and redirect back to hotel page
            hotelDAOManager.createCustomerHotel(ID, hotel.getName(), price, hotel.getAvailability(), hotel.getImg(), checkInDate, checkOutDate, hotel.getRoomType(), hotel.getRoomSize(), hotel.getCity());
            Item customerHotel = hotelDAOManager.fetchCustomerHotelItem(ID);
            cart.addItemToCart(customerHotel);
            session.setAttribute("cart", cart);
            request.getRequestDispatcher("hotels.jsp").include(request, response);

        }catch (SQLException e) {
            System.out.println(e);
        }
    }
    //unique id generation methods, probability of duplication is close to 0
    private int generateUniqueId() {      
        UUID idOne = UUID.randomUUID();
        String str=""+idOne;        
        int uid=str.hashCode();
        String filterStr=""+uid;
        str=filterStr.replaceAll("-", "");
        return Integer.parseInt(str);
    }
}
