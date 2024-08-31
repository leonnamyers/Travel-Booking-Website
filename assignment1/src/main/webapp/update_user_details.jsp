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
        <nav>
            <h1>Update User Details</h1>
            <%
            if (session != null && session.getAttribute("user") != null) { 
            %>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="account_details.jsp">Account</a></li>
                <li><a href="logout.jsp">Logout</a></li>
            </ul>

            <!--Menu Items => If User is NOT logged in-->

            <%
            } else {
            %>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="login.jsp">Login</a></li>
                <li><a href="register.jsp">Register</a></li>
            </ul>
            <% 
            }
            %>
        </nav>
    </head>
    <body>
        <% 
        String errorMessage = (String)request.getAttribute("errorMessage");
        if (errorMessage == null) {
            errorMessage = "";
        }
    %>

    <% if (!errorMessage.isEmpty()) { %>
        <div class="outer-container">
            <div class="flex-container" style="flex-direction: column;">
                <br>
                <p><%= errorMessage %></p>
                <br>
            </div>
        </div>
    <% } %>

        <div class="outer-container">
            <div class="flex-container">
                <%
                User user = (User) session.getAttribute("user");

                if (user != null) {
                    if (user instanceof Customer) {
                        Customer customer = (Customer) user;
                        %>
                        <!-- Customer -->
                        <h3 style="padding: 5%;">Enter your new customer details:</h3>
                        <form action="/UpdateController" method="post" class="login-form">
                            <input type="hidden" id="userTypeCustomer" name="userType" value="customer">
                            <div id="form-item">
                                <label for="email">Email:</label>
                                <input type="email" id="email" name="email" maxlength="60" required value="<%= user != null ? customer.getEmail() : "" %>">
                            </div>
                            <div id="form-item">
                                <label for="password">Password (at least 8 characters):</label>
                                <input type="password" id="password" name="password" maxlength="30" required>
                            </div>
                            <div id="form-item">
                                <label for="first_name">First Name:</label>
                                <input type="text" id="first_name" name="first_name" maxlength="20" required value="<%= user != null ? customer.getFirstName() : "" %>">
                            </div>
                            <div id="form-item">
                                <label for="last_name">Last Name:</label>
                                <input type="text" id="last_name" name="last_name" maxlength="20" required value="<%= user != null ? customer.getLastName() : "" %>">
                            </div>
                            <div id="form-item">
                                <label for="street_address">Street Address:</label>
                                <input type="text" id="street_address" name="street_address" maxlength="30" required value="<%= user != null ? customer.getAddress().getStreetAddress() : "" %>">
                            </div>
                            <div id="form-item">
                                <label for="postcode">Postcode (4 to 6 digits):</label>
                                <input type="number" id="postcode" name="postcode" min="0" max="999999" required value="<%= user != null ? customer.getAddress().getPostcode() : "" %>">
                            </div>
                            <div id="form-item">
                                <label for="city">City:</label>
                                <input type="text" id="city" name="city" maxlength="40" required value="<%= user != null ? customer.getAddress().getCity() : "" %>">
                            </div>
                            <div id="form-item">
                                <label for="state">State:</label>
                                <select id="state" name="state" style="font-size: medium;" required>
                                    <option value="">Select a state</option>
                                    <option value="NSW" <%= customer.getAddress().getState().equals("NSW") ? "selected" : "" %>>NSW</option>
                                    <option value="VIC" <%= customer.getAddress().getState().equals("VIC") ? "selected" : "" %>>VIC</option>
                                    <option value="TAS" <%= customer.getAddress().getState().equals("TAS") ? "selected" : "" %>>TAS</option>
                                    <option value="NT" <%= customer.getAddress().getState().equals("NT") ? "selected" : "" %>>NT</option>
                                    <option value="WA" <%= customer.getAddress().getState().equals("WA") ? "selected" : "" %>>WA</option>
                                    <option value="QLD" <%= customer.getAddress().getState().equals("QLD") ? "selected" : "" %>>QLD</option>
                                    <option value="ACT" <%= customer.getAddress().getState().equals("ACT") ? "selected" : "" %>>ACT</option>
                                    <option value="SA" <%= customer.getAddress().getState().equals("SA") ? "selected" : "" %>>SA</option>
                                    <option value="OT" <%= customer.getAddress().getState().equals("OT") ? "selected" : "" %>>OT</option>
                                </select>
                            </div>
                            
                            <div id="form-item">
                                <label for="phone_number">Phone Number:</label>
                                <input type="number" id="phone_number" name="phone_number" value="<%= customer.getHomePhoneNumber() != -1 ? customer.getHomePhoneNumber() : "" %>">
                            </div>
                            <div id="form-item">
                                <label for="mobile_number">Mobile Number:</label>
                                <input type="number" id="mobile_number" name="mobile_number" value="<%= customer.getMobilePhoneNumber() != -1 ? customer.getMobilePhoneNumber() : "" %>">
                            </div>
                            <div id="register-buttons">
                                <button type="submit">Update Details</button>
                            </div>
                        </form>
                        <%
                    }
                        else if (user instanceof Staff) {
                            Staff staff = (Staff) user;
                        %>
                        <!-- Staff -->
                        <h3 style="padding: 5%;">Enter your new staff details:</h3>
                        <form action="/UpdateController" method="post" class="staff-form">
                            <input type="hidden" id="userTypeStaff" name="userType" value="staff">
                            <div id="form-item">
                                <label for="staff_id">Staff ID (8 Digits):</label>
                                <input type="text" id="staff_id" name="staff_id" required value="<%= user != null ? staff.getStaffID() : "" %>">
                            </div>
                            <div id="form-item">
                                <label for="email">Email:</label>
                                <input type="email" id="email" name="email" maxlength="60" required value="<%= user != null ? staff.getEmail() : "" %>">
                            </div>
                            <div id="form-item">
                                <label for="password">Password (at least 8 characters):</label>
                                <input type="password" id="password" name="password" maxlength="30" required>
                            </div>
                            <div id="form-item">
                                <label for="first_name">First Name:</label>
                                <input type="text" id="first_name" name="first_name" maxlength="20" required value="<%= user != null ? staff.getFirstName() : "" %>">
                            </div>
                            <div id="form-item">
                                <label for="last_name">Last Name:</label>
                                <input type="text" id="last_name" name="last_name" maxlength="20" required value="<%= user != null ? staff.getLastName() : "" %>">
                            </div>
                            <div id="form-item">
                                <label for="staff_type">Staff Type:</label>
                                <select id="staff_type" name="staff_type" style="font-size: medium;" required>
                                    <option value="">Select a type</option>
                                    <option value="1" <%= staff.getStaffTypeID() == 1 ? "selected" : "" %>>Stock Clerk</option>
                                    <option value="2" <%= staff.getStaffTypeID() == 2 ? "selected" : "" %>>System Admin</option>
                                </select>
                            </div>
                            <div id="register-buttons" style="padding: 5%;">
                                <button type="submit">Update Details</button>
                            </div>
                        </form>                        
                        <%
                    }
                } else {
                    %>
                    <p>
                        Error: User not logged in.
                    </p>
                    <%
                }
                %>
            </div>            
        </div>   
        <jsp:include page="/ConnServlet" flush="true" />     
    </body>
</html>
