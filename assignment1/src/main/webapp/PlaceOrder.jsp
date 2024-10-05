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
        <title>Dream Escape - Order Placed!</title>
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
            <h2 class="form-element">Travel Details:</h2>

            <div class="form-container">
                <form action="/PlaceOrderController" method="post">
                    <div class="form-element">
                        <label for="first-name">First Name:</label>
                        <input type="text" name="firstName" id="first-name" required />
                    </div>
                    <div class="form-element">
                        <label for="last-name">Last Name:</label>
                        <input type="text" name="lastName" id="last-name" required />
                    </div>
                    <div class="form-element">
                        <label for="passengers">Number of Passengers:</label>
                        <input type="number" name="passengers" id="passengers" min="1" required />
                    </div>
                    <div class="form-element">
                        <label for="seatType">Seat Type:</label>
                        <input type="text" name="seatType" id="seatType" required />
                    </div>
                    <div class="form-element">
                        <label for="departureDate">Departure Date:</label>
                        <input type="date" name="departureDate" id="departureDate" required />
                    </div>
                    <div class="form-element">
                        <label for="returnDate">Return Date:</label>
                        <input type="date" name="returnDate" id="returnDate" required />
                    </div>
                    <button class="general-buttons form-element">Update Details</button>
                </form>
            </div>
        </div>
    </main>
</body>
</html>
