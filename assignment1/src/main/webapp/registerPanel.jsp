<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="com.iotbay.*" %>
<%@ page import="com.iotbay.Model.*" %>

<!DOCTYPE html>
<html>
<head>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <script type="text/javascript" src="js/index.js"></script>
        <title>Register New User</title>
        <jsp:include page="navbar.jsp" flush="true" />

        <script>
            function showForm(role) {
                const customerForm = document.getElementById('customer-form');
                const staffForm = document.getElementById('staff-form');

                if (role === 'customer') {
                    customerForm.style.display = 'flex';
                    staffForm.style.display = 'none';
                } else if (role === 'staff') {
                    staffForm.style.display = 'flex';
                    customerForm.style.display = 'none';
                }
            }
        </script>
        
    </head>
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
        <div class="flex-container" style="flex-direction: column;">
        <%
        User user = (User) session.getAttribute("user");
        if(user == null){
            response.sendRedirect("login.jsp");
        } else {
            if (user instanceof Staff) {
        %>
            <div id="register-buttons">
                <button onclick="showForm('customer')">Customer</button>
                <button onclick="showForm('staff')">Staff</button>
            </div>
    <!--
        Customer form -
        shows after 'Customer' has been clicked / selected. This is also the default form when registering.
    -->
            <div id="customer-form">
                <div>
                    <h3 style="padding: 5%;">Please enter your customer details:</h3>
                        <form action="AdminStaffUserMgmtController" method="post" class="login-form">
                            <input type="hidden" name="action" value="addUser">
                            <input type="hidden" id="userTypeHidden" name="userType" value="customer">
                            <div id="form-item">
                                <label for="email">Email:</label>
                                <input type="email" id="email" name="email" maxlength="60" required>
                            </div>
                            <div id="form-item">
                                <label for="password">Password (at least 8 characters):</label>
                                <input type="password" id="password" name="password" maxlength="30" required>
                            </div>
                            <div id="form-item">
                                <label for="first_name">First Name:</label>
                                <input type="text" id="first_name" name="first_name" maxlength="20" required>
                            </div>
                            <div id="form-item">
                                <label for="last_name">Last Name:</label>
                                <input type="text" id="last_name" name="last_name" maxlength="20" required>
                            </div>
                            <div id="form-item">
                                <label for="street_address">Street Address:</label>
                                <input type="text" id="street_address" name="street_address" maxlength="30" required>
                            </div>
                            <div id="form-item">
                                <label for="postcode">Postcode (4 to 6 digits):</label>
                                <input type="number" id="postcode" name="postcode" min="0" max="999999" required>
                            </div>
                            <div id="form-item">
                                <label for="city">City:</label>
                                <input type="text" id="city" name="city" maxlength="40" required>
                            </div>
                            <div id="form-item">
                                <label for="state">State:</label>
                                </div>
                                <div>
                                <select id="state" name="state" style="font-size: medium;" required>
                                    <option value="">Select a state</option>
                                    <option value="NSW">NSW</option>
                                    <option value="VIC">VIC</option>
                                    <option value="TAS">TAS</option>
                                    <option value="NT">NT</option>
                                    <option value="WA">WA</option>
                                    <option value="QLD">QLD</option>
                                    <option value="ACT">ACT</option>
                                    <option value="SA">SA</option>
                                    <option value="OT">OT</option>
                                </select>
                                </div>
                                <div id="form-item">
                                    <label for="phone_number">Phone Number (8-16 digits):</label>
                                    <input type="number" id="phone_number" name="phone_number"> 
                                </div>
                                <div id="form-item">
                                    <label for="mobile_number">Mobile Number (8-16 digits):</label>
                                    <input type="number" id="mobile_number" name="mobile_number">
                                </div>
                            </div>
                                <div id="register-buttons">
                                    <button type="submit">Register</button>
                                </div>
                            </div>
                        </form>
    <!-- 
        Staff Form -
        shows when 'Staff' tab has been clicked / selected
    -->
            <div id="staff-form" style="display: none;">
                <h3 style="padding: 5%;">Enter your staff details:</h3>
                <div>
                <form action="AdminStaffUserMgmtController" method="post" class="staff-form">
                    <input type="hidden" name="action" value="addUser">
                    <input type="hidden" id="userTypeStaff" name="userType" value="staff">
                    <div id="form-item">
                        <label for="staff_id">Staff ID (8 Digits):</label>
                        <input type="text" id="staff_id" name="staff_id" required>
                    </div>
                    <div id="form-item">
                        <label for="email">Email:</label>
                        <input type="email" id="email" name="email" maxlength="60" required>
                    </div>
                    <div id="form-item">
                        <label for="password">Password (at least 8 characters):</label>
                        <input type="password" id="password" name="password" maxlength="30" required>
                    </div>
                    <div id="form-item">
                        <label for="first_name">First Name:</label>
                        <input type="text" id="first_name" name="first_name" maxlength="20" required>
                    </div>
                    <div id="form-item">
                        <label for="last_name">Last Name:</label>
                        <input type="text" id="last_name" name="last_name" maxlength="20" required>
                    </div>
                    <div id="form-item">
                        <label for="staff_type">Staff Type:</label>
                        </div>
                        <div>
                            <select id="staff_type" name="staff_type" style="font-size: medium;" required>
                                <option value="">Select a type</option>
                                <option value="1">Stock Clerk</option>
                                <option value="2">System Admin</option>
                            </select>
                        </div>
                    </div>
                        <div id="register-buttons" style="padding: 5%;">
                            <button type="submit">Register Staff Account </button>
                        </div>
                </form>
            </div>
        </div>
        <% } else { %>
            <p>Error: You do not have permission to register users.</p>
        <% }
        }
        %>
        <jsp:include page="/ConnServlet" flush="true" />
</body>
</html>
