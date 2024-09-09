package com.iotbay.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.iotbay.Dao.DBManager;
import com.iotbay.Model.Address;
import com.iotbay.Model.Customer;
import com.iotbay.Model.Staff;
import com.iotbay.Model.User;


public class UpdateUserPanelController extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        try {
            HttpSession session = request.getSession();
            String registeredUserType = request.getParameter("userType");
            String email = request.getParameter("email");
            String password = request.getParameter("password"); 
            String firstName = request.getParameter("first_name");
            String lastName = request.getParameter("last_name");
            String streetAddress = request.getParameter("street_address");
            String postcode = request.getParameter("postcode");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String phoneNumber = request.getParameter("phone_number");
            String mobileNumber = request.getParameter("mobile_number");

            //DBManager manager = (DBManager) session.getAttribute("manager");
            UserValidation.clear(session);
            Customer customerToUpdate = (Customer) session.getAttribute("selectedCustomer");

            if (customerToUpdate == null) {
                session.setAttribute("error", "Error: No user selected for editing.");
                response.sendRedirect("userListPanel.jsp");
                return;
            }

            // pre-validation logic to check that all input fields are valid
            // these generic validations apply whether the User is a Customer OR a Staff.
            if (!customerToUpdate.getEmail().equals(email)) {
                /*if (manager.isDuplicateEmail(email)) {
                    session.setAttribute("duplicateEmail", "Error: Email is already registered.");
                    forwardWithError(request, response, session);
                    return;
                }*/
            } else if (!UserValidation.isEmailValid(email)) {
                session.setAttribute("emailError", "Error: Email incorrectly formatted. Please try again.");
                forwardWithError(request, response, session);
                return;
            } else if (!UserValidation.isPasswordValid(password)) {
                session.setAttribute("passwordError", "Error: Password incorrectly formatted. Please try again.");
                forwardWithError(request, response, session);
                return;
            } else if (!UserValidation.isFieldAlphaNum(firstName)) {
                session.setAttribute("firstNameError", "Error: First name incorrectly formatted. Please try again.");
                forwardWithError(request, response, session);
                return;
            } else if (!UserValidation.isFieldAlphaNum(lastName)) {
                session.setAttribute("lastNameError", "Error: Last name incorrectly formatted. Please try again.");
                forwardWithError(request, response, session);
                return;
            } 

            if (customerToUpdate != null) {

                customerToUpdate.setEmail(email);
                customerToUpdate.setFirstName(firstName);
                customerToUpdate.setLastName(lastName);
                if (password != null && !password.isEmpty()) {
                    customerToUpdate.setPassword(password);
                }
                Address address = new Address(request.getParameter("street_address"), Integer.parseInt(request.getParameter("postcode")), request.getParameter("city"), request.getParameter("state"));
                customerToUpdate.setAddress(address);

                if (phoneNumber != null && !phoneNumber.isEmpty() && phoneNumber.matches("\\d+")) {
                    customerToUpdate.setHomePhoneNumber(Integer.parseInt(phoneNumber));
                    if (!UserValidation.isPhoneNumberValid(phoneNumber)) {
                        session.setAttribute("homePhoneError", "Error: Home Phone Number should be 8-16 digits. Please try again.");
                        forwardWithError(request, response, session);
                        return;
                    }
                }
                if (mobileNumber != null && !mobileNumber.isEmpty() && mobileNumber.matches("\\d+")) {
                    customerToUpdate.setMobilePhoneNumber(Integer.parseInt(mobileNumber));
                    if (!UserValidation.isPhoneNumberValid(mobileNumber)) {
                        session.setAttribute("mobilePhoneError", "Error: Mobile Phone Number should be 8-16 digits. Please try again.");
                        forwardWithError(request, response, session);
                        return;
                    }
                }

                if (!UserValidation.isFieldAlphaNum(address.getStreetAddress())) {
                    session.setAttribute("streetAddressError", "Error: Street Address incorrectly formatted. Please try again.");
                    forwardWithError(request, response, session);
                    return;
                } else if (!UserValidation.isPostcodeValid(address.getPostcode())) {
                    session.setAttribute("postcodeError", "Error: Postcode should be 4 digits. Please try again.");
                    forwardWithError(request, response, session);
                    return;
                } else if (!UserValidation.isFieldAlphaNum(address.getCity())) {
                    session.setAttribute("cityError", "Error: City incorrectly formatted. Please try again.");
                    forwardWithError(request, response, session);
                    return;
                }

                //manager.updateCustomer(customerUser, (Customer)session.getAttribute("user"));
                session.setAttribute("selectedCustomer", customerToUpdate);

                @SuppressWarnings("unchecked")
                List<Customer> users = (List<Customer>) session.getAttribute("userList");
                if (users == null) {
                    users = new ArrayList<>(); // Initialize the list if it's null
                    session.setAttribute("userList", users);
                }
        
                for (Customer customer : users) {
                    if (customer.getEmail().equals(customerToUpdate.getEmail())) {
                        users.set(users.indexOf(customer), customerToUpdate);
                    }
                }
                session.setAttribute("userList", users);

                session.setAttribute("updateSuccess", "User successfully updated account details.");
                response.sendRedirect("userListPanel.jsp");
            }

        } catch (Exception ex) { //SQLException
            Logger.getLogger(UpdateUserPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    // if validations fails for any reason, redirect back to the update page with a relevant error message
    private void forwardWithError(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        String[] errorAttributes = {
            "duplicateEmail",
            "duplicateStaffID",
            "emailError",
            "passwordError",
            "firstNameError",
            "lastNameError",
            "homePhoneError",
            "mobilePhoneError",
            "streetAddressError",
            "postcodeError",
            "cityError",
            "staffIDError"
        };

        for (String attribute : errorAttributes) {
            String errorMessage = (String) session.getAttribute(attribute);
            if (errorMessage != null && !errorMessage.isEmpty()) {
                request.setAttribute("errorMessage", errorMessage);
                session.removeAttribute(attribute);
                break;
            }
        }
        request.getRequestDispatcher("updateUserPanel.jsp").forward(request, response);
    }
}
