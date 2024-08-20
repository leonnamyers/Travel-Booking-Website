package com.iotbay.Controller;

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
import com.iotbay.Model.Address;
import com.iotbay.Model.CustomerUser;
import com.iotbay.Model.Staff;
import com.iotbay.Model.User;

public class UpdateController extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        try {
            HttpSession session = request.getSession();
            String registeredUserType = request.getParameter("userType");
            String email = request.getParameter("email");
            String password = request.getParameter("password"); 
            String firstName = request.getParameter("first_name");
            String lastName = request.getParameter("last_name");

            DBManager manager = (DBManager) session.getAttribute("manager");
            UserValidation.clear(session);
            User oldUserData = (User) session.getAttribute("user");
            
            // pre-validation logic to check that all input fields are valid
            // these generic validations apply whether the User is a Customer OR a Staff.
            if (!oldUserData.getEmail().equals(email)) {
                if (manager.isDuplicateEmail(email)) {
                    session.setAttribute("duplicateEmail", "Error: Email is already registered.");
                    forwardWithError(request, response, session);
                    return;
                }
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

            // then determine whether a user is a Customer or Staff, and do more specific validation
            // update the user details in the relevant DB table, then update the user stored in the session
            if (registeredUserType.equalsIgnoreCase("customer")) {

                //  Customer User Update

                Address address = new Address(request.getParameter("street_address"), Integer.parseInt(request.getParameter("postcode")), request.getParameter("city"), request.getParameter("state"));
                String phoneNumber = request.getParameter("phone_number");
                String mobileNumber = request.getParameter("mobile_number");

                CustomerUser customerUser = new CustomerUser(email, password, firstName, lastName, address);

                if (phoneNumber != null && !phoneNumber.isEmpty() && phoneNumber.matches("\\d+")) {
                    ((CustomerUser) customerUser).setHomePhoneNumber(Integer.parseInt(phoneNumber));
                    if (!UserValidation.isPhoneNumberValid(phoneNumber)) {
                        session.setAttribute("homePhoneError", "Error: Home Phone Number should be 8-16 digits. Please try again.");
                        forwardWithError(request, response, session);
                        return;
                    }
                }
                if (mobileNumber != null && !mobileNumber.isEmpty() && mobileNumber.matches("\\d+")) {
                    ((CustomerUser) customerUser).setMobilePhoneNumber(Integer.parseInt(mobileNumber));
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

                manager.updateCustomer(customerUser, (CustomerUser)session.getAttribute("user"));
                session.setAttribute("user", (CustomerUser) customerUser);

            } else if (registeredUserType.equalsIgnoreCase("staff")) {

                // Staff Registration and Update

                String staffID = request.getParameter("staff_id");
                String staffType = request.getParameter("staff_type");

                Staff staff = new Staff(email, password, firstName, lastName);
                if (staffID != null && !staffID.isEmpty() && staffID.matches("\\d+")) {
                    ((Staff) staff).setStaffID(Integer.parseInt(staffID));
                    if (!UserValidation.isStaffIdInvalid(staffID)) {
                        session.setAttribute("staffIDError", "Error: Staff ID expects 8 digits. Please try again.");
                        forwardWithError(request, response, session);
                        return;
                    }
                }

                if (staffID != null && !staffType.isEmpty() && staffType.matches("\\d+")) {
                    ((Staff) staff).setStaffTypeID(Integer.parseInt(staffType));
                } else {
                    ((Staff) staff).setStaffTypeID(1);
                }
                Staff oldData = (Staff) session.getAttribute("user");
                if (oldData.getStaffID() != staff.getStaffID()) {
                    if (manager.isDuplicateStaffID(staffID)) {
                        session.setAttribute("duplicateStaffID", "Error: Staff ID already in system.");
                        forwardWithError(request, response, session);
                        return;
                    }
                }
                manager.updateStaff(staff, oldData);
                session.setAttribute("user", (Staff) staff);
            }

            session.setAttribute("updateSuccess", "User successfully updated account details.");
            response.sendRedirect("account_details.jsp");

        } catch (SQLException ex) {
            Logger.getLogger(UpdateController.class.getName()).log(Level.SEVERE, null, ex);
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
        request.getRequestDispatcher("update_user_details.jsp").forward(request, response);
    }
}
