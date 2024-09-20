<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Random"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="com.iotbay.*" %>
<%@ page import="com.iotbay.Model.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css"> 
    <script type="text/javascript" src="js/index.js"></script>
    <title>Unregister</title>
    <nav>
        <h1>Unregister</h1>

        <!--Menu Items => If User is logged in-->

        <%
        if (session != null && session.getAttribute("user") != null) { 
        %>
        <ul>
            <li><a href="Cart.jsp">Cart</a></li>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="account_details.jsp">Account</a></li>
            <li><a href="logout.jsp">Logout</a></li>
        </ul>

        <!--Menu Items => If User is NOT logged in-->

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
<body>
    <div class="outer-container">
        <div class="flex-container">
            <div style="display: flex; align-items: center; flex-direction: column;">
                <form action="/UnregisterController" method="post" class="unregister-form">
                <%
                if (session != null && session.getAttribute("user") != null) { 
                %>
                    <p>Are you sure you want to unregister your account?</p>
                    <br>
                    
                    <div id="register-buttons" style="padding: 5%; display: flex; justify-content: center; align-items: center;">
                        <button type="submit">Confirm</button>
                    </div>
                </form>
                <%
                } else {
                %>
                    <p>Cannot unregister: User is not logged in.</p>
                <%
                }
                %>
            </div>
        </div>            
    </div>  
    <jsp:include page="/ConnServlet" flush="true" />      
</body>
</html>
