<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="com.iotbay.*" %>
<%@ page import="com.iotbay.Dao.DBManager" %>
<%@ page import="com.iotbay.Model.*" %>

<!--
    Customer and Staff Account Management
        Logout Page
        Invalidates current session
        Displays message to tell the User that they are logging out and being redirected to the home page (index) - lasts for a second.
-->

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <script type="text/javascript" src="js/index.js"></script>
    <title>Logout</title>
    <script type="text/javascript">
        window.onload = function() {
            <% if (session == null || session.getAttribute("user") == null) { %>
                document.body.innerHTML +=
                '<div class="outer-container">' +
                    '<div class="flex-container">' +
                        '<p>No one is logged in</p>' +
                    '</div>' +
                '</div>';
            <% } else { %>
                document.body.innerHTML += 
                '<div class="outer-container">' +
                    '<div class="flex-container">' +
                        '<p>Logging out. . .</p>' +
                        '<p>Redirecting to Home</p>' +
                    '</div>' +
                '</div>';
                <%
                session.invalidate(); 
                %>
                setTimeout(function() {
                    window.location.href = "index.jsp";
                }, 1000);
            <% } %>
        }
    </script>
    <nav>
        <h1>Logout</h1>
        <ul>
            <li><a href="index.jsp">Home</a></li>
        </ul>
    </nav>
</head>
<body>
</body>
</html>
