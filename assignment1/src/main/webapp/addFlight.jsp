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

    <div>
        <center>
            <h1>Flight Catalogue Management</h1>
            <h2>
                <form action="http://localhost:8080/addFlight.jsp">
                    <button type="submit">Add new device</button>
                </form>
                &nbsp;&nbsp;&nbsp;
                <form action="http://localhost:8080/flights.jsp">
                    <button type="submit">List all devices</button>
                </form>

            </h2>
        </center>
    </div>
    <br/>
    <br/>


    <div align="center">
        

        <form action="http://localhost:8080/addFlightOperation.jsp">
            
        <table border="1" cellpadding="5" align="center">
            <caption>
                <h2>
                    <h1>Add new flight</h1>
                </h2>
            </caption>
            
  
            <!-- Form inputs -->
            <tr>
                <th>Flight ID: </th>
                <td>
                    <input type="text" name="itemID" id="itemID" value=""
                    />
                </td>
            </tr>  

            <tr>
                <th>Company: </th>
                <td>
                    <input type="text" name="name" id="name" value=""
                     />
                </td>
            </tr>
            
            <tr>
                <th>Price</th>
                <td>
                    <input type="text" name="price" id="price" value="0"
                    />
                </td>
            </tr>

            <tr>
                <th>Availability</th>
                <td>
                    <input type="text" name="availability" id="availability" value="0"
                    />
                </td>
            </tr>

            <tr>
                <th>Flight Time: </th>
                <td>
                    <input type="datetime-local" name="startTime" id="startTime" 
                    />
                </td>
            </tr>

            <tr>
                <th>Departure: </th>
                <td>
                    <input type="text" name="departureCity" id="departureCity" value=""
                    />
                </td>
            </tr>

            <tr>
                <th>Destination: </th>
                <td>
                    <input type="text" name="destinationCity" id="destinationCity" value=""
                    />
                </td>
            </tr>
            <tr>
                <th>Hours: </th>
                <td>
                    <input type="text" name="hours" id="hours" value="0"
                    />
                </td>
            </tr>
            <tr>
                <th>Stops: </th>
                <td>
                    <input type="text" name="stops" id="stops" value=""
                    />
                </td>
            </tr>
            <tr>
                <th>Seat: </th>
                <td>
                    <input type="text" name="seatType" id="seatType" value=""
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>