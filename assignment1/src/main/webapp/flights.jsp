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

            ArrayList<Flight> flightList = new ArrayList<Flight>();
            Timestamp t1 = new Timestamp(2024, 10, 23, 7, 00, 00, 00);
            Timestamp t2 = new Timestamp(2024, 11, 15, 8, 30, 00, 00);
            Timestamp t3 = new Timestamp(2024, 0, 15, 9, 00, 00, 00);
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

    <img src="/images/flightPhoto.jpeg" width="100%" >
    <div>    
        <form>
            <label>Departure:</label>
            <input list="departures" name="departure" id="departure" type="text">
                <datalist id="departures">
                    <option value="Sydney">
                    <option value="Melbourne">
                    <option value="Brisbane">
                    <option value="Canberra">
                    <option value="Perth">
                    <option value="Adelaide">
                    <option value="Gold Coast">
                    <option value="Darwin">
                </datalist>
            </input>
            <label>Destination:</label>
            <input list="destinations" name="destination" id="destination" type="text">
                <datalist id="destinations">
                    <option value="Sydney">
                    <option value="Melbourne">
                    <option value="Brisbane">
                    <option value="Canberra">
                    <option value="Perth">
                    <option value="Adelaide">
                    <option value="Gold Coast">
                    <option value="Darwin">
                </datalist>
            </input>
            <input name="departureTime" id="departureTime" type="date">
            
            <label>Seat</label>
            <select name="seats" id="seats">
                <option value="Economy">Economy</option>
                <option value="Premium Economy">Premium Economy</option>
                <option value="Business">Business</option>
            </select>

            <input type="submit">

        </form>
        <br/>
        <br/>
    </div>

    <div>
        <table>
            <caption><h2>Flight Booking</h2></caption>
            <tr class="flight-list">
                <th></th>
                <th>Company</th>
                <th>Price</th>
                <th>Flight Time</th>
                <th>Departure</th>
                <th>Destination</th>
                <th>Time</th>
                <th>Seat</th>
                <th></th>

            </tr>
        <c:forEach var="flight" items="${flightList}">
            <tr class="flight-list">
                <td><img width="200px" height="200px" src="images/${flight.img}"></td>
                <td><c:out value="${flight.name}" /></td>
                <fmt:formatNumber var="formattedUnitPrice" type="number" minFractionDigits="2" maxFractionDigits="2" value="${flight.price}" />
                <td>$<c:out value="${formattedUnitPrice}" /></td>
                <td><c:out value="${flight.startTime}" /></td>
                <td><c:out value="${flight.departureCity}" /></td>
                <td><c:out value="${flight.destinationCity}" /></td>
                <td><c:out value="${flight.hours}" /> hrs</td>
                <td><c:out value="${flight.seatType}" /></td>
                <td>
                    <!-- <form method="GET" action=""> -->
                        <input id="addCartBtn" type="submit" value="Add to cart"/>
                    <!-- </form> -->
                </td>
            </tr>
        </c:forEach>
        </table>
    </div> 
    </body>
</html>

