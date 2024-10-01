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
import com.iotbay.Model.Address;
import com.iotbay.Model.Customer;
import com.iotbay.Model.Staff;

public class RegisterController extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException { 

        try {
            HttpSession session = request.getSession();
            String registeredUserType = request.getParameter("userType");
            String email = request.getParameter("email");
            String password = request.getParameter("password"); 
            String firstName = request.getParameter("first_name");
            String lastName = request.getParameter("last_name");

            DBManager manager = (DBManager) session.getAttribute("manager");
 
            UserValidation.clear(session);

            // initial pre-validations for all fields that apply to both Customer and Staff users
            // error handling

            if (manager.isDuplicateEmail(email)) {
                session.setAttribute("duplicateEmail", "Error: Email is already registered.");
                forwardWithError(request, response, session);
                return;
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
            
            // then check the user type and perform more specific validations
            // add the user to the relevant DB table and then update the user session
            
            if (registeredUserType.equalsIgnoreCase("customer")) {

                // Customer User Registration
                Address address = new Address(request.getParameter("street_address"), Integer.parseInt(request.getParameter("postcode")), request.getParameter("city"), request.getParameter("state"));
                String phoneNumber = request.getParameter("phone_number");
                String mobileNumber = request.getParameter("mobile_number");

                Customer customer = new Customer(email, password, firstName, lastName, address);

                if (phoneNumber != null && !phoneNumber.isEmpty() && phoneNumber.matches("\\d+")) {
                    ((Customer) customer).setHomePhoneNumber(Integer.parseInt(phoneNumber));
                    if (!UserValidation.isPhoneNumberValid(phoneNumber)) {
                        session.setAttribute("homePhoneError", "Error: Home Phone Number should be 8-16 digits. Please try again.");
                        forwardWithError(request, response, session);
                        return;
                    }
                }
                if (mobileNumber != null && !mobileNumber.isEmpty() && mobileNumber.matches("\\d+")) {
                    ((Customer) customer).setMobilePhoneNumber(Integer.parseInt(mobileNumber));
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

                session.setAttribute("user", (Customer) customer);
                manager.addCustomer(customer, session.getId());
                response.sendRedirect("welcome.jsp");

            } else {
                // Staff User Registration

                String staffID = request.getParameter("staff_id");
                String staffType = request.getParameter("staff_type");

                Staff staff = new Staff(email, password, firstName, lastName);

                if (staffID != null && !staffID.isEmpty() && staffID.matches("\\d+")) {
                    staff.setStaffID(Integer.parseInt(staffID));
                    if (!UserValidation.isStaffIdInvalid(staffID)) {
                        session.setAttribute("staffIDError", "Error: Staff ID expects 8 digits. Please try again.");
                        forwardWithError(request, response, session);
                        return;
                    }

                    if (manager.isDuplicateStaffID(staffID)) {
                        session.setAttribute("duplicateStaffID", "Error: Staff ID already in system.");
                        forwardWithError(request, response, session);
                        return;
                    }
                }

                if (staffID != null && !staffType.isEmpty() && staffType.matches("\\d+")) {
                    staff.setStaffTypeID(Integer.parseInt(staffType));
                } else {
                    staff.setStaffTypeID(1);
                }

                session.setAttribute("user", (Staff) staff);
                manager.addStaff(staff, session.getId());
                response.sendRedirect("welcome.jsp");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
}
