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
        boolean isUpdateSuccessful = false;
        %>

        <%
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String streetAddress = request.getParameter("address");
        String city = request.getParameter("city");
        String postcode = request.getParameter("postcode");
        String state = request.getParameter("state");
        String phoneNumber = request.getParameter("phone_number");
        String mobileNumber = request.getParameter("mobile_number");

        int parsedPostcode = 0;
        if (postcode != null && !postcode.isEmpty()) {
            try {
                parsedPostcode = Integer.parseInt(postcode);
            } catch (NumberFormatException e) {
                errorMessage = "Invalid postcode. Please enter a valid number.";
            }
        }

        int parsedPhoneNumber = 0;
        int parsedMobileNumber = 0;
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            try {
                parsedPhoneNumber = Integer.parseInt(phoneNumber);
            } catch (NumberFormatException e) {
                errorMessage = "Invalid phone number. Please enter a valid number.";
            }
        }
        if (mobileNumber != null && !mobileNumber.isEmpty()) {
            try {
                parsedMobileNumber = Integer.parseInt(mobileNumber);
            } catch (NumberFormatException e) {
                errorMessage = "Invalid mobile number. Please enter a valid number.";
            }
        }

        Address address = new Address(streetAddress, parsedPostcode, city, state);
        Customer customer = new Customer(email, password, firstName, lastName, address);

        session.setAttribute("selectedCustomer", customer);

        %>


        <div class="outer-container">
            <div class="flex-container">
            <%
            User user = (User) session.getAttribute("user");
            if(user == null){
                response.sendRedirect("login.jsp");
            } else {
                if (user instanceof Staff) {
            
                    if(request.getMethod().equalsIgnoreCase("POST")){
                        if(email != null && firstName != null && lastName != null){
                            if(customer != null){
                                customer.setEmail(email);
                                customer.setFirstName(firstName);
                                customer.setLastName(lastName);
                                if (password != null && !password.isEmpty()) {
                                    customer.setPassword(password);
                                }
                                customer.getAddress().setStreetAddress(streetAddress);
                                customer.getAddress().setCity(city);
                                customer.getAddress().setPostcode(Integer.parseInt(postcode));
                                customer.getAddress().setState(state);

                                customer.setHomePhoneNumber(Integer.parseInt(phoneNumber));
                                customer.setMobilePhoneNumber(Integer.parseInt(mobileNumber));

                                // if update successful
                                session.setAttribute("selectedCustomer", customer);
                                isUpdateSuccessful = true;
                            } else {
                                errorMessage = "Customer not found in session.";
                                request.setAttribute("errorMessage", errorMessage);
                            }
                        } else {
                            errorMessage = "Required fields are missing!";
                            request.setAttribute("errorMessage", errorMessage);
                        }
                    }
                    %>

                    <%
                    if (isUpdateSuccessful) {
                    %>
                        <div class="outer-container">
                            <div class="flex-container" style="flex-direction: column;">
                                <br>
                                <p style="color:green;">User details updated successfully!</p>
                                <br>
                            </div>
                        </div>
                    <%
                    } else if (errorMessage != null && !errorMessage.isEmpty()) {
                    %>
                        <div class="outer-container">
                            <div class="flex-container" style="flex-direction: column;">
                                <br>
                                <p style="color:red;"><%= errorMessage %></p>
                                <br>
                            </div>
                        </div>
                    <%
                    }
                    %>

                    <h3 style="padding: 5%;">Enter <%= firstName %>'s details:</h3>
                    <form action="/UpdateUserPanelController" method="post" class="login-form" id="updateUserForm"  onsubmit="handleUpdateDetails(event)">
                        <input type="hidden" id="userTypeCustomer" name="userType" value="customer">
                        <div id="form-item">
                            <label for="email">Email:</label>
                            <input type="email" id="email" name="email" maxlength="60" required value="<%= email %>" disabled>
                        </div>
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
                        <div id="form-item">
                            <label for="street_address">Street Address:</label>
                            <input type="text" id="street_address" name="street_address" maxlength="30" required value="<%= streetAddress %>">
                        </div>
                        <div id="form-item">
                            <label for="postcode">Postcode (4 to 6 digits):</label>
                            <input type="number" id="postcode" name="postcode" min="0" max="999999" required value="<%= postcode %>">
                        </div>
                        <div id="form-item">
                            <label for="city">City:</label>
                            <input type="text" id="city" name="city" maxlength="40" required value="<%= city %>">
                        </div>
                        <div id="form-item">
                            <label for="state">State:</label>
                            <select id="state" name="state" style="font-size: medium;" required>
                                <option value="">Select a state</option>
                                <option value="NSW" <%= "NSW".equals(state) ? "selected" : "" %>>NSW</option>
                                <option value="VIC" <%= "VIC".equals(state) ? "selected" : "" %>>VIC</option>
                                <option value="TAS" <%= "TAS".equals(state) ? "selected" : "" %>>TAS</option>
                                <option value="NT" <%= "NT".equals(state) ? "selected" : "" %>>NT</option>
                                <option value="WA" <%= "WA".equals(state) ? "selected" : "" %>>WA</option>
                                <option value="QLD" <%= "QLD".equals(state) ? "selected" : "" %>>QLD</option>
                                <option value="ACT" <%= "ACT".equals(state) ? "selected" : "" %>>ACT</option>
                                <option value="SA" <%= "SA".equals(state) ? "selected" : "" %>>SA</option>
                                <option value="OT" <%= "OT".equals(state) ? "selected" : "" %>>OT</option>
                            </select>
                        </div>
                        
                        <div id="form-item">
                            <label for="phone_number">Phone Number:</label>
                            <input type="number" id="phone_number" name="phone_number" value="<%= phoneNumber %>">
                        </div>
                        <div id="form-item">
                            <label for="mobile_number">Mobile Number:</label>
                            <input type="number" id="mobile_number" name="mobile_number" value="<%= mobileNumber %>">
                        </div>
                        
                        <div id="register-buttons">
                            <button type="submit">Update Details</button>
                        </div>

                    </form>
                <%
                } else {
                %>
                <p> Error: You do not have permission to update users. </p>
                <%
                }
            }
            %>
            </div>            
        </div>   
        <jsp:include page="/ConnServlet" flush="true" />     

        
        <script type="text/javascript">
            function handleUpdateDetails(event) {
                event.preventDefault();
                
                alert('Update Successful!');
                
                // Redirect after 2 seconds
                setTimeout(function() {
                    document.getElementById('updateUserForm').submit();
                });
            }
        </script>
    </body>
</html>
