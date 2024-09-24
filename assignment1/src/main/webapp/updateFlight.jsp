<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.*" %>
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
        User user = (User)session.getAttribute("user");
        Flight updatingFlight = (Flight) session.getAttribute("updatingFlight");

        String nameErr = (String) request.getAttribute("nameErr");
        String departureTimeErr = (String) request.getAttribute("departureTimeErr");
        String arrivalTimeErr = (String) request.getAttribute("arrivalTimeErr");
        String priceErr = (String) request.getAttribute("priceErr");
        String availabilityErr = (String) request.getAttribute("availabilityErr");
        String departureCityErr = (String) request.getAttribute("departureCityErr");
        String destinationCityErr = (String) request.getAttribute("destinationCityErr");
        String imgErr = (String) request.getAttribute("imgErr");
        
    %>
<body>
    <nav>
        <h1>Flight</h1>
        <!--If User is logged in-->
        <%
        if (session != null && session.getAttribute("user") != null) { 
        %>
        <ul>
            <li><a href="Cart.jsp">Cart</a></li>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="account_details.jsp">Account</a></li>
            <li><a href="logout.jsp">Logout</a></li>
        </ul>
        <!--If User is NOT logged in-->
        <%
        } else {
        %>
        <ul>
            <li><a href="Cart.jsp">Cart</a></li>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="login.jsp">Login</a></li>
            <li><a href="register.jsp">Register</a></li>
        </ul>
        <% 
        }
        %>
    </nav>
    <br/>
    <br/>

    <div>
        <center>
            <h1>Flight Catalogue Management</h1>
            <br/>
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
        <!-- update -->
        <form method="post" action="/UpdateFlightController">
            
        <!-- Form -->
        <h1>Update the flight</h1>
            <br/>
            <label>
                <% if(nameErr != null) { %>
                <%=nameErr%></h1
                <% } %>
                <% if(departureTimeErr != null) { %>
                <%=departureTimeErr%>
                <% } %>
                <% if(arrivalTimeErr != null) { %>
                <%=arrivalTimeErr%>
                <% } %>
                <% if(departureCityErr != null) { %>
                <%=departureCityErr%>
                <% } %>
                <% if(destinationCityErr != null) { %>
                <%=destinationCityErr%>
                <% } %>
                <% if(imgErr != null) { %>
                <%=imgErr%>
                <% } %>
                <% if(priceErr != null) { %>
                <%=priceErr%>
                <% } %>
                <% if(availabilityErr != null) { %>
                <%=availabilityErr%>
                <% } %>
            </label>
            <br/>
            <!-- Form inputs -->
            <input type="hidden" name="itemID" id="itemID" value="<c:out value='${updatingFlight.itemID}' />"/>
        
            <div>
                <label>Company: </label>
                <input list="companyList" name="name" id="name" type="text" value="<c:out value='${updatingFlight.name}' />"/>
                <datalist id="companyList">
                    <option value="Traveling">
                    <option value="Harmony">
                    <option value="JetEngine">
                </datalist>
            </div>
            <br/>

            <div>
                <label>Price: </label>
                <input type="text" name="price" id="price" value="<c:out value='${updatingFlight.price}' />"/>
            </div>
            <br/>
        
            <div>
                <label>Availability: </label>
                <input type="text" name="availability" id="availability" value="<c:out value='${updatingFlight.availability}' />"/>
            </div>
            <br/>
        
            <div>
                <label>Departure Time: </label>
                <input type="datetime-local" name="startTime" id="startTime" value="<c:out value='${updatingFlight.startTime}' />"/>
            </div>
            <br/>
        
            <div>
                <label>Arrival Time: </label>
                <input type="datetime-local" name="endTime" id="endTime" value="<c:out value='${updatingFlight.endTime}' />"/>
            </div>
            <br/>
        
            <div>
                <label>Departure: </label>
                <input list="departureCityList" name="departureCity" id="departureCity" type="text" value="<c:out value='${updatingFlight.departureCity}' />"/>
                <datalist id="departureCityList">
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
            </div>
            <br/>
        
            <div>
                <label>Destination: </label>
                <input list="destinationCityList" name="destinationCity" id="destinationCity" type="text" value="<c:out value='${updatingFlight.destinationCity}' />"/>
                <datalist id="destinationCityList">
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
            </div>
            <br/> 

        <c:choose>
            <c:when test="${updatingFlight.stops.equals('Non Stop')}">
                <div>
                    <label>Stops: </label>
                    <select id="stops" name="stops" type="text">
                        <option value="Non Stop" selected="selected">Non Stop</option>
                        <option value="1 Stop">1 Stop</option>
                        <option value="2 Stops">2 Stops</option>
                        <option value="3 Stops">3 Stops</option>
                    </select>
                </div>
                <br/>
            </c:when>
            <c:when test="${updatingFlight.stops.equals('1 Stop')}">
                <div>
                    <label>Stops: </label>
                    <select id="stops" name="stops" type="text">
                        <option value="Non Stop">Non Stop</option>
                        <option value="1 Stop" selected="selected">1 Stop</option>
                        <option value="2 Stops">2 Stops</option>
                        <option value="3 Stops">3 Stops</option>
                    </select>
                </div>
                <br/>
            </c:when> 
            <c:when test="${updatingFlight.stops.equals('2 Stops')}">
                <div>
                    <label>Stops: </label>
                    <select id="stops" name="stops" type="text">
                        <option value="Non Stop">Non Stop</option>
                        <option value="1 Stop">1 Stop</option>
                        <option value="2 Stops" selected="selected">2 Stops</option>
                        <option value="3 Stops">3 Stops</option>
                    </select>
                </div>
                <br/>
            </c:when>
            <c:otherwise>
                <div>
                    <label>Stops: </label>
                    <select id="stops" name="stops" type="text">
                        <option value="Non Stop">Non Stop</option>
                        <option value="1 Stop">1 Stop</option>
                        <option value="2 Stops">2 Stops</option>
                        <option value="3 Stops" selected="selected">3 Stops</option>
                    </select>
                </div>
                <br/>
            </c:otherwise>  
        </c:choose>

        <c:choose>
            <c:when test="${updatingFlight.seatType.equals('Economy')}">
                <div>
                <label>Seat: </label>
                    <select id="seatType" name="seatType" type="text">
                        <option value="Economy" selected="selected">Economy</option>
                        <option value="Premium Economy">Premium Economy</option>
                        <option value="Business">Business</option>
                    </select>
                </div>
                <br/>
            </c:when>
            <c:when test="${updatingFlight.seatType.equals('Premium Economy')}">
                <div>
                <label>Seat: </label>
                    <select id="seatType" name="seatType" type="text">
                        <option value="Economy">Economy</option>
                        <option value="Premium Economy" selected="selected">Premium Economy</option>
                        <option value="Business">Business</option>
                    </select>
                </div>
                <br/>
            </c:when>
            <c:otherwise>
                <div>
                <label>Seat: </label>
                    <select id="seatType" name="seatType" type="text">
                        <option value="Economy">Economy</option>
                        <option value="Premium Economy">Premium Economy</option>
                        <option value="Business" selected="selected">Business</option>
                    </select>
                </div>
                <br/>
            </c:otherwise>
        </c:choose>

            <div>
                <label>Image: </label>
                <input list="imgList" name="img" id="img" type="text" value="<c:out value='${updatingFlight.img}' />"/>
                <datalist id="imgList">
                    <option value="Harmony.jpg">
                    <option value="JetEngine.jpg">
                    <option value="Travel.jpg">
                    <option value="imageYetToCome.jpg">
                </datalist>
            </div>
            <br/>
        
            <div>
                <input type="submit" value="Save" />
            </div>
        </table>
        </form>
    </div>   
    <br/>
    <br/>
</body>
</html>