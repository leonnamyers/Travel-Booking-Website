package com.iotbay.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iotbay.Dao.AdminStaffUserMgmtDAO;
import com.iotbay.Dao.DBManager;
import com.iotbay.Dao.DBConnector;
import com.iotbay.Model.Customer;
import com.iotbay.Model.Staff;
import com.iotbay.Model.User;
import com.iotbay.Model.Address;
import com.iotbay.Model.UserType;

@WebServlet(name = "AdminStaffUserMgmtController", urlPatterns = {"/AdminStaffUserMgmtController"})
public class AdminStaffUserMgmtController extends HttpServlet {
    private DBConnector connector;
    private Connection conn;
    private AdminStaffUserMgmtDAO manager;
    private DBManager dbManager;
    

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            connector = new DBConnector();
            conn = connector.openConnection();
            manager = new AdminStaffUserMgmtDAO(conn);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException("Error initializing servlet", ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loggedUser = (User) session.getAttribute("user");
        
        if (loggedUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }
    
        ArrayList<User> users = new ArrayList<>();
        
        try {
            if (loggedUser.getUserType() == UserType.CUSTOMER) {
                // Customer-specific logic if needed
            } else if (loggedUser.getUserType() == UserType.STAFF) {
                Staff staffUser = (Staff) loggedUser;
                int staffTypeID = staffUser.getStaffTypeID();
                
                if (staffTypeID == 2) { // SYSTEM_ADMIN
                    users = manager.getAllUsers();
                } else if (staffTypeID == 1) { // CLERK
                    users = manager.getCustomers();
                } else {
                    request.setAttribute("errorMessage", "Invalid staff type.");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                    return;
                }
            } else {
                request.setAttribute("errorMessage", "Invalid user type.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }
    
            request.setAttribute("users", users);
            request.getRequestDispatcher("userListPanel.jsp").forward(request, response);
        } catch (SQLException ex) {
            request.setAttribute("errorMessage", "Error retrieving users.");
            request.getRequestDispatcher("userListPanel.jsp").forward(request, response);
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String email = request.getParameter("email");

        try {
            switch (action) {
                case "addUser":
                    handleAddUserAction(request, response);
                    break;
                case "search":
                    handleSearchAction(request, response);
                    break;
                case "viewProfile":
                    handleViewAction(request, response, email);
                    break;
                case "update":
                    handleUpdateAction(request, response, email);
                    break;
                case "delete":
                    handleDeleteAction(request, response, email);
                    break;
                case "viewList":
                    doGet(request, response);
                    break;
                default:
                    request.setAttribute("errorMessage", "Invalid action.");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminStaffUserMgmtController.class.getName()).log(Level.SEVERE, "Error processing request", ex);
            request.setAttribute("errorMessage", "Database error occurred.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private void handleAddUserAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        String registeredUserType = request.getParameter("userType");
        String email = request.getParameter("email");
        String password = request.getParameter("password"); 
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        
        DBManager manager = (DBManager) session.getAttribute("manager");
    
        UserValidation.clear(session);
    
        if (email == null || password == null || firstName == null || lastName == null) {
            request.setAttribute("errorMessage", "Required fields are missing.");
            request.getRequestDispatcher("registerPanel.jsp").forward(request, response);
            return;
        }
    
        if (manager.isDuplicateEmail(email)) {
            session.setAttribute("duplicateEmail", "Error: Email is already registered.");
            forwardWithError(request, response, session);
            return;
        } else if (!UserValidation.isEmailValid(email)) {
            session.setAttribute("emailError", "Error: Email incorrectly formatted.");
            forwardWithError(request, response, session);
            return;
        } else if (!UserValidation.isPasswordValid(password)) {
            session.setAttribute("passwordError", "Error: Password incorrectly formatted.");
            forwardWithError(request, response, session);
            return;
        } else if (!UserValidation.isFieldAlphaNum(firstName)) {
            session.setAttribute("firstNameError", "Error: First name incorrectly formatted.");
            forwardWithError(request, response, session);
            return;
        } else if (!UserValidation.isFieldAlphaNum(lastName)) {
            session.setAttribute("lastNameError", "Error: Last name incorrectly formatted.");
            forwardWithError(request, response, session);
            return;
        }
    
        if ("customer".equalsIgnoreCase(registeredUserType)) {
            String streetAddress = request.getParameter("street_address");
            int postcode = Integer.parseInt(request.getParameter("postcode"));
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            int homePhone = Integer.parseInt(request.getParameter("phone_number"));
            int mobilePhone = Integer.parseInt(request.getParameter("mobile_number"));
    
            Address address = new Address(streetAddress, postcode, city, state);
            Customer customer = new Customer(email, password, firstName, lastName, address);
            customer.setHomePhoneNumber(homePhone);
            customer.setMobilePhoneNumber(mobilePhone);
    
            manager.addCustomer(customer, session.getId());
    
        } else if ("staff".equalsIgnoreCase(registeredUserType)) {
            int staffID = Integer.parseInt(request.getParameter("staff_id"));
            int staffTypeID = Integer.parseInt(request.getParameter("staff_type"));
    
            Staff staff = new Staff(email, password, firstName, lastName);
            staff.setStaffID(staffID);
            staff.setStaffTypeID(staffTypeID);
    
            // Check for duplicate staff ID
            if (manager.isDuplicateStaffID(String.valueOf(staffID))) {
                session.setAttribute("duplicateStaffID", "Error: Staff ID already exists.");
                forwardWithError(request, response, session);
                return;
            }
    
            manager.addStaff(staff, session.getId());
        }
    
        response.sendRedirect("AdminStaffUserMgmtController");
    }

    private void handleSearchAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        User loggedUser = (User) session.getAttribute("user");
    
        if (loggedUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }
    
        String fullName = request.getParameter("fullName");
        ArrayList<User> users = new ArrayList<>();
    
        if (loggedUser.getUserType() == UserType.STAFF) {
            Staff staffUser = (Staff) loggedUser;
            int staffTypeID = staffUser.getStaffTypeID();
    
            if (staffTypeID == 2) { // SYSTEM_ADMIN
                if (fullName != null && !fullName.isEmpty()) {
                    users = manager.searchAllUsers(fullName);
                } else {
                    users = manager.getAllUsers();
                }
            } else if (staffTypeID == 1) { // CLERK
                if (fullName != null && !fullName.isEmpty()) {
                    users = manager.searchCustomers(fullName);
                } else {
                    users = manager.getCustomers();
                }
            }
        }
    
        request.setAttribute("users", users);
        request.getRequestDispatcher("userListPanel.jsp").forward(request, response);
    }
    

    private void handleViewAction(HttpServletRequest request, HttpServletResponse response, String email) throws ServletException, IOException, SQLException {
        ArrayList<User> users = manager.getAllUsers();
        User targetUser = users.stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);

        if (targetUser != null) {
            request.setAttribute("targetUser", targetUser);
            request.getRequestDispatcher("accountDetailsPanel.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "User not found.");
            request.getRequestDispatcher("userListPanel.jsp").forward(request, response);
        }
    }



    private void handleUpdateAction(HttpServletRequest request, HttpServletResponse response, String email) throws ServletException, IOException, SQLException {
        User targetUser = manager.getUserByEmail(email);
    
        // Enumeration<String> parameterNames = request.getParameterNames();
        // while (parameterNames.hasMoreElements()) {
        //     String paramName = parameterNames.nextElement();
        //     System.out.println("Parameter Name - " + paramName + ", Value - " + request.getParameter(paramName));
        // }


        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String password = request.getParameter("password");
        
        if (firstName == null || firstName.trim().isEmpty()) {
            request.setAttribute("errorMessage", "First Name cannot be empty.");
            request.setAttribute("targetUser", targetUser);
            request.getRequestDispatcher("updateUserPanel.jsp").forward(request, response);
            return;
        }

        // generic details
        targetUser.setFirstName(firstName);
        targetUser.setLastName(lastName);
        // Update only if password is provided
        if (password != null && !password.trim().isEmpty()) {
            targetUser.setPassword(password);
        }


        if (targetUser instanceof Customer) {        // Check if user is customer
            Customer customer = (Customer) targetUser;
    
            String streetAddress = request.getParameter("street_address");
            String postcode = request.getParameter("postcode");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String homePhoneNumber = request.getParameter("phone_number");
            String mobilePhoneNumber = request.getParameter("mobile_number");
    
            // Update customer address
            Address address = customer.getAddress();
            if (address == null) {
                address = new Address();
            }
            address.setStreetAddress(streetAddress);
            address.setPostcode(Integer.parseInt(postcode));
            address.setCity(city);
            address.setState(state);
            customer.setAddress(address);
    
            // Update customer phone numbers
            customer.setHomePhoneNumber(homePhoneNumber != null ? Integer.parseInt(homePhoneNumber) : -1);
            customer.setMobilePhoneNumber(mobilePhoneNumber != null ? Integer.parseInt(mobilePhoneNumber) : -1);
        } else if (targetUser instanceof Staff) {        // Check if user is staff and update staff_type
            Staff staff = (Staff) targetUser;
            int staffTypeID = Integer.parseInt(request.getParameter("staff_type"));
            staff.setStaffTypeID(staffTypeID);
        }

        manager.updateUser(targetUser);
        response.sendRedirect("AdminStaffUserMgmtController?action=viewProfile&email=" + email);
    }


    private void handleDeleteAction(HttpServletRequest request, HttpServletResponse response, String email) throws ServletException, IOException, SQLException {
        try {
            String userType = request.getParameter("userType");

            if ("STAFF".equalsIgnoreCase(userType)) {
                manager.deleteUser(email, "STAFF");
            } else if ("CUSTOMER".equalsIgnoreCase(userType)) {
                manager.deleteUser(email, "CUSTOMER");
            } else {
                request.setAttribute("errorMessage", "Invalid user type.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }
            
            response.sendRedirect("AdminStaffUserMgmtController");
        } catch (SQLException ex) {
            Logger.getLogger(AdminStaffUserMgmtController.class.getName()).log(Level.SEVERE, "Error during deletion", ex);
            request.setAttribute("errorMessage", "Failed to delete user.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                log("Error closing connection", ex);
            }
        }
    }

    private void forwardWithError(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        String[] errorAttributes = {
            "duplicateEmail", "duplicateStaffID", "emailError", "passwordError", "firstNameError",
            "lastNameError", "homePhoneError", "mobilePhoneError", "streetAddressError", "postcodeError", "cityError"
        };
    
        for (String attribute : errorAttributes) {
            String errorMessage = (String) session.getAttribute(attribute);
            if (errorMessage != null && !errorMessage.isEmpty()) {
                request.setAttribute("errorMessage", errorMessage);
                session.removeAttribute(attribute);
                break;
            }
        }
        request.getRequestDispatcher("registerPanel.jsp").forward(request, response);
    }
}