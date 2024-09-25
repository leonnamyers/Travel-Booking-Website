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

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String action = request.getParameter("action");
    System.out.println("Received action: " + action);  

    User user = (User) request.getSession().getAttribute("user");

    try {
        if ("loadPackages".equals(action)) {
            loadPackages(request, response);
        } else if ("deletePackage".equals(action) && user != null && user.getUserType() == UserType.STAFF) {
            System.out.println("Deleting package...");
            int itemID = Integer.parseInt(request.getParameter("packageId"));
            packageDAO.deletePackage(itemID);
            response.sendRedirect("packageBooking.jsp?action=loadPackages");
        } else if ("addPackage".equals(action) && user != null && user.getUserType() == UserType.STAFF) {
            System.out.println("Forwarding to packageForm.jsp for adding a new package");
            // Forward to the packageForm.jsp page for adding a new package
            request.getRequestDispatcher("/packageForm.jsp").forward(request, response);
        } else if ("editPackage".equals(action) && user != null && user.getUserType() == UserType.STAFF) {
            int packageId = Integer.parseInt(request.getParameter("packageId"));
            Package pkg = packageDAO.fetchPackageById(packageId);
            request.setAttribute("selectedPackage", pkg);
            System.out.println("Forwarding to packageForm.jsp for editing package: " + packageId);
            // Forward to the packageForm.jsp page for editing the package
            request.getRequestDispatcher("/packageForm.jsp").forward(request, response);
        } else {
            System.out.println("Invalid action or user not logged in, redirecting to index.jsp");
            response.sendRedirect("index.jsp");
        }
    } catch (SQLException e) {
        System.out.println("SQLException occurred: " + e.getMessage());
        throw new ServletException("Error loading/deleting package", e);
    } catch (Exception e) {
        System.out.println("General Exception occurred: " + e.getMessage());
        throw new ServletException("Error processing request", e);
    }
}


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        User user = (User) request.getSession().getAttribute("user");

        try {
            if (user != null && user.getUserType() == UserType.STAFF) {
                if ("addPackage".equals(action)) {
                    addPackage(request, response);
                } else if ("updatePackage".equals(action)) {
                    updatePackage(request, response);
                }
            } else {
                response.sendRedirect("noPermission.jsp");
            }
        } catch (SQLException e) {
            throw new ServletException("Error processing package", e);
        }
    }

    private void loadPackages(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
    System.out.println("Loading packages from database...");
    List<Package> packages = packageDAO.fetchAllPackages();
    System.out.println("Number of packages loaded: " + packages.size());
    
   
    for (Package pkg : packages) {
        System.out.println("Package: " + pkg.getName() + ", Price: " + pkg.getPrice());
    }

    request.setAttribute("packages", packages);
    request.getRequestDispatcher("packageBooking.jsp").forward(request, response);
}


    private void addPackage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
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
        response.sendRedirect("/packageBooking.jsp?action=loadPackages");

    }

    private void updatePackage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int itemID = Integer.parseInt(request.getParameter("itemID"));
        Package pkg = packageDAO.fetchAllPackages().stream()
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

            packageDAO.updatePackage(pkg.getItemID(), pkg.getName(), pkg.getPrice(), pkg.getAvailability(),
                    pkg.getImg(), pkg.getDescription(), pkg.getIntroduction(), pkg.getActivities(),
                    pkg.getTransportation(), pkg.getDining(), pkg.getSpecialOffer(), pkg.getContactName(),
                    pkg.getContactPhone());
                    response.sendRedirect("/packageBooking.jsp?action=loadPackages");

        }
    }
}
