package com.iotbay.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iotbay.Dao.DBConnector;
import com.iotbay.Dao.PackageDAO;
import com.iotbay.Model.User;
import com.iotbay.Model.UserType;

public class DeletePackageController extends HttpServlet {

    private PackageDAO packageDAO;

    @Override
    public void init() throws ServletException {
        try {
            DBConnector dbConnector = new DBConnector();
            Connection connection = dbConnector.openConnection();
            if (connection != null) {
                packageDAO = new PackageDAO(connection);
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
                int packageId = Integer.parseInt(request.getParameter("packageId"));

                if (packageDAO.fetchPackageById(packageId) != null) {
                    packageDAO.deletePackage(packageId);
                    System.out.println("Deleted package with ID: " + packageId);
                    response.sendRedirect("PackageController?action=loadPackages");
                } else {
                    request.setAttribute("error", "Package not found.");
                    request.getRequestDispatcher("packageBooking.jsp").forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException("Error deleting package", e);
            }
        } else {
            response.sendRedirect("noPermission.jsp");
        }
    }
}
