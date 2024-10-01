package com.iotbay.Controller.CustomerAndStaffManagement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iotbay.Dao.DBManager;
import com.iotbay.Model.User;

// Logic to unregister/delete an account. This redirects to the index page but with a special unregister success message.
public class UnregisterController extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException { 
        User user = null;
        try {
            HttpSession session = request.getSession();
            DBManager manager = (DBManager) session.getAttribute("manager");
            user = (User) session.getAttribute("user");
            manager.removeUser(user);
            session.invalidate();
            response.sendRedirect("index.jsp?unregister=true");

        } catch (Exception ex) {
            Logger.getLogger(UnregisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
