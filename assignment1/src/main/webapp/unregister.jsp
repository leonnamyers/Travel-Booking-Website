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
</head>
<body>
    <jsp:include page="navbar.jsp" flush="true" />
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
