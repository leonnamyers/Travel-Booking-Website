
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
        <nav>
            <h1>Account Details</h1>
            <%
            if (session != null && session.getAttribute("user") != null) { 
            %>
            <ul>
                <li><a href="cart.jsp">Cart</a></li>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="account_details.jsp">Account</a></li>
                <li><a href="logout.jsp">Logout</a></li>
            </ul>

            <!--Menu Items => If User is NOT logged in-->

            <%
            } else {
            %>
            <ul>
                <li><a href="cart.jsp">Cart</a></li>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="login.jsp">Login</a></li>
                <li><a href="register.jsp">Register</a></li>
            </ul>
            <% 
            }
            %>
        </nav>
        <script>
            function confirmUnregister() {
                if (confirm('Are you sure you want to unregister this user?')) {
                    window.location.href = "userListPanel.jsp";
                }
            }

            function redirectToUpdateUserPanel() {
                window.location.href = "updateUserPanel.jsp";
            }

            function redirectToViewAccessLogs() {
                window.location.href = "view_access_logs.jsp";
            }

            function redirectToSearchAccessLogs() {
                window.location.href = "search_access_logs.jsp";
            }
        </script>
    </head>
    <body>
        <div class="outer-container">
            <div class="flex-container">
            <%
            User user = (User) session.getAttribute("user");
            if(user == null){
                response.sendRedirect("login.jsp");
            } else {
                if (user instanceof Staff) {
            %>
                <div>
                    <%
                    String email = request.getParameter("email");
                    List<Customer> users = (List<Customer>) session.getAttribute("userList");
                    Customer selectedUser = null;
                    if (users != null && email != null) {
                        for(Customer customer : users){
                            if (customer.getEmail().equals(email)) {
                                selectedUser = customer;
                                break;
                            }
                        }
                    }

                    if (selectedUser != null){
                        Address address = selectedUser.getAddress();
                    %>
                    <table>
                        <tr>
                            <th>Email:</th>
                            <td><%= selectedUser.getEmail() %></td>
                        </tr>
                        <tr>
                            <th>Name:</th>
                            <td><%= selectedUser.getFirstName() + " " + selectedUser.getLastName() %></td>
                        </tr>
                        <tr>
                            <th> Account Type:</th>
                            <td><%= selectedUser instanceof Customer ? "Customer" : "Staff" %></td>
                        </tr>
                        <tr>
                            <th> Address:</th>
                            <td><%= address.getStreetAddress() + " " + address.getCity() + " " + address.getPostcode() + " " + address.getState() %></td>
                        </tr>
                        <tr>
                            <th> Home Phone:</th>
                            <td><%= selectedUser.getHomePhoneNumber() == -1 ? "None" : selectedUser.getHomePhoneNumber() %></td>
                        </tr>
                        <tr>
                            <th> Mobile Phone:</th>
                            <td><%= selectedUser.getMobilePhoneNumber() == -1 ? "None" : selectedUser.getMobilePhoneNumber() %></td>
                        </tr>
                    </table>
                    <br>
                    <br>
                    <div style="display: flex; flex-direction: row; justify-content: center;">
                        <div style="padding-top: 10%; margin-right: 10px;">
                            <form action="updateUserPanel.jsp" method="GET">
                                <input type="hidden" name="email" value="<%= selectedUser.getEmail() %>">
                                <input type="hidden" name="firstName" value="<%= selectedUser.getFirstName() %>">
                                <input type="hidden" name="lastName" value="<%= selectedUser.getLastName() %>">
                                <input type="hidden" name="password" value="<%= selectedUser.getPassword() %>">
                                <input type="hidden" name="address" value="<%= selectedUser.getAddress().getStreetAddress() %>">
                                <input type="hidden" name="city" value="<%= selectedUser.getAddress().getCity() %>">
                                <input type="hidden" name="postcode" value="<%= selectedUser.getAddress().getPostcode() %>">
                                <input type="hidden" name="state" value="<%= selectedUser.getAddress().getState() %>">
                                <input type="hidden" name="homePhoneNumber" value="<%= selectedUser.getHomePhoneNumber() %>">
                                <input type="hidden" name="mobilePhoneNumber" value="<%= selectedUser.getMobilePhoneNumber() %>">
                                <button style="padding: 2%; width: 150px; text-align: center;" type="submit" name="action" value="edit">Update Details</button>
                            </form>
                        
                        </div>
                        <div style="padding-top: 10%;">
                            <button style="padding: 2%; width: 150px; text-align: center;" onclick="confirmUnregister()">Unregister</button>
                        </div>
                        <%-- <div style="padding-top: 10%; margin-left: 10px;">
                            <button style="padding: 2%; width: 150px; text-align: center;" onclick="redirectToViewAccessLogs()">Access Logs</button>
                        </div>
                        <div style="padding-top: 10%; margin-left: 10px;">
                            <button style="padding: 2%; width: 150px; text-align: center;" onclick="redirectToSearchAccessLogs()">Search Access Logs</button>
                        </div> --%>
                    </div>
                </div>
                <%
                    } else {
                        %>
                        <p> Error: No such user. </p>
                        <%
                    }
                } else {
                %>
                <p> Error: You do not have permission to view other user's accounts. </p>
                <%
                }
            }
            %>
            </div>            
        </div>  
        <jsp:include page="/ConnServlet" flush="true" />      
    </body>
</html>
