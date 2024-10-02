<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.iotbay.Model.*"%>
<%@page import="com.iotbay.Dao.*"%>
<%@page import="com.iotbay.Controller.*"%>
<%@page import="java.sql.Date" %>
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

    <%
        <!-- get hotel attribute that are for updating -->
        User user = (User)session.getAttribute("user");
        Hotel updatingHotel = (Hotel) session.getAttribute("updatingHotel");
        <!-- get validation messages -->
        String nameErr = (String) request.getAttribute("nameErr");
        String priceErr = (String) request.getAttribute("priceErr");
        String availabilityErr = (String) request.getAttribute("availabilityErr");
        String imgErr = (String) request.getAttribute("imgErr");
        String cityErr = (String) request.getAttribute("cityErr");
        String availableBeginDateErr = (String) request.getAttribute("availableBeginDateErr");
        String availableEndDateErr = (String) request.getAttribute("availableEndDateErr");
    %>
    <body>
        <nav>
            <h1>Hotel</h1>
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

        <!-- management block -->
        <div align="center" class="div-1">
            <br/>
            <br/>
            <div class="staff-container">
            <center>
                <h1>Hotel Catalogue Management</h1>
                <br/>
                <h2>
                    <form action="http://localhost:8080/addHotel.jsp">
                        <button type="submit">ADD NEW HOTEL</button>
                    </form>
                    &nbsp;&nbsp;&nbsp;
                    <form method="post" action="/HotelCatalogueController">
                        <button type="submit">LIST ALL HOTEL</button>
                    </form>
                </h2>
                <br/>
            </center>
            </div>
            <br/>
            <br/>
        </div>
        
        <!-- Updating form -->
        <div align="center" class="div-1">
            <div  class="update-container">
            <form method="post" action="http://localhost:8080/UpdateHotelController">
                <h1>Update The Hotel</h1>
                <br/>
                <label>
                    <!-- Updating validation -->
                    <% if(nameErr != null) { %>
                    <%=nameErr%>
                    <% } %>
                    <% if(priceErr != null) { %>
                    <%=priceErr%>
                    <% } %>
                    <% if(availabilityErr != null) { %>
                    <%=availabilityErr%>
                    <% } %>
                    <% if(imgErr != null) { %>
                    <%=imgErr%>
                    <% } %>
                    <% if(cityErr != null) { %>
                    <%=cityErr%>
                    <% } %>
                    <% if(availableBeginDateErr != null) { %>
                    <%=availableBeginDateErr%>
                    <% } %>
                    <% if(availableEndDateErr != null) { %>
                        <%=availableEndDateErr%>
                    <% } %>
                </label>
                <br/>

                <!-- inputs -->
                <div>
                <label>Hotel: </label>
                <input list="hotel-list" name="name" id="name" type="text" value="<c:out value='${updatingHotel.name}' />"/>
                <datalist id="hotel-list">
                    <option value="Tranquil Oasis">
                    <option value="Amour Villa">
                    <option value="Prince Hotel">
                    <option value="Beach Life">
                </datalist>
                </div>
                <br/>
    

                <div>
                <label>Price: </label>
                <input type="text" name="price" id="price" value="<c:out value='${updatingHotel.price}' />"/>
                </div>
                <br/>

                <div>
                <label>Availability: </label>
                <input type="text" name="availability" id="availability" value="<c:out value='${updatingHotel.availability}' />"/>
                </div>
                <br/>
        <c:choose>
            <c:when test="${updatingHotel.roomType.equals('Double')}">
                <div>
                <label>Room Type: </label>
                <select id="roomType" name="roomType" type="text">
                    <option value="Single">Single</option>
                    <option value="Double" selected="selected">Double</option>
                    <option value="Triple">Triple</option>
                    <option value="Family Room">Family Room</option>
                    <option value="Queen">Queen</option>
                    <option value="Executive Suite">Executive Suite</option>
                </select>
                </div>
                <br/>
            </c:when>
            <c:when test="${updatingHotel.roomType.equals('Triple')}">
                <div>
                <label>Room Type: </label>
                <select id="roomType" name="roomType" type="text">
                    <option value="Single" selected="selected">Single</option>
                    <option value="Double">Double</option>
                    <option value="Triple" selected="selected">Triple</option>
                    <option value="Family Room">Family Room</option>
                    <option value="Queen">Queen</option>
                    <option value="Executive Suite">Executive Suite</option>
                </select>
                </div>
                <br/>
            </c:when>
            <c:when test="${updatingHotel.roomType.equals('Family Room')}">
                <div>
                <label>Room Type: </label>
                <select id="roomType" name="roomType" type="text">
                    <option value="Single" selected="selected">Single</option>
                    <option value="Double">Double</option>
                    <option value="Triple">Triple</option>
                    <option value="Family Room" selected="selected">Family Room</option>
                    <option value="Queen">Queen</option>
                    <option value="Executive Suite">Executive Suite</option>
                </select>
                </div>
                <br/>
            </c:when>
            <c:when test="${updatingHotel.roomType.equals('Queen')}">
                <div>
                <label>Room Type: </label>
                <select id="roomType" name="roomType" type="text">
                    <option value="Single" selected="selected">Single</option>
                    <option value="Double">Double</option>
                    <option value="Triple">Triple</option>
                    <option value="Family Room">Family Room</option>
                    <option value="Queen" selected="selected">Queen</option>
                    <option value="Executive Suite">Executive Suite</option>
                </select>
                </div>
                <br/>
            </c:when>
            <c:when test="${updatingHotel.roomType.equals('Executive Suite')}">
                <div>
                <label>Room Type: </label>
                <select id="roomType" name="roomType" type="text">
                    <option value="Single" selected="selected">Single</option>
                    <option value="Double">Double</option>
                    <option value="Triple">Triple</option>
                    <option value="Family Room">Family Room</option>
                    <option value="Queen">Queen</option>
                    <option value="Executive Suite" selected="selected">Executive Suite</option>
                </select>
                </div>
                <br/>
            </c:when>
            <c:otherwise>
                <div>
                <label>Room Type: </label>
                <select id="roomType" name="roomType" type="text">
                    <option value="Single" selected="selected">Single</option>
                    <option value="Double">Double</option>
                    <option value="Triple">Triple</option>
                    <option value="Family Room">Family Room</option>
                    <option value="Queen">Queen</option>
                    <option value="Executive Suite">Executive Suite</option>
                </select>
                </div>
                <br/>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${updatingHotel.roomSize.equals('2 people')}">
                <div>
                <label>Room Size:</label>
                <select id="roomSize" name="roomSize" type="text">
                    <option value="1 person">1 person</option>
                    <option value="2 people" selected="selected">2 people</option>
                    <option value="3 people">3 people</option>
                    <option value="4 people">4 people</option>
                </select>
                </div>
                <br/>
            </c:when>
            <c:when test="${updatingHotel.roomSize.equals('3 people')}">
                <div>
                <label>Room Size:</label>
                <select id="roomSize" name="roomSize" type="text">
                    <option value="1 person">1 person</option>
                    <option value="2 people">2 people</option>
                    <option value="3 people" selected="selected">3 people</option>
                    <option value="4 people">4 people</option>
                </select>
                </div>
                <br/>
            </c:when>
            <c:when test="${updatingHotel.roomSize.equals('4 people')}">
                <div>
                <label>Room Size:</label>
                <select id="roomSize" name="roomSize" type="text">
                    <option value="1 person">1 person</option>
                    <option value="2 people">2 people</option>
                    <option value="3 people">3 people</option>
                    <option value="4 people" selected="selected">4 people</option>
                </select>
                </div>
                <br/>
            </c:when>
            <c:otherwise>
                <div>
                <label>Room Size:</label>
                <select id="roomSize" name="roomSize" type="text">
                    <option value="1 person" selected="selected">1 person</option>
                    <option value="2 people">2 people</option>
                    <option value="3 people">3 people</option>
                    <option value="4 people">4 people</option>
                </select>
                </div>
                <br/>
            </c:otherwise>
        </c:choose>

    
                <div>
                <label>City: </label>
                <input list="cities" name="city" id="city" type="text" value="<c:out value='${updatingHotel.city}' />"/>
                <datalist id="cities">
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
    


                <label>Begin:</label>
                <input name="availableBeginDate" id="availableBeginDate" type="date" value="<c:out value='${updatingHotel.availableBeginDate}' />"/>
    
                <label>End:</label>
                <input name="availableEndDate" id="availableEndDate" type="date" value="<c:out value='${updatingHotel.availableEndDate}' />"/>

    
    
                <div>
                <br/>
                <label>Image: </label>
                <input list="hotelImgList" name="img" id="img" type="text" value="<c:out value='${updatingHotel.img}' />"/>
                <datalist id="hotelImgList">
                    <option value="TranquilOasis.jpg">
                    <option value="AmourVilla.jpg">
                    <option value="PrinceHotel.jpg">
                    <option value="BeachLife.jpg">
                </datalist>
                </div>
                <br/>
    
                <div>
                <input type="submit" value="Save" />
                </div>
                <br/>
                <input name="itemID" id="itemID" type="hidden" value="<c:out value='${updatingHotel.itemID}' />">
            </form>
            </div>
            <br/>
            <br/>
        </div>   

    </body>
</html>