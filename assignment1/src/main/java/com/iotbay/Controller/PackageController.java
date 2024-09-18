package com.iotbay.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iotbay.Dao.DBManager;
import com.iotbay.Model.Package;
import com.iotbay.Model.Staff;
import com.iotbay.Model.User;
import com.iotbay.Model.UserType;

public class PackageController extends HttpServlet {

    private DBManager dbManager;

    @Override
    public void init() throws ServletException {
        Connection connection = (Connection) getServletContext().getAttribute("dbConnection");
        try {
            dbManager = new DBManager(connection);
        } catch (SQLException e) {
            throw new ServletException("Failed to initialize DBManager", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        User user = (User) request.getSession().getAttribute("user");

  
        if (user != null && user.getUserType() == UserType.STAFF) {
            Staff staff = (Staff) user;
            if (staff.getStaffTypeID() == 1) { 

                if ("addPackage".equals(action)) {
             
                    try {
                        Package pkg = new Package();
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

                        dbManager.addPackage(pkg);
                        response.sendRedirect("packageBooking.jsp?action=loadPackages");
                    } catch (SQLException e) {
                        throw new ServletException("Error adding package", e);
                    }
                } else if ("updatePackage".equals(action)) {
                    try {
                        int itemID = Integer.parseInt(request.getParameter("itemID"));
                        Package pkg = dbManager.getAllPackages().stream()
                                .filter(p -> p.getItemID() == itemID)
                                .findFirst()
                                .orElse(null);

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

                            dbManager.updatePackage(pkg);
                            response.sendRedirect("packageBooking.jsp?action=loadPackages");
                        }
                    } catch (SQLException e) {
                        throw new ServletException("Error updating package", e);
                    }
                } else if ("deletePackage".equals(action)) {
                    try {
                        int itemID = Integer.parseInt(request.getParameter("itemID"));
                        dbManager.deletePackage(itemID);
                        response.sendRedirect("packageBooking.jsp?action=loadPackages");
                    } catch (SQLException e) {
                        throw new ServletException("Error deleting package", e);
                    }
                }
            } else {
                response.sendRedirect("noPermission.jsp"); 
            }
        } else {
            if ("loadPackages".equals(action)) {
                try {
                    List<Package> packages = dbManager.getAllPackages();
                    request.setAttribute("packages", packages);
                    request.getRequestDispatcher("packageBooking.jsp").forward(request, response);
                } catch (SQLException e) {
                    throw new ServletException("Error loading packages", e);
                }
            } else {
                response.sendRedirect("index.jsp");
            }
        }
    }
}
