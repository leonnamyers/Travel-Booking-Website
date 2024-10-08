<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Random"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="com.iotbay.*" %>
<%@ page import="com.iotbay.Model.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <script type="text/javascript" src="js/index.js"></script>
        <title>Update User Details</title>
        <jsp:include page="navbar.jsp" flush="true" />
    </head>

    <body>
        <div class="outer-container">
            <div class="flex-container">

            <% 
                User loggedInUser = (User) session.getAttribute("user");
                if (loggedInUser == null) {
                    response.sendRedirect("login.jsp");
                    return;
                }

                User targetUser = (User) request.getAttribute("targetUser");
                if (targetUser == null) {
                    out.println("<p>Error: No user selected or user not found.</p>");
                } else {
                    String email = targetUser.getEmail();
                    String firstName = targetUser.getFirstName();
                    String lastName = targetUser.getLastName();
                    String password = targetUser.getPassword();

                    // Customer-related details
                    Address address = targetUser instanceof Customer ? ((Customer) targetUser).getAddress() : null;
                    String streetAddress = address != null ? address.getStreetAddress() : "";
                    String postcode = address != null ? String.valueOf(address.getPostcode()) : "";
                    String city = address != null ? address.getCity() : "";
                    String state = address != null ? address.getState() : "";
                    String phoneNumber = targetUser instanceof Customer ? String.valueOf(((Customer) targetUser).getHomePhoneNumber()) : "";
                    String mobileNumber = targetUser instanceof Customer ? String.valueOf(((Customer) targetUser).getMobilePhoneNumber()) : "";
            %>
                    <h3 style="padding: 5%;">Enter <%= firstName %>'s new details:</h3>
                    <form action="AdminStaffUserMgmtController" method="POST" class="login-form" id="updateUserForm">
                        <input type="hidden" name="action" value="update">

                        <div id="form-item">
                            <label for="email">Email:</label>
                            <input type="email" id="email" name="email" value="<%= email %>" readonly style="background-color: #e9ecef; color: #6c757d;">
                        </div>

                        <!-- Option to change password -->
                        <div id="form-item">
                            <label for="password">Password (at least 8 characters):</label>
                            <input type="password" id="password" name="password" maxlength="30">
                            <small>Leave blank if you don't want to change it</small>
                        </div>

                        <div id="form-item">
                            <label for="first_name">First Name:</label>
                            <input type="text" id="first_name" name="first_name" maxlength="20" required value="<%= firstName %>">
                        </div>

                        <div id="form-item">
                            <label for="last_name">Last Name:</label>
                            <input type="text" id="last_name" name="last_name" maxlength="20" required value="<%= lastName %>">
                        </div>

                        <!-- If user is Staff, allow change to staff type -->
                        <% if (targetUser instanceof Staff) { 
                            Staff staff = (Staff) targetUser;
                        %>
                            <div id="form-item">
                                <label for="staff_type">Staff Type:</label>
                                <select id="staff_type" name="staff_type" style="font-size: medium;" required>
                                    <option value="1" <%= staff.getStaffTypeID() == 1 ? "selected" : "" %>>Stock Clerk</option>
                                    <option value="2" <%= staff.getStaffTypeID() == 2 ? "selected" : "" %>>System Admin</option>
                                </select>
                            </div>
                        <% } %>

                        <% if (targetUser instanceof Customer) { %>
                        
                            <div id="form-item">
                                <label for="street_address">Street Address:</label>
                                <input type="text" id="street_address" name="street_address" maxlength="50" value="<%= streetAddress != null ? streetAddress : "" %>">
                            </div>

                            <div id="form-item">
                                <label for="postcode">Postcode:</label>
                                <input type="number" id="postcode" name="postcode" min="0" max="999999" value="<%= postcode != null ? postcode : "" %>">
                            </div>

                            <div id="form-item">
                                <label for="city">City:</label>
                                <input type="text" id="city" name="city" maxlength="40" value="<%= city != null ? city : "" %>">
                            </div>

                            <div id="form-item">
                                <label for="state">State:</label>
                                <select id="state" name="state">
                                    <option value="NSW" <%= "NSW".equals(state) ? "selected" : "" %>>NSW</option>
                                    <option value="VIC" <%= "VIC".equals(state) ? "selected" : "" %>>VIC</option>
                                    <option value="QLD" <%= "QLD".equals(state) ? "selected" : "" %>>QLD</option>
                                    <option value="WA" <%= "WA".equals(state) ? "selected" : "" %>>WA</option>
                                    <option value="SA" <%= "SA".equals(state) ? "selected" : "" %>>SA</option>
                                    <option value="TAS" <%= "TAS".equals(state) ? "selected" : "" %>>TAS</option>
                                    <option value="NT" <%= "NT".equals(state) ? "selected" : "" %>>NT</option>
                                    <option value="ACT" <%= "ACT".equals(state) ? "selected" : "" %>>ACT</option>
                                </select>
                            </div>

                            <div id="form-item">
                                <label for="phone_number">Home Phone:</label>
                                <input type="number" id="phone_number" name="phone_number" value="<%= phoneNumber %>">
                            </div>

                            <div id="form-item">
                                <label for="mobile_number">Mobile Phone:</label>
                                <input type="number" id="mobile_number" name="mobile_number" value="<%= mobileNumber %>">
                            </div>
                        <% } %>

                        <div id="register-buttons">
                            <button type="submit" onclick="return confirm('Save <%= targetUser.getFirstName() + ' ' + targetUser.getLastName() %>\'s new details?');">Update Details</button>
                        </div>
                    </form>
                <%
                    }
                %>
            </div>
        </div>

        <jsp:include page="/ConnServlet" flush="true" />
    </body>
</html>
