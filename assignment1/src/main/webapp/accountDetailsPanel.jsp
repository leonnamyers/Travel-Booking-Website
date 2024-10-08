
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Random"%>
<%@ page import="java.util.List"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="com.iotbay.*" %>
<%@ page import="com.iotbay.Model.User" %>
<%@ page import="com.iotbay.Model.Staff" %>
<%@ page import="com.iotbay.Model.Customer" %>
<%@ page import="com.iotbay.Model.Address" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <script type="text/javascript" src="js/index.js"></script>
        <title>Account Details</title>
        <jsp:include page="navbar.jsp" flush="true" />
        
    </head>
    <body>
        <div class="outer-container">
            <div class="flex-container">
                <div>
                    <%
                        User loggedInUser = (User) session.getAttribute("user");
                        if (loggedInUser == null) {
                            response.sendRedirect("login.jsp");
                            return;
                        }
                        
                        // Retrieve target user to display
                        User targetUser = (User) request.getAttribute("targetUser");
                        // if no user, display error, else, display user
                        if (targetUser == null) {
                            out.println("<p>Error: No user selected or user not found.</p>");
                        } else {
                            Address address = targetUser instanceof Customer ? ((Customer) targetUser).getAddress() : null;
                    %>
                    
                    <h1 class="heading"><%= targetUser.getFirstName() + " " + targetUser.getLastName() %>'s account</h1>
                    <br>

                    <table>
                        <tr>
                            <th>Email:</th>
                            <td><%= targetUser.getEmail() %></td>
                        </tr>
                        <tr>
                            <th>First Name:</th>
                            <td><%= targetUser.getFirstName() %></td>
                        </tr>
                        <tr>
                            <th>Last Name:</th>
                            <td><%= targetUser.getLastName() %></td>
                        </tr>
                        <% if (targetUser instanceof Customer) { %>
                            <tr>
                                <th>Address:</th>
                                <td><%= address.getStreetAddress() + ", " + address.getCity() + ", " + address.getPostcode() + ", " + address.getState() %></td>
                            </tr>
                            <tr>
                                <th>Home Phone:</th>
                                <td><%= ((Customer) targetUser).getHomePhoneNumber() != -1 ? ((Customer) targetUser).getHomePhoneNumber() : "None" %></td>
                            </tr>
                            <tr>
                                <th>Mobile Phone:</th>
                                <td><%= ((Customer) targetUser).getMobilePhoneNumber() != -1 ? ((Customer) targetUser).getMobilePhoneNumber() : "None" %></td>
                            </tr>
                        <% } %>
                        
                        <% if (targetUser instanceof Staff) { %>
                            <tr>
                                <th>Staff ID:</th>
                                <td><%= ((Staff) targetUser).getStaffID() %></td>
                            </tr>
                            <tr>
                                <th>Account Type:</th>
                                <td><%= targetUser instanceof Staff ? "Staff" : "Customer" %></td>
                            </tr>
                            <tr>
                                <th>Staff Type:</th>
                                <td><%= ((Staff) targetUser).getStaffTypeID() == 1 ? "Clerk" : "System Admin" %></td>
                            </tr>
                        <% } else { %>
                            <tr>
                                <th>Account Type:</th>
                                <td><%= targetUser instanceof Staff ? "Staff" : "Customer" %></td>
                            </tr>
                        <% } %>
                    </table>
                    
                    <!-- Update and Delete buttons -->
                    <div style="display: flex; flex-direction: row; justify-content: center;">
                        <div style="padding-top: 10%; margin-right: 10px;">
                            <form action="AdminStaffUserMgmtController" method="POST">
                                <input type="hidden" name="email" value="<%= targetUser.getEmail() %>">
                                <input type="hidden" name="userType" value="<%= targetUser instanceof Staff ? "STAFF" : "CUSTOMER" %>">
                                <button style="padding: 2%; width: 150px; text-align: center;" type="submit" name="action" value="update">Update</button>
                            </form>
                        </div>
                        <div style="padding-top: 10%; margin-left: 10px;">
                            <form action="AdminStaffUserMgmtController" method="POST">
                                <input type="hidden" name="email" value="<%= targetUser.getEmail() %>">
                                <input type="hidden" name="userType" value="<%= targetUser instanceof Staff ? "STAFF" : "CUSTOMER" %>">
                                <button style="padding: 2%; width: 150px; text-align: center;" type="submit" name="action" value="delete" onclick="return confirm('Are you sure you want to remove <%= targetUser.getFirstName() + ' ' + targetUser.getLastName() %>\'s account?');">Delete</button>
                            </form>
                        </div>
                    </div>
                    <%
                        }
                    %>
                </div>
            </div>            
        </div>
    </body>
</html>
