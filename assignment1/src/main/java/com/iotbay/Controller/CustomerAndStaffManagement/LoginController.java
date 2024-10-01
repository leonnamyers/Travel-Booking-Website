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

import com.iotbay.Controller.UserValidation;
import com.iotbay.Dao.DBManager;
import com.iotbay.Model.Customer;
import com.iotbay.Model.DummyUsers;
import com.iotbay.Model.Staff;
import com.iotbay.Model.User;

// Logs in the stored user by verifying username and password information against the database
// then stores the logged in user information to the session
public class LoginController extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException { 
        User user = null;
        try {
            HttpSession session = request.getSession();
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            DBManager manager = (DBManager) session.getAttribute("manager");
            UserValidation.clear(session);

            
            if (!UserValidation.isEmailValid(email)) {
                session.setAttribute("emailError", "Error: Email incorrectly formatted. Please try again.");
                request.getRequestDispatcher("login.jsp").include(request, response);
            } else if (!UserValidation.isPasswordValid(password)) {
                session.setAttribute("passwordError", "Error: Password incorrectly formatted. Please try again.");
                request.getRequestDispatcher("login.jsp").include(request, response);
            } else {

                // testing purposes - allows developers (team members) to log into the website as a particular user
                // without having to register or worry about logging in
                // will delete in r2 (when development is complete)

                if (email.equals("clerk@clerk.com")) {
                    // dummy clerk
                    session.setAttribute("user", DummyUsers.getDummyClerk());
                    session.setAttribute("welcomeMessage", "You have successfully logged in!");
                    response.sendRedirect("welcome.jsp?login=success");
                } else if (email.equals("admin@admin.com")) {
                    // dummy admin
                    session.setAttribute("user", DummyUsers.getDummyAdmin());
                    session.setAttribute("welcomeMessage", "You have successfully logged in!");
                    response.sendRedirect("welcome.jsp?login=success");
                } else if (email.equals("customer@customer.com")) {
                    // dummy customer
                    session.setAttribute("user", DummyUsers.getDummyCustomer());
                    session.setAttribute("welcomeMessage", "You have successfully logged in!");
                    response.sendRedirect("welcome.jsp?login=success");
                } else {

                    // checks if the user exists in the db and that the email and password combination is correct
                    // directs to welcome page if successful
                    // error message if unsuccessful
                
                    user = manager.userLogin(email, password, session.getId());
                if (user != null) {
                    switch(user.getUserType()) {
                        case CUSTOMER:
                        session.setAttribute("user", (Customer) user);
                        break;
                        case STAFF:
                        session.setAttribute("user", (Staff) user);
                        default:
                            break;
                    }
                    session.setAttribute("welcomeMessage", "You have successfully logged in!");
                    response.sendRedirect("welcome.jsp?login=success");
                    
                } else {
                    session.setAttribute("loginError", "User cannot be found. Please try again.");
                    request.getRequestDispatcher("login.jsp?login=failure").include(request, response);
                }
            }
        }

     } catch (SQLException ex) { 
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
}

