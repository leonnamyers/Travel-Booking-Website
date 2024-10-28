package com.iotbay.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iotbay.Dao.CruiseDAO;
import com.iotbay.Dao.DBConnector;
import com.iotbay.Model.User;
import com.iotbay.Model.UserType;

public class DeleteCruiseController extends HttpServlet {

    private CruiseDAO cruiseDAO;

    @Override
    public void init() throws ServletException {
        try {
            DBConnector dbConnector = new DBConnector();
            Connection connection = dbConnector.openConnection();
            if (connection != null) {
                cruiseDAO = new CruiseDAO(connection);
            } else {
                throw new ServletException("Failed to connect to the database");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException("Database connection error", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");

   
        if (user != null && user.getUserType() == UserType.STAFF) {
            try {
           
                int cruiseId = Integer.parseInt(request.getParameter("cruiseId"));

     
                if (cruiseDAO.fetchCruiseById(cruiseId) != null) {
                
                    cruiseDAO.deleteCruise(cruiseId);
                    System.out.println("Deleted cruise with ID: " + cruiseId);
       
                    response.sendRedirect("CruiseController?action=loadCruises");
                } else {
            
                    request.setAttribute("error", "Cruise not found.");
                    request.getRequestDispatcher("cruiseBooking.jsp").forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException("Error deleting cruise", e);
            }
        } else {
          
            response.sendRedirect("noPermission.jsp");
        }
    }
}
