<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="java.util.Random"%>
<%@ page import="com.iotbay.*" %>
<%@ page import="com.iotbay.Model.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

    <script type="text/javascript" src="js/index.js"></script>
    <title>Login</title>
</head>
<body>
    <jsp:include page="navbar.jsp" flush="true" />
    <div class="outer-container">
        <div class="flex-container">
            <%
            if (session != null && session.getAttribute("user") != null) { 
            %>
            <p>An account is already logged in.</p>
            <%
                } else {
            %>
    <form id="loginForm" action="/LoginController" method="post" class="login-form">
        <div style="padding: 2%; display: flex; justify-content: center;">
            <h2>Login</h2>
        </div>
        <div>
            <%
            // Error Messages
            String emailError = (String) session.getAttribute("emailError");
            String passwordError = (String) session.getAttribute("passwordError");
            String loginError = (String) session.getAttribute("loginError");

            String errorMessage = null;

            if (emailError != null && !emailError.isEmpty()) {
                errorMessage = emailError;
            } else if (passwordError != null && !passwordError.isEmpty()) {
                errorMessage = passwordError;
            } else if (loginError != null && !loginError.isEmpty()) {
                errorMessage = loginError;
            }
            UserValidation.clear(session);

            if (errorMessage != null) {
                %>
                <br>
                    <div><%= errorMessage %></div>
                <br>
                <%
            }
            %>
        </div>
        <div style="padding: 2%;">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div style="padding: 2%;">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div style="display: flex; justify-content: center; padding-top: 10%;">
            <button id="register-buttons" style="padding: 2%;" type="submit">Login</button>
        </div>
    </form>
            <%
                }
            %>        
        </div>
    </div>
    <jsp:include page="/ConnServlet" flush="true" />
    </body>
</html>
