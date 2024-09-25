<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Random"%>
<%@ page import="com.iotbay.*" %>
<%@ page import="com.iotbay.Model.*" %>
<%@page import="java.sql.Timestamp" %>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/navbar.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <title>Home Page</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp" flush="true" />
        

        <div class="outer-container">
            <div class="flex-container" style="flex-direction: column;">
                <%
                if (request.getParameter("unregister") != null && request.getParameter("unregister").equals("true")) {
                %>
                <p>You have successfully unregistered your account</p>
                <br>
                <%
                }
                %>
        

                <!-- Content will be loaded based on navigation -->
                <div class="content">
                    <!-- No default content, only show based on navigation -->
                </div>
            </div>           
        </div>
        <jsp:include page="/ConnServlet" flush="true" />
    </body>
</html>
