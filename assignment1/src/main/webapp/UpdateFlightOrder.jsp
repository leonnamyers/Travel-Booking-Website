<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="com.iotbay.Model.User" %>
<%@page import="com.iotbay.Model.Cart" %>
<%@page import="com.iotbay.Model.Flight" %>
<%@page import="com.iotbay.Model.Hotel" %>
<%@page import="com.iotbay.Model.Package" %>
<%@page import="com.iotbay.Model.Order" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

        <script type="text/javascript" src="/js/index.js"></script>
        <title>Dream Escape</title>
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="logout.jsp">Logout</a></li>
            </ul>
        </nav>
    </head>
<body>
    <main class="text-display">
        <div class="form-container">
            <h2 class="form-element">Travel Details:</h2>

            <div class="form-container">
                <form action="/UpdateFlightOrderController" method="post">
                    <input type="hidden" name="orderID" value="${order.orderID}"/>
                

                    <div class="form-element">
                        <label for="email">Email:</label>
                        <input type="text" name="email" id="email" required />
                    </div>
                    <div class="form-element">
                        <label for="seatType">Seat Type:</label>
                        <input type="text" name="seatType" id="seatType" required />
                    </div>
                    <div class="form-element">
                        <label for="startTime">Start Time:</label>
                        <input type="text" name="startTime" id="startTime" required />
                    </div>
                    <div class="form-element">
                        <label for="endTime">End Time:</label>
                        <input type="text" name="endTime" id="endTime" required />
                    </div>
                    <button class="general-buttons form-element">Update Details</button>
                </form>
            </div>
        </div>
    </main>
</body>
</html>
