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
import com.iotbay.Model.Cruise;
import com.iotbay.Model.User;
import com.iotbay.Model.UserType;

public class EditCruiseController extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        User user = (User) request.getSession().getAttribute("user");

        if (user != null && user.getUserType() == UserType.STAFF) {  // Only staff can edit cruises
            try {
                if ("editCruise".equals(action)) {
                    int cruiseId = Integer.parseInt(request.getParameter("cruiseId"));
                    Cruise cruise = cruiseDAO.fetchCruiseById(cruiseId);
                    request.setAttribute("selectedCruise", cruise);
                    request.getRequestDispatcher("/editCruiseForm.jsp").forward(request, response);
                } else {
                    response.sendRedirect("index.jsp");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException("Error loading cruise data", e);
            }
        } else {
            response.sendRedirect("noPermission.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        if (user != null && user.getUserType() == UserType.STAFF) {  // Only staff can update cruises
            try {
                int cruiseId = Integer.parseInt(request.getParameter("cruiseId"));
                Cruise cruise = cruiseDAO.fetchCruiseById(cruiseId);

                if (cruise != null) {
                    // Update cruise details based on form input
                    cruise.setName(request.getParameter("name"));
                    cruise.setPrice(Double.parseDouble(request.getParameter("price")));
                    cruise.setAvailability(Integer.parseInt(request.getParameter("availability")));
                    cruise.setImg(request.getParameter("img"));
                    cruise.setPort(request.getParameter("port"));
                    cruise.setDescription(request.getParameter("description"));
                    cruise.setDuration(Integer.parseInt(request.getParameter("duration")));
                    cruise.setDepartureDate(request.getParameter("departureDate"));
                    cruise.setSpecialOffer(request.getParameter("specialOffer"));
                    cruise.setLocation(request.getParameter("location"));

                    // Update the cruise in the database
                    cruiseDAO.updateCruise(cruise.getItemID(), cruise.getName(), cruise.getPrice(), cruise.getAvailability(),
                            cruise.getImg(), cruise.getPort(), cruise.getDescription(), cruise.getDuration(),
                            cruise.getDepartureDate(), cruise.getSpecialOffer(), cruise.getLocation());

                    // Redirect to the cruise list page after successful update
                    response.sendRedirect("CruiseController?action=loadCruises");
                } else {
                    // If no cruise is found, show error message
                    request.setAttribute("error", "Cruise not found.");
                    request.getRequestDispatcher("editCruiseForm.jsp").forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException("Error updating cruise", e);
            }
        } else {
            response.sendRedirect("noPermission.jsp");
        }
    }
}
