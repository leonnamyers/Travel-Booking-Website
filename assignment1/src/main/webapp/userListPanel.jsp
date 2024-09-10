<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Random"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.iotbay.*" %>
<%@ page import="com.iotbay.Model.*" %>
<!DOCTYPE html>



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <script type="text/javascript" src="js/index.js"></script>
        <title>Home Page</title>
        <nav>
            <h1>Home</h1>

            <!--If User is logged in-->

            <%
            if (session != null && session.getAttribute("user") != null) { 
            %>
            <ul>
                <li><a href="Cart.jsp">Cart</a></li>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="account_details.jsp">Account</a></li>
                <li><a href="logout.jsp">Logout</a></li>
            </ul>

            <!--If User is NOT logged in-->

            <%
        } else {
            %>
            <ul>
                <li><a href="Cart.jsp">Cart</a></li>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="login.jsp">Login</a></li>
                <li><a href="register.jsp">Register</a></li>
            </ul>
            <% 
        }
            %>

        </nav>
    </head>
    <body onload="startTime()" >
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
                <%
                User user = (User) session.getAttribute("user");
                if(user == null){
                    response.sendRedirect("login.jsp");
                } else {
                    if (user instanceof Staff) {
                        //Dummy users
                        Object userListObj = session.getAttribute("userList");
                        List<Customer> users = null;
                        if (userListObj instanceof List<?>) {
                            users = (List<Customer>) userListObj;
                        } else {
                            users = new ArrayList<>();
                            session.setAttribute("userList", users);

                            Address address1 = new Address("123 Main St", 12345, "City", "NSW");
                            Address address2 = new Address("456 Oak St", 67890, "Another City", "QLD");
                            Address address3 = new Address("789 Fake St", 11223, "Ben City", "VIC");
                            users.add(new Customer("user1@example.com", "password1", "John", "Doe", address1));
                            users.add(new Customer("user2@example.com", "password2", "Jane", "Smith", address2));
                            users.add(new Customer("user3@example.com", "password3", "Ben", "Lee", address3));
                        }
                        //need to login as staff first
                        //Add User button says I can't create because I already have an account (But I want create another user account)\
                        //View says own acc
                        //Edit Button shows own account details
                        //Search doesn't work

                        //registerUserPanel
                        //account_details
                        //updateUserPanel
                        

                        //Search
                        String searchFullName = request.getParameter("fullName");
                        List<Customer> filteredUsers = new ArrayList<>();
                        if (searchFullName != null && !searchFullName.trim().isEmpty()) {
                            for (Customer customer : users) {
                                if (customer.getFirstName().toLowerCase().contains(searchFullName.toLowerCase()) ||
                                    customer.getLastName().toLowerCase().contains(searchFullName.toLowerCase())) {
                                    filteredUsers.add(customer);
                                }
                            }
                        } else {
                            filteredUsers = users;
                        }

                        //UpdateUserPanel


                        //Deletes user
                        String emailToDelete = request.getParameter("emailToDelete");
                        if (emailToDelete != null) {
                            for(int i = 0; i < users.size(); i++){
                                if(users.get(i).getEmail().equals(emailToDelete)){
                                    users.remove(i);
                                    break;
                                }
                            }
                            session.setAttribute("userList", users);
                        }
                    %>
                    
                        <div style="display: flex; flex-direction: row; justify-content: center; padding: 25px">

                            <!-- Add user button -->
                            <a href="./registerPanel.jsp" ><input type="button" style="padding: 0 10px; margin-right: 100px; text-align: center;" value="Add User"></a>

                            <!-- Search form -->
                            <!-- <form action="UserManagementController" method="POST"> -->
                            <form action="userListPanel.jsp" method="GET">
                                <input type="text" id="fullName" name="fullName" class="search" style="padding: 0 10px; text-align: center;"  placeholder="Search first or last name" value="<%= searchFullName != null ? searchFullName : "" %>" />
                                <!-- <input type="hidden" name="action" value="search"> -->
                                <input type="submit" class="SearchButton" name="searchButton" style="padding: 0 10px; text-align: center;" value="Search">
                            </form>
                        </div>
                    
                        <table>
                            <tr>
                                <th>Email</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Action</th>
                            </tr>
                            <%
                            if (filteredUsers == null || filteredUsers.isEmpty()) {
                            %>
                            <tr>
                                <td colspan="5">No users found.</td>
                            </tr>
                            <%
                            } else {
                                for (Customer customer : filteredUsers) {
                            %>
                                    <tr>
                                        <td><%= customer.getEmail() %></td>
                                        <td><%= customer.getFirstName() %></td>
                                        <td><%= customer.getLastName() %></td>
                                        <td>
                                            <div style="display: flex;">
                                                <form action="accountDetailsPanel.jsp" method="POST">
                                                    <input type="hidden" name="email" value="<%= customer.getEmail() %>">
                                                    <button type="submit" style="padding: 0 10px; margin: 0 3px; text-align: center;" name="action" value="viewProfile" >View</button>
                                                </form>
                                                <form action="updateUserPanel.jsp" method="GET">
                                                    <input type="hidden" name="email" value="<%= customer.getEmail() %>">
                                                    <input type="hidden" name="firstName" value="<%= customer.getFirstName() %>">
                                                    <input type="hidden" name="lastName" value="<%= customer.getLastName() %>">
                                                    <input type="hidden" name="password" value="<%= customer.getPassword() %>">
                                                    <input type="hidden" name="address" value="<%= customer.getAddress().getStreetAddress() %>">
                                                    <input type="hidden" name="city" value="<%= customer.getAddress().getCity() %>">
                                                    <input type="hidden" name="postcode" value="<%= customer.getAddress().getPostcode() %>">
                                                    <input type="hidden" name="state" value="<%= customer.getAddress().getState() %>">
                                                    <input type="hidden" name="homePhoneNumber" value="<%= customer.getHomePhoneNumber() %>">
                                                    <input type="hidden" name="mobilePhoneNumber" value="<%= customer.getMobilePhoneNumber() %>">
                                                    <button type="submit" name="action" style="padding: 0 10px; margin: 0 3px; text-align: center;" value="edit">Edit</button>
                                                </form>
                                                <form action="userListPanel.jsp" method="POST">
                                                    <input type="hidden" name="emailToDelete" value="<%= customer.getEmail() %>">
                                                    <button type="submit" name="action" style="padding: 0 10px; margin: 0 3px; text-align: center;" value="delete" onclick="return confirm('Are you sure you want to delete this user?');">Delete</button>
                                                </form>
                                            </div>
                                        </td>
                                    </tr>
                            <%
                                }
                            }
                            %>
                        </table>
                    <%
                    } else {
                    %>
                    <p> Error: You do not have permission to view this page. </p>
                    <%
                    }
                }
                %>
            </div>
        </div>
        <jsp:include page="/ConnServlet" flush="true" />
    </body>
</html>