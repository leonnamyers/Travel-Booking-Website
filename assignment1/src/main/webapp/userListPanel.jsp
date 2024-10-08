<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.iotbay.Model.User" %>
<%@ page import="com.iotbay.Model.Customer" %>
<%@ page import="com.iotbay.Model.Staff" %>
<%@ page import="com.iotbay.Model.UserType" %>

<!DOCTYPE html>

<%
    // Check if user is staff
    User user = (User) session.getAttribute("user");
    if (user == null || user.getUserType() != UserType.STAFF) {
        response.sendRedirect("index.jsp");
        return;
    }

    // Retrieve users list
    ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
    String errorMessage = (String) request.getAttribute("errorMessage");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <script type="text/javascript" src="js/index.js"></script>
        <title>User List</title>
        <jsp:include page="navbar.jsp" flush="true" />
    </head>
    <body onload="startTime()">
        <div class="outer-container">
            <div class="flex-container" style="flex-direction: column;">
                
                <%
                if (request.getParameter("unregister") != null && request.getParameter("unregister").equals("true")) {
                %>
                <p>You have successfully unregistered your account</p>
                <br>
                <%
                }
                %>

                <h1 class="heading">User List</h1>
                
                <% if (errorMessage != null) { %>
                    <p style="color: red;"><%= errorMessage %></p>
                <% } %>

                <!-- Add user button and Search form -->
                <div style="display: flex; flex-direction: row; justify-content: center; padding: 25px">
                    <!-- Add user button -->
                    <div style="margin-right: 100px;">
                        <form action="registerPanel.jsp" method="GET">
                            <input type="submit" value="Add User" style="padding: 0 10px;">
                        </form>
                    </div>

                    <!-- Search form -->
                    <form action="AdminStaffUserMgmtController?action=search" method="POST">
                        <input type="text" id="fullName" name="fullName" class="search" placeholder="Search first or last name" value="<%= (request.getParameter("fullName") == null) ? "" : request.getParameter("fullName") %>" />
                        <input type="submit" value="Search">
                    </form>
                </div>

                <!-- User list table -->
                <table>
                    <tr>
                        <th>Email</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Action</th>
                    </tr>
                    <% if (users == null || users.isEmpty()) { %>
                        <tr><td colspan="4">No users found.</td></tr>
                    <% } else { 
                        for (User u : users) { %>
                            <tr>
                                <td><%= u.getEmail() %></td>
                                <td><%= u.getFirstName() %></td>
                                <td><%= u.getLastName() %></td>
                                <td>
                                    <form action="AdminStaffUserMgmtController" method="POST">
                                        <input type="hidden" name="email" value="<%= u.getEmail() %>">
                                        <input type="hidden" name="userType" value="<%= u instanceof Staff ? "STAFF" : "CUSTOMER" %>">
                                        <button type="submit" name="action" value="viewProfile">View</button>
                                        <button type="submit" name="action" value="update">Edit</button>
                                        <button type="submit" name="action" value="delete" onclick="return confirm('Are you sure you want to remove <%= u.getFirstName() + ' ' + u.getLastName() %>\'s account?');">Delete</button>
                                    </form>
                                </td>
                            </tr>
                    <% } } %>
                </table>

                <%
                    if (users == null) {
                        out.println("No users found (users == null).<br>");
                    } else {
                        out.println("Number of users found: " + users.size() + "<br>");
                    }
                %>
            </div>
        </div>
        <jsp:include page="/ConnServlet" flush="true" />
    </body>
</html>
