package com.iotbay.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iotbay.Dao.DBConnector;
import com.iotbay.Dao.PackageDAO;
import com.iotbay.Model.Package;
import com.iotbay.Model.User;
import com.iotbay.Model.UserType;

public class PackageController extends HttpServlet {

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

    // mvn clean install jetty:run -Dorg.eclipse.jetty.LEVEL=DEBUG

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("Received GET action: " + action);

        User user = (User) request.getSession().getAttribute("user");
        System.out.println("User: " + (user != null ? user.getUserType() : "Anonymous"));

        try {
            if ("loadPackages".equals(action)) {
                loadPackages(request, response);
            } else if ("addPackage".equals(action) && user != null && user.getUserType() == UserType.STAFF) {
                request.getRequestDispatcher("/packageForm.jsp").forward(request, response);
            } else if ("viewDetails".equals(action)) {
                int packageId = Integer.parseInt(request.getParameter("packageId"));
                Package pkg = packageDAO.fetchPackageById(packageId);
                request.setAttribute("selectedPackage", pkg);
                request.getRequestDispatcher("/packageDetails.jsp").forward(request, response);  
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
                if ("addPackage".equals(action)) {
                    addPackage(request, response);
                }
            } else {
                response.sendRedirect("noPermission.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Error processing package");
}
}

    private void loadPackages(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Package> packages = packageDAO.fetchAllPackages();
        System.out.println("Loaded " + packages.size() + " packages");  // 打印加载的包数量
        request.setAttribute("packages", packages);
        request.getRequestDispatcher("packageBooking.jsp").forward(request, response);
    }

    private void addPackage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
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

        packageDAO.createPackage(
                pkg.getName(), pkg.getPrice(), pkg.getAvailability(), pkg.getImg(), pkg.getDescription(),
                pkg.getIntroduction(), pkg.getActivities(), pkg.getTransportation(), pkg.getDining(),
                pkg.getSpecialOffer(), pkg.getContactName(), pkg.getContactPhone()
        );

        System.out.println("Added new package: " + pkg.getName());  

        response.sendRedirect("PackageController?action=loadPackages");
    }

  
}









