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
        <title>Account Details</title>

        <script>
            function redirectToUnregisterPage() {
                window.location.href = "unregister.jsp";
            }

            function redirectToUpdateUserDetails() {
                window.location.href = "update_user_details.jsp";
            }

        </script>
    </head>
    <body>
        <jsp:include page="navbar.jsp" flush="true" />
        <div class="outer-container">
            <div class="flex-container">
                <div>
                    <%
                    User user = (User) session.getAttribute("user");
                    if (user != null) {
                        String successMessage = (String) session.getAttribute("updateSuccess");
                        UserValidation.clear(session);
                        if (!successMessage.isEmpty()) {
                            %>
                            <p><%= successMessage %></p>
                            <br>
                            <%
                        }
                    %>
                    <table>
                        <tr>
                            <th>Email:</th>
                            <td><%= user.getEmail() %></td>
                        </tr>
                        <tr>
                            <th>Name:</th>
                            <td><%= user.getFirstName() + " " + user.getLastName() %></td>
                        </tr>
                        <tr>
                            <th> Account Type:</th>
                            <td><%= user instanceof Customer ? "Customer" : "Staff" %></td>
                        </tr>
                    <%
                    // Display Account Details

                    if (user instanceof Customer) {
                        Customer customer = (Customer) user;
                        Address address = customer.getAddress();
                    %>
                    <tr>
                        <th> Address:</th>
                        <td><%= address.getStreetAddress() + " " + address.getCity() + " " + address.getPostcode() + " " + address.getState() %></td>
                    </tr>
                    <tr>
                        <th> Home Phone:</th>
                        <td><%= customer.getHomePhoneNumber() == -1 ? "None" : customer.getHomePhoneNumber() %></td>
                    </tr>
                    <tr>
                        <th> Mobile Phone:</th>
                        <td><%= customer.getMobilePhoneNumber() == -1 ? "None" : customer.getMobilePhoneNumber() %></td>
                    </tr>
                    <%
                    } else {
                        Staff staff = (Staff) user;
                    %>
                    <tr>
                        <th> Staff ID:</th>
                        <td><%= staff.getStaffID() %></td>
                    </tr>
                    <tr>
                        <th> Staff Type:</th>
                        <td><%= staff.getStaffTypeID() == 1 ? "Stock Clerk" : "System Admin" %></td>
                    </tr>
                    <%
                    }
                    %>
                    </table>
                    <br>
                    <br>
                    <div style="display: flex; flex-direction: row; justify-content: center;">
                        <div style="padding-top: 10%; margin-right: 10px;">
                            <button style="padding: 2%; width: 150px; text-align: center;" onclick="redirectToUpdateUserDetails()">Update Details</button>
                        </div>
                        <div style="padding-top: 10%;">
                            <button style="padding: 2%; width: 150px; text-align: center;" onclick="redirectToUnregisterPage()">Unregister</button>
                        </div>
                    </div>
                </div>
                <%
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
