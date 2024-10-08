<%@ page import="com.iotbay.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="com.iotbay.Model.*" %>

<!--
    Customer and Staff Account Management
        Landing page for Users that successfully login or register (with relevant message)
-->

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

    <script type="text/javascript" src="js/index.js"></script>
    <title>Welcome</title>
</head>
<body>
    <jsp:include page="navbar.jsp" flush="true" />

    <div class="outer-container">
        <div class="flex-container">
            <%
            User user = null;
            if (session.getAttribute("user") != null) {
                user = (User) session.getAttribute("user");
            }
            /*
            Login Logic
            */
            String login = request.getParameter("login");
            %>
            <div style="padding: 20px;">
                <h2>Welcome, <%= user != null ? user.getFirstName(): "null"%></h2>
            </div>
            <br>
            <p>
                <% if (login != null && login.equals("success")) { %>
                    Login Successful!
                <% } else { %>
                    Thank you for registering!
                <% } %>
            </p>
        </div>
    </div>
    <jsp:include page="/ConnServlet" flush="true" />
</body>
</html>
