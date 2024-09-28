package com.iotbay.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iotbay.Dao.DBConnector;
import com.iotbay.Dao.PackageDAO;
import com.iotbay.Model.Cart;
import com.iotbay.Model.Package;

public class PackageDetailsController extends HttpServlet {

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
        String packageIdStr = request.getParameter("packageId");
        int packageId = Integer.parseInt(packageIdStr);

        try {
          
            Package selectedPackage = packageDAO.fetchPackageById(packageId);
            request.setAttribute("selectedPackage", selectedPackage);

          
            request.getRequestDispatcher("packageDetails.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Error fetching package details", e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart); 
        }

        String packageIdStr = request.getParameter("packageId");
        int packageId = Integer.parseInt(packageIdStr);

        try {
     
            Package selectedPackage = packageDAO.fetchPackageById(packageId);

     
            cart.addItemToCart(selectedPackage);

          
            session.setAttribute("cart", cart);

           
            request.getRequestDispatcher("Cart.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Error adding package to cart", e);
        }
    }
}
