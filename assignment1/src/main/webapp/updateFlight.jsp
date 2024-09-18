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
        Flight updatingFlight = flightList.get(flightUpdatingIndex);
        session.setAttribute("updatingFlight",updatingFlight);     

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
        <form action="http://localhost:8080/updateFlightOperation.jsp">
            
        <!-- Form -->
        <h1>Update the flight</h1>
            

            <!-- Form inputs -->
            <div>
                <label>Flight Id: </label>
                <input type="text" name="itemID" id="itemID" value="<c:out value='${updatingFlight.itemID}' />"/>
            </div>
            <br/>
        
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
        
            <div>
                <label>Duration: </label>
                <input type="text" name="hours" id="hours" value="<c:out value='${updatingFlight.hours}' />"/>
            </div>
            <br/>

            <div>
                <label>Stops: </label>
                <input list="stopList" name="stops" id="stops" type="text" value="<c:out value='${updatingFlight.stops}' />"/>
                <datalist id="stopList">
                    <option value="Non Stop">
                    <option value="1 Stop">
                    <option value="2 Stops">
                    <option value="3 Stops">
                </datalist>
            </div>
            <br/>

        
            <div>
                <label>Seat: </label>
                <input list="seatList" name="seatType" id="seatType" type="text" value="<c:out value='${updatingFlight.seatType}' />"/>
                <datalist id="seatList">
                    <option value="Economy">
                    <option value="Premium Economy">
                    <option value="Business">
                </datalist>
            </div>
            <br/>

        
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
                    
            <input type="hidden" id="flightIndex" name="flightIndex" value="<%= flightUpdatingIndex%>"/>
        </table>
        </form>
    </div>   
    <br/>
    <br/>
</body>
</html>