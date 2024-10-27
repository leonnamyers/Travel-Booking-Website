package com.iotbay.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iotbay.Dao.CruiseDAO;
import com.iotbay.Dao.DBConnector;
import com.iotbay.Model.Cruise;
import com.iotbay.Model.User;
import com.iotbay.Model.UserType;

public class CruiseController extends HttpServlet {

    private CruiseDAO cruiseDAO;
    private static final List<String> imagePaths = new ArrayList<>();

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

        imagePaths.add("images/Bali_island.jpg");
        imagePaths.add("images/great_barrier_reef.jpg");
        imagePaths.add("images/kangaroo_island_wildlife.jpg");
        imagePaths.add("images/Tasmania.jpg");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("Received GET action: " + action);

        User user = (User) request.getSession().getAttribute("user");
        System.out.println("User: " + (user != null ? user.getUserType() : "Anonymous"));

        try {
            if ("loadCruises".equals(action)) {
                loadCruises(request, response);
            } else if ("searchByPort".equals(action)) {
                searchCruisesByPort(request, response);
            } else if ("searchByDate".equals(action)) {
                searchCruisesByDate(request, response);  
            } else if ("addCruise".equals(action) && user != null && user.getUserType() == UserType.STAFF) {
                request.getRequestDispatcher("/cruiseForm.jsp").forward(request, response);
            } else if ("viewDetails".equals(action)) {
                int cruiseId = Integer.parseInt(request.getParameter("cruiseId"));
                Cruise cruise = cruiseDAO.fetchCruiseById(cruiseId);
                if (cruise != null) {
                    request.setAttribute("selectedCruise", cruise);
                    request.getRequestDispatcher("/cruiseDetails.jsp").forward(request, response);
                } else {
                    response.sendRedirect("cruiseBooking.jsp");
                }
            } else {
                response.sendRedirect("index.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Database error occurred");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("Received POST action: " + action);

        User user = (User) request.getSession().getAttribute("user");
        System.out.println("User: " + (user != null ? user.getUserType() : "Anonymous"));

        try {
            if (user != null && user.getUserType() == UserType.STAFF) {
                if ("addCruise".equals(action)) {
                    addCruise(request, response);
                }
            } else {
                response.sendRedirect("noPermission.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Error processing cruise");
        }
    }

    private void loadCruises(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Cruise> cruises = cruiseDAO.fetchAllCruises();
        assignImagePaths(cruises); 
        request.setAttribute("cruises", cruises);
        request.getRequestDispatcher("cruiseBooking.jsp").forward(request, response);
    }

    private void searchCruisesByPort(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String port = request.getParameter("port");
        List<Cruise> filteredCruises = cruiseDAO.searchCruisesByPort(port);
        assignImagePaths(filteredCruises); 
        request.setAttribute("cruises", filteredCruises);
        request.getRequestDispatcher("cruiseBooking.jsp").forward(request, response);
    }


    private void searchCruisesByDate(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        List<Cruise> filteredCruises = cruiseDAO.searchCruisesByDate(startDate, endDate);
        assignImagePaths(filteredCruises); 
        request.setAttribute("cruises", filteredCruises);
        request.getRequestDispatcher("cruiseBooking.jsp").forward(request, response);
    }

    private void addCruise(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int availability = Integer.parseInt(request.getParameter("availability"));
        String img = request.getParameter("img");
        String port = request.getParameter("port");
        String description = request.getParameter("description");
        int duration = Integer.parseInt(request.getParameter("duration"));
        String departureDate = request.getParameter("departureDate");
        String specialOffer = request.getParameter("specialOffer");
        String location = request.getParameter("location");

        cruiseDAO.createCruise(name, price, availability, img, port, description, duration, departureDate, specialOffer, location);

        System.out.println("Added new cruise: " + name);
        response.sendRedirect("CruiseController?action=loadCruises");
    }

 
    private void assignImagePaths(List<Cruise> cruises) {
        for (int i = 0; i < cruises.size(); i++) {
            Cruise cruise = cruises.get(i);
          
            cruise.setImg(imagePaths.get(i % imagePaths.size()));
        }
    }
}
