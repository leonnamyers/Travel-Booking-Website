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
    <style>
        .div-1 {
            background-color: #a7abe0;
        }
        
        .update-container {
            background: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            border-radius: 10px;
            padding: 20px;
            width: 40%;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .staff-container {
            background: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            border-radius: 10px;
            padding: 20px;
            width: 40%;
            display: flex;
            align-items: center;
            justify-content: center;
        }
    </style>
    <!-- get request parameters of previously entered input
     if redirection ever happen, that way the data don't lost -->
    <%
        ArrayList<Flight> flightList = (ArrayList<Flight>)session.getAttribute("flightList");
        User user = (User)session.getAttribute("user");   
        String name = (String)request.getParameter("name");
        String startTime = (String)request.getParameter("startTime");
        String endTime = (String)request.getParameter("endTime");
        String departureCity = (String)request.getParameter("departureCity");
        String destinationCity = (String)request.getParameter("destinationCity");
        String img = (String)request.getParameter("img");
        String price = (String)request.getParameter("price");
        String availability = (String)request.getParameter("availability");
    
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
            <li><a href="cart.jsp">Cart</a></li>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="account_details.jsp">Account</a></li>
            <li><a href="logout.jsp">Logout</a></li>
        </ul>
        <!--If User is NOT logged in-->
        <%
        } else {
        %>
        <ul>
            <li><a href="cart.jsp">Cart</a></li>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="login.jsp">Login</a></li>
            <li><a href="register.jsp">Register</a></li>
        </ul>
        <% 
        }
        %>
    </nav>

    <!-- Management block -->
    <div align="center" class="div-1">
        <br/>
        <br/>
        <div class="staff-container">
        <center>
            <h1>Flight Catalogue Management</h1>
            <br/>
            <h2>
                <form action="addFlight.jsp">
                    <button type="submit">ADD NEW FLIGHT</button>
                </form>
                &nbsp;&nbsp;&nbsp;
                <form method="post" action="/FlightCatalogueController">
                    <button type="submit">LIST ALL FLIGHT</button>
                </form>
            </h2>
            <br/>
        </center>
    </div>
    <br/>
    <br/>
    </div>


    <!-- Add flight form with error validation -->
    <div align="center" class="div-1">
        <div  class="update-container">
        <form method="post" action="/AddFlightController">
            <h1>Add new flight</h1>
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

            <c:if test="${empty name}">
                <div>
                <label>Company: </label>
                <input list="companyList" name="name" id="name" type="text" value=""/>
                <datalist id="companyList">
                    <option value="Traveling">
                    <option value="Harmony">
                    <option value="JetEngine">
                </datalist>
                </div>
                <br/>
            </c:if>

            <c:if test="${not empty name}">
                <div>
                <label>Company: </label>
                <input list="companyList" name="name" id="name" type="text" value="<%= name%>"/>
                <datalist id="companyList">
                    <option value="Traveling">
                    <option value="Harmony">
                    <option value="JetEngine">
                </datalist>
                </div>
                <br/>
            </c:if>

            <c:if test="${not empty price}">
                <div>
                <label>Price: </label>
                <input type="text" name="price" id="price" value="<%= price%>"/>
                </div>
            <br/>
            </c:if>

            <c:if test="${empty price}">
                <div>
                <label>Price: </label>
                <input type="text" name="price" id="price" value="0"/>
                </div>
                <br/>
            </c:if>

            <c:if test="${not empty availability}">
                <div>
                <label>Availability: </label>
                <input type="text" name="availability" id="availability" value="<%= availability%>"/>
                </div>
                <br/>
            </c:if>

            <c:if test="${empty availability}">
                <div>
                <label>Availability: </label>
                <input type="text" name="availability" id="availability" value="0"/>
                </div>
                <br/>
            </c:if>

            <c:if test="${not empty startTime}">
                <div>
                <label>Departure Time: </label>
                <input type="datetime-local" name="startTime" id="startTime" value="<%= startTime%>"/>
                </div>
                <br/>
            </c:if>

            <c:if test="${empty startTime}">
                <div>
                <label>Departure Time: </label>
                <input type="datetime-local" name="startTime" id="startTime"/>
                </div>
                <br/>
            </c:if>
            
            <c:if test="${empty endTime}">
                <div>
                <label>Arrival Time: </label>
                <input type="datetime-local" name="endTime" id="endTime"/>
                </div>
                <br/>
            </c:if>

            <c:if test="${not empty endTime}">
                <div>
                <label>Arrival Time: </label>
                <input type="datetime-local" name="endTime" id="endTime" value="<%= endTime%>"/>
                </div>
                <br/>
            </c:if>

            <c:if test="${empty departureCity}">
                <div>
                <label>Departure: </label>
                <input list="departureCityList" name="departureCity" id="departureCity" type="text" value=""/>
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
            </c:if>

            <c:if test="${not empty departureCity}">
                <div>
                <label>Departure: </label>
                <input list="departureCityList" name="departureCity" id="departureCity" type="text" value="<%= departureCity%>"/>
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
            </c:if>

            <c:if test="${empty destinationCity}">
                <div>
                <label>Destination: </label>
                <input list="destinationCityList" name="destinationCity" id="destinationCity" type="text" value=""/>
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
            </c:if>
            <c:if test="${not empty destinationCity}">
                <div>
                <label>Destination: </label>
                <input list="destinationCityList" name="destinationCity" id="destinationCity" type="text" value="destinationCity"/>
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
            </c:if>

            <div>
            <label>Stops: </label>
            <select id="stops" name="stops" type="text">
                <option value="Non Stop" selected="selected">Non Stop</option>
                <option value="1 Stop">1 stop</option>
                <option value="2 Stops">2 stops</option>
                <option value="3 Stops">3 stops</option>
            </select>
            </div>
            <br/>

            <div>
            <label>Seat: </label>
            <select id="seatType" name="seatType" type="text">
                <option value="Economy" selected="selected">Economy</option>
                <option value="Premium Economy">Premium Economy</option>
                <option value="Business">Business</option>
            </select>
            </div>
            <br/>

            <c:if test="${empty img}">
                <div>
                <label>Image: </label>
                <input list="imgList" name="img" id="img" type="text" value=""/>
                <datalist id="imgList">
                    <option value="Harmony.jpg">
                    <option value="JetEngine.jpg">
                    <option value="Travel.jpg">
                    <option value="imageYetToCome.jpg">
                </datalist>
                </div>
                <br/>
            </c:if>
            <c:if test="${not empty img}">
                <div>
                <label>Image: </label>
                <input list="imgList" name="img" id="img" type="text" value="<%=img%>"/>
                <datalist id="imgList">
                    <option value="Harmony.jpg">
                    <option value="JetEngine.jpg">
                    <option value="Travel.jpg">
                    <option value="imageYetToCome.jpg">
                </datalist>
                </div>
                <br/>
            </c:if>

            <div>
            <input type="submit" value="Save" />
            </div>
            <br/>
        </form>
        </div>
        <br/>
        <br/>
    </div>   
</body>
</html>