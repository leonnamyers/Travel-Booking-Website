<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Random"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="com.iotbay.*" %>
<%@ page import="java.util.*, java.sql.*"%>
<%@ page import="com.iotbay.Dao.DBManager" %>
<%@ page import="com.iotbay.Model.*" %>



<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css"> 
    <script type="text/javascript" src="js/index.js"></script>
    <title>Access Logs</title>
    <nav>
        <h1>Access Logs</h1>

        <!--Menu Items => If User is logged in-->

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
    <div class="outer-container">
        <div class="flex-container">
            <div>
                <%
                if (session != null && session.getAttribute("user") != null) { 
                    User user = (User) session.getAttribute("user");
                    DBManager manager = (DBManager) session.getAttribute("manager");

                    ArrayList<Timestamp[]> logs = manager.getUserLogs(user.getEmail());
                        if (logs != null && !logs.isEmpty()) {
                %>
                <div>
                    <table>
                        <thead>
                            <tr>
                                <th>Login Date/Time</th>
                                <th>Logout Date/Time</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                            for (Timestamp[] log : logs) {
                            %>
                            <tr>
                                <td><%= log[0] %></td>
                                <td><%= log[1] == null ? " " : log[1] %></td>
                            </tr>
                            <%
                            }
                            %>
                        </tbody>
                    </table>
                </div>
                <%
            }
            else {
                %>
                <p> No data found </p>
                <%
            }
                } else {
                %>
                <p>User is not logged in.</p>
                <%
                }
                %>
            </div>
        </div>            
    </div>     
    <jsp:include page="/ConnServlet" flush="true" />   
</body>
</html>
