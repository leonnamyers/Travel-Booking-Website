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
import com.iotbay.Model.Package;
import com.iotbay.Model.User;
import com.iotbay.Model.UserType;

public class EditPackageController extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        User user = (User) request.getSession().getAttribute("user");

        if (user != null && user.getUserType() == UserType.STAFF) {
            try {
                if ("editPackage".equals(action)) {
                    int packageId = Integer.parseInt(request.getParameter("packageId"));
                    Package pkg = packageDAO.fetchPackageById(packageId);
                    request.setAttribute("selectedPackage", pkg);
                    request.getRequestDispatcher("/editPackageForm.jsp").forward(request, response);
                } else {
                    response.sendRedirect("index.jsp");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException("Error loading package data", e);
            }
        } else {
            response.sendRedirect("noPermission.jsp");
        }
    }

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    User user = (User) request.getSession().getAttribute("user");

    if (user != null && user.getUserType() == UserType.STAFF) {
        try {
            int packageId = Integer.parseInt(request.getParameter("packageId"));
            Package pkg = packageDAO.fetchPackageById(packageId);

            if (pkg != null) {
            
                pkg.setName(request.getParameter("name"));
                pkg.setPrice(Double.parseDouble(request.getParameter("price")));
                pkg.setAvailability(Integer.parseInt(request.getParameter("availability")));
                pkg.setImg(request.getParameter("img"));
                pkg.setDescription(request.getParameter("description"));
                pkg.setIntroduction(request.getParameter("introduction"));
                pkg.setActivities(request.getParameter("activities"));
                pkg.setTransportation(request.getParameter("transportation"));
                pkg.setDining(request.getParameter("dining"));
                pkg.setSpecialOffer(request.getParameter("specialOffer"));
                pkg.setContactName(request.getParameter("contactName"));
                pkg.setContactPhone(request.getParameter("contactPhone"));

                packageDAO.updatePackage(pkg.getItemID(), pkg.getName(), pkg.getPrice(), pkg.getAvailability(),
                        pkg.getImg(), pkg.getDescription(), pkg.getIntroduction(), pkg.getActivities(),
                        pkg.getTransportation(), pkg.getDining(), pkg.getSpecialOffer(), pkg.getContactName(),
                        pkg.getContactPhone());

  
                response.sendRedirect("PackageController?action=loadPackages");
            } else {
         
                request.setAttribute("error", "Package not found.");
                request.getRequestDispatcher("editPackageForm.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Error updating package", e);
        }
    } else {
        response.sendRedirect("noPermission.jsp");
    }
}

}
