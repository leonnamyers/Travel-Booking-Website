<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Random"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="com.iotbay.model.User"%>
<%@page import="com.iotbay.Dao.DBManager"%>
<%@page import="com.iotbay.Controller.UserValidation"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css"> 
    <script type="text/javascript" src="js/index.js"></script>
    <title>Search Access Logs</title>
    <nav>
        <h1>Search Access Logs</h1>

        <!-- Menu Items => If User is logged in -->
        <%
        if (session != null && session.getAttribute("user") != null) { 
        %>
        <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="account_details.jsp">Account</a></li>
            <li><a href="logout.jsp">Logout</a></li>
        </ul>

        <!-- Menu Items => If User is NOT logged in -->
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

                    String errorMessage = (String) session.getAttribute("dateFormatError");
                    UserValidation.clear(session);

                    if (errorMessage != null && !errorMessage.isEmpty()) {
                        %>
                        <br>
                        <p><%= errorMessage %></p>
                        <br>
                        <%
                    }
                %>
                <form action="/SearchAccessLogs" method="post">
                    <label for="inputDate">Enter Date:</label>
                    <input type="date" id="inputDate" name="inputDate">
                    <div id="register-buttons" style="padding: 5%; display: flex; justify-content: center; align-items: center;">
                        <button type="submit">Search</button>
                    </div>
                </form>
                
                <%
                } else {
                %>
                <p>User is not logged in.</p>
                <%
                }
                %>
            </div>
        </div>            
    </div>        
</body>
</html>
