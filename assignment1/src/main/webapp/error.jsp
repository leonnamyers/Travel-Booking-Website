<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="com.iotbay.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css"> 
    <script type="text/javascript" src="js/index.js"></script>
    <title>Error</title>
    <nav>
        <h1>Error</h1>

        <!--
            Menu Items => 
        -->

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
                String errorMessage = request.getParameter("errorMessage");

                if (errorMessage != null && !errorMessage.isEmpty()) { %>
                    <div class="outer-container">
                        <div class="flex-container" style="flex-direction: column;">
                            <br>
                            <p> <%= errorMessage %></p>
                            <br>
                        </div>
                    </div>
                <% } %>  

            </div>
        </div>            
    </div>        
</body>
</html>
