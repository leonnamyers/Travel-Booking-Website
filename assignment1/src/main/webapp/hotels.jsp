<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.iotbay.Model.*"%>
<%@page import="com.iotbay.Dao.*"%>
<%@page import="com.iotbay.Controller.*"%>
<%@page import="java.sql.*" %>
<html>
    <head>
        <meta http-equiv="Content-Type="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/navbar.css"> 
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <title>Hotel Page</title>
    </head>

    <style>
        .div-1 {
            background-color: #a7abe0;
        }
    
        .search-container {
            background: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            border-radius: 10px;
            padding: 20px;
            width: 80%;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .flight-container {
            background: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            border-radius: 10px;
            padding: 20px;
            width: 97%;
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
            ArrayList<Hotel> Hotel = (ArrayList<Hotel>)session.getAttribute("hotelList");
            User user = (User)session.getAttribute("user");
            String checkInTime = (String)session.getAttribute("checkInTime");
            String checkOutTime = (String)session.getAttribute("checkOutTime");
    %>

    <body>
    <nav>
        <h1>Hotel</h1>
        <!--If User is logged in-->
        <%
        if (session != null && session.getAttribute("user") != null) { 
        %>
        <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="account_details.jsp">Account</a></li>
            <li><a href="logout.jsp">Logout</a></li>
                <a href="Cart.jsp">
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
                <a href="Cart.jsp">
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

    <img src="/images/Hotel.jpg" width="100%" >

    <div align="center" class="div-1"> 
        <br>
        <br> 
        <div class="search-container">
        <form method="post" action="/FilteringHotelController">

            <label>City:</label>
                <input list="cities" name="city" id="city" type="text" value=""/>
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
                </input>
            
            <label>From:</label>
            <c:if test="${empty checkInTime}">
                <input name="checkInTime" id="checkInTime" type="date" value=""/>
            </c:if>
            <c:if test="${not empty checkInTime}">
                <input name="checkInTime" id="checkInTime" type="date" value="<%= checkInTime%>"/>
            </c:if>

            <label>To:</label>
            <c:if test="${empty checkOutTime}">
                <input name="checkOutTime" id="checkOutTime" type="date" value=""/>
            </c:if>
            <c:if test="${not empty checkOutTime}">
                <input name="checkOutTime" id="checkOutTime" type="date" value="<%= checkOutTime%>"/>
            </c:if>

            <label>Room Type:</label>
            <select id="roomType" name="roomType" type="text">
                <option value="" selected="selected">All</option>
                <option value="Single">Single</option>
                <option value="Double">Double</option>
                <option value="Triple">Triple</option>
                <option value="Family Room">Family Room</option>
                <option value="Queen">Queen</option>
                <option value="Executive Suite">Executive Suite</option>
            </select>

            <label>Room Size:</label>
            <select id="roomSize" name="roomSize" type="text">
                <option value="" selected="selected">All</option>
                <option value="1 person">1 person</option>
                <option value="2 people">2 people</option>
                <option value="3 people">3 people</option>
                <option value="4 people">4 people</option>
            </select>

            <input type="submit" value="SEARCH">
        </form>
        </div> 
        <br/>
        <br/> 
    </div>

    <%
        if ((user == null) || (user != null && user.getUserType() == UserType.CUSTOMER)) { 
    %>

    <div align="center" class="div-1">
        <div class="flight-container">
        <br/>
        <br/>
        <table>
            <caption><h2>Hotel Booking</h2></caption>
            <tr class="flight-list">
                <th></th>
                <th>Hotel</th>
                <th>Price</th>
                <th>Room Type</th>
                <th>Room Size</th>
                <th>Location</th>
                <th></th>

            </tr>
        <c:forEach var="hotel" items="${hotelList}">
            <tr class="flight-list">
                <td><img width="50px" height="50px" src="images/${hotel.img}"></td>
                <td><c:out value="${hotel.name}" /></td>
                <fmt:formatNumber var="formattedUnitPrice" type="number" minFractionDigits="2" maxFractionDigits="2" value="${hotel.price}" />
                <td>$<c:out value="${formattedUnitPrice}" /></td>
                <td><c:out value="${hotel.roomType}" /></td>
                <td><c:out value="${hotel.roomSize}" /></td>
                <td><c:out value="${hotel.city}" /></td>
                <td>
                    <form method="POST" action="/AddHotelToCartController">
                        <input type="hidden" name="itemID" value="${hotel.itemID}"/>
                        <input id="addCartBtn" type="submit" value="Add to cart"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </table>
    </div>
    <br/>
    <br/>
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
        <div align="center" class="div-1">
            <div class="staff-container">
            <center>
                <h1>Hotels Catalogue Management</h1>
            </br>
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

        <div align="center" class="div-1">
            <div class="flight-container">
            <table>
                <tr class="flight-list">
                    <th></th>
                    <th>Hotel ID</th>
                    <th>Hotel</th>
                    <th>Price</th>
                    <th>Available</th>
                    <th>Room Type</th>
                    <th>Room Size</th>
                    <th>Location</th>
                    <th>Begin date</th>
                    <th>End date</th>
                    <th></th>

                </tr>
            <c:forEach var="hotel" items="${hotelList}">
                <tr class="flight-list">
                    <td><img width="50px" height="50px" src="images/${hotel.img}"></td>
                    <td><c:out value="${hotel.itemID}"/></td>
                    <td><c:out value="${hotel.name}" /></td>
                    <fmt:formatNumber var="formattedUnitPrice" type="number" minFractionDigits="2" maxFractionDigits="2" value="${hotel.price}" />
                    <td>$<c:out value="${formattedUnitPrice}" /></td>
                    <td><c:out value="${hotel.availability}" /></td>
                    <td><c:out value="${hotel.roomType}" /></td>
                    <td><c:out value="${hotel.roomSize}" /></td>
                    <td><c:out value="${hotel.city}" /></td>
                    <td><c:out value="${hotel.availableBeginDate}"/></td>
                    <td><c:out value="${hotel.availableEndDate}"/></td>
                    <td>
                        <form method="POST" action="http://localhost:8080/UpdateHotelFormController">
                            <input type="hidden" name="itemID" value="${hotel.itemID}"/>
                            <input id="updateHotel" type="submit" value="Update"/>
                        </form>
                        <form method="POST" action="http://localhost:8080/DeleteHotelController">
                            <input type="hidden" name="itemID" value="${hotel.itemID}"/>
                            <input id="deleteHotel" type="submit" value="Delete"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </table>
            </div>
            <br/>
            <br/>
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

