<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.iotbay.Model.*"%>
<%@page import="com.iotbay.Dao.*"%>
<%@page import="com.iotbay.Controller.*"%>
<%@page import="java.sql.Timestamp" %>
<html>
    <head>
        <meta http-equiv="Content-Type="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/navbar.css"> 
        <title>Flight Page</title>
    </head>
    <%
        ArrayList<Flight> flightList = (ArrayList<Flight>)session.getAttribute("flightList");
        
        User user = (User)session.getAttribute("user");
        int flightUpdatingIndex = Integer.parseInt((String)request.getParameter("flightIndex"));
        String itemID = (String)request.getParameter("itemID");
        String name = (String)request.getParameter("name");
        double price = Double.parseDouble((String)request.getParameter("price"));
        int availability = Integer.parseInt((String)request.getParameter("availability"));
        String img = (String)request.getParameter("img");
        
        String startTime = (String)request.getParameter("startTime");
        String correctTime = startTime.replace("T"," ");
        correctTime+=":00";
        Timestamp startTimeStamp = Timestamp.valueOf(correctTime);

        String departureCity = (String)request.getParameter("departureCity");
        String destinationCity = (String)request.getParameter("destinationCity");
        int hours = Integer.parseInt((String)request.getParameter("hours"));
        String stops = (String)request.getParameter("stops");
        String seatType = (String)request.getParameter("seatType");

        Flight updatedFlight = new Flight(itemID,name,price,availability,img,startTimeStamp, 
        departureCity, destinationCity, hours, stops, seatType);

        flightList.set(flightUpdatingIndex,updatedFlight);

    %>
    <body>
        <nav>
            <h1>Flight</h1>
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
        </br>
        </br>
        <h1 align = "center"> You have successfully update the flight!</h1>   
        </br>
        </br>
        <form align = "center" action="http://localhost:8080/flights.jsp">
            <button>Back to Flight Catalogue</button>
        </form>
        
    </body>
</html>