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
        <title>Home Page</title>
    </head>
    <%  ArrayList<Flight> flightList = new ArrayList<Flight>();
        Timestamp t1 = new Timestamp(124, 10, 23, 7, 00, 00, 00);
        Timestamp t2 = new Timestamp(124, 11, 15, 8, 30, 00, 00);
        Timestamp t3 = new Timestamp(124, 0, 15, 9, 00, 00, 00);
        Flight f1 = new Flight("F101","JetEngine",250,50,"JetEngine.jpg",t1,"Sydney","Melbourne", 2,"Non-Stop","Economy");
        Flight f2 = new Flight("F102","Harmony",630,50,"Harmony.jpg",t2,"Melbourne","Sydney",2,"Non stop","Business");
        Flight f3 = new Flight("F103","Traveling",500,50,"Travel.jpg",t3,"Queensland","Melbourne",2,"Non stop","Premium Economy");    
        flightList.add(f1);  
        flightList.add(f2);  
        flightList.add(f3);  
        session.setAttribute("flightList", flightList);
    %>
    <body>
        <nav>
            <h1>Home</h1>
            <!--If User is logged in-->
            <%
            if (session != null && session.getAttribute("user") != null) { 
            %>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="account_details.jsp">Account</a></li>
                <li><a href="logout.jsp">Logout</a></li>
            </ul>
            <!--If User is NOT logged in-->
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

                <!-- Including the navbar -->
                <jsp:include page="navbar.jsp" flush="true" />

                <!-- Content will be loaded based on navigation -->
                <div class="content">
                    <!-- No default content, only show based on navigation -->
                </div>
            </div>           
        </div>
        <jsp:include page="/ConnServlet" flush="true" />
    </body>
</html>
