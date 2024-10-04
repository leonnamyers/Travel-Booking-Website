<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="com.iotbay.Model.User" %>
<%@page import="com.iotbay.Model.Cart" %>
<%@page import="com.iotbay.Model.Flight" %>
<%@page import="com.iotbay.Model.Hotel" %>
<%@page import="com.iotbay.Model.Order" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

        <script type="text/javascript" src="/js/index.js">
        </script>
        <title>Dream Escape</title>
        <nav>
            <ul>
                <<li><a href="index.jsp">Home</a></li>
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
        </nav>
    </head>
<body>
    <main class="text-display">
        <div class="form-container">
            <h2 class="form-element">Review Trip:</h2>
            <%
                Flight flight = (Flight) request.getAttribute("flight");
            %>

            <div class="form-container">
                <form action="/PlaceOrderController" method="post">
                    <div class="flight-details">
                        <p>Departing Flight - <%= flight.getStartTime().toLocalDateTime().toLocalDate() %></p>
                        <div class="flight-info">
                            <div>
                                <h3><%= flight.getName() %></h3>
                                <p><%= flight.getDepartureCity() %> to <%= flight.getDestinationCity() %></p>
                                <p>Departure: <%= flight.getStartTime() %></p>
                                <p>Arrival: <%= flight.getEndTime() %></p>
                                <p>Duration: <%= flight.getHours() %> hours</p>
                                <p>Seat Type: <%= flight.getSeatType() %></p>
                            </div>
                        </div>
                    </div>
                    <button class="general-buttons form-element">Book Now ></button>
                </form>
            </div>
        </div>
    </main>
</body>
</html>
