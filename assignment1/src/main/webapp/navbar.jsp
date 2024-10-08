<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Random"%>
<%@ page import="com.iotbay.*" %>
<%@ page import="com.iotbay.Model.*" %>
<%@page import="java.sql.Timestamp" %>
<%@page import="java.util.ArrayList"%>


<head>
    <link rel="stylesheet" href="css/style.css">
</head>

<nav>
    <h1>Dream Escape</h1>
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

<nav>
    <ul>
        <li><a href="PackageController?action=loadPackages">Package Booking</a></li>
        <li><a href="CruiseController?action=loadCruises">Cruise Booking</a></li>
        <li><a href="//localhost:8080/flights.jsp">Flight</a></li>
        <li><a href="//localhost:8080/hotels.jsp">Hotel</a></li>
        <li><a href="userListPanel.jsp">User List</a></li>
    </ul>
</nav>

