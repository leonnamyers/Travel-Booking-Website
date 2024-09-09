<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="com.iotbay.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css"> 
        <script type="text/javascript" src="js/index.js"></script>
        <title>Dream Escape- Order Placed!</title>
        <nav>
    
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
    <main class="text-display">
        <h1>Your destination has been booked!</h1>
        <h2>Thank you for using Dream Escape</h2>
    </main>
</body>
</html>
