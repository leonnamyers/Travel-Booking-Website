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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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
                <a href="cart.jsp">
                    <button class ="shopping-cart-button" >
                        <i class="fas fa-shopping-cart"></i>
                        <% Cart cart = (Cart) request.getSession().getAttribute("cart");%>
                        <% if (cart == null) { %>
                        $0.00
                        <% } else { %>
                        $<%=cart.getTotalPrice()%>
                        <% } %>
                    </button>
                </a>
        </ul>
        <!--If User is NOT logged in-->
        <%
        } else {
        %>
        <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="login.jsp">Login</a></li>
            <li><a href="register.jsp">Register</a></li>
                <a href="cart.jsp">
                    <button class ="shopping-cart-button" >
                        <i class="fas fa-shopping-cart"></i>
                        <% Cart cart = (Cart) request.getSession().getAttribute("cart");%>
                        <% if (cart == null) { %>
                        $0.00
                        <% } else { %>
                        $<%=cart.getTotalPrice()%>
                        <% } %>
                    </button>
                </a>
        </ul>
        <% 
        }
        %>
    </nav>

    <img src="/images/flightPhoto.jpeg" width="100%" >

    <div>    
        <form>
            <label>Departure:</label>
            <input list="departures" name="departure" id="departure" type="text" value="">
                <datalist id="departures">
                    <option value="Sydney">
                    <option value="Melbourne">
                    <option value="Brisbane">
                    <option value="Canberra">
                    <option value="Perth">
                    <option value="Adelaide">
                    <option value="Gold Coast">
                    <option value="Darwin">
                    <option value="Christmas Island"></option>
                    <option value="Hobart"></option>
                </datalist>
            </input>

            <label>Destination:</label>
            <input list="destinations" name="destination" id="destination" type="text" value="">
                <datalist id="destinations">
                    <option value="Sydney">
                    <option value="Melbourne">
                    <option value="Brisbane">
                    <option value="Canberra">
                    <option value="Perth">
                    <option value="Adelaide">
                    <option value="Gold Coast">
                    <option value="Darwin">
                    <option value="Christmas Island"></option>
                    <option value="Hobart"></option>
                </datalist>
            </input>
            
            <input name="departureTime" id="departureTime" type="date">

            <label>Seat</label>
 
            <select id="seats" name="seats" type="text">
                <option value="Economy" selected="selected">Economy</option>
                <option value="Premium Economy">Premium Economy</option>
                <option value="Business">Business</option>
                <option value="">All</option>
            </select>

            <input type="submit">

        </form>
        <br/>
        <br/>
    </div>

    <%
        if ((user == null) || (user != null && user.getUserType() == UserType.CUSTOMER)) { 
    %>

    <div>
        <table>
            <caption><h2>Flight Booking</h2></caption>
            <tr class="flight-list">
                <th></th>
                <th>Company</th>
                <th>Price</th>
                <th>Departure Time</th>
                <th>Arrival Time</th>
                <th>Departure</th>
                <th>Destination</th>
                <th>Duration</th>
                <th>Stop</th>
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
                <td><c:out value="${flight.endTime}" /></td>
                <td><c:out value="${flight.departureCity}" /></td>
                <td><c:out value="${flight.destinationCity}" /></td>
                <td><c:out value="${flight.hours}" /> hrs</td>
                <td><c:out value="${flight.stops}" /></td>
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

    <%
    } else if(user != null && user.getUserType() == UserType.STAFF) {
    %>
    <% 
        Staff staff = (Staff)session.getAttribute("user");
    %>
    <%
    if (staff.getStaffTypeID()==1) { 
    %>
        <div>
            <center>
                <h1>Flights Catalogue Management</h1>
            </br>
            </br>
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

        <div>
            <table>
                <tr class="flight-list">
                    <th></th>
                    <th>Flight ID</th>
                    <th>Company</th>
                    <th>Price</th>
                    <th>Departure Time</th>
                    <th>Arrival Time</th>
                    <th>Departure</th>
                    <th>Destination</th>
                    <th>Available</th>
                    <th>Duration</th>
                    <th>Stop</th>
                    <th>Seat</th>
                    <th></th>

                </tr>
            <c:forEach var="flight" items="${flightList}">
                <tr class="flight-list">
                    <td><img width="200px" height="200px" src="images/${flight.img}"></td>
                    <td><c:out value="${flight.itemID}" /></td>
                    <td><c:out value="${flight.name}" /></td>
                    <fmt:formatNumber var="formattedUnitPrice" type="number" minFractionDigits="2" maxFractionDigits="2" value="${flight.price}" />
                    <td>$<c:out value="${formattedUnitPrice}" /></td>
                    <td><c:out value="${flight.startTime}" /></td>
                    <td><c:out value="${flight.endTime}" /></td>
                    <td><c:out value="${flight.departureCity}" /></td>
                    <td><c:out value="${flight.destinationCity}" /></td>
                    <td><c:out value="${flight.availability}" /></td>
                    <td><c:out value="${flight.hours}" /> hrs</td>
                    <td><c:out value="${flight.stops}" /></td>
                    <td><c:out value="${flight.seatType}" /></td>
                    <td>
                        <form method="POST" action="http://localhost:8080/UpdateFlightFormController">
                            <input type="hidden" name="itemID" value="${flight.itemID}"/>
                            <input id="updateFlight" type="submit" value="Update"/>
                        </form>
                        <form action="deleteFlight.jsp">
                            <input type="hidden" name="itemID" value="${flight.itemID}"/>
                            <input id="deleteFlight" type="submit" value="Delete"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </table>
        </div> 

    <%
    } else{
    %>
        <h1 align="center">No Permission to Access</h1>
    <% 
    }
    %>  

    <% 
    }
    %>  


    </body>
</html>

